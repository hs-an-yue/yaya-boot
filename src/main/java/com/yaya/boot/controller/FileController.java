package com.yaya.boot.controller;

import cn.hutool.core.util.IdUtil;
import com.yaya.boot.config.YaYaConfig;
import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysFile;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.service.SysFileService;
import com.yaya.boot.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 文件操作管理
 */
@Tag(name = "文件管理")
@RestController
public class FileController {

    @Resource
    private SysFileService sysFileService;

    @Resource
    private YaYaConfig yaYaConfig;
    //本系统支持的文件格式
    private final List<String> extensions= Arrays.asList(".zip",".rar",".mp3",".mp4",".pdf",".docx",".xlsx",".txt",".jpg",".jpeg",".png",".gif",".bmp",".tiff",".tif",".webp",".svg");
    //头像格式
    private final List<String> picExtensions = Arrays.asList(".jpg",".jpeg",".png",".gif");
    //MIME格式
    private static final Set<String> ALLOWED_MIME = Set.of("image/jpeg", "image/png", "image/gif", "image/bmp", "image/webp");
    private final Tika tika = new Tika();
    /**
     * 头像上传
     */
    @Operation(summary = "头像上传")
    @PostMapping(value = "/uploadAvatar")
    public Result uploadAvatar(@RequestParam(value = "file",required = true) MultipartFile file) throws IOException {
        //获取文件名
        String filename = file.getOriginalFilename();
        //获取文件后缀
        if(StringUtils.isNotEmpty(filename)){
            //判断文件格式是否不安全
            InputStream in = file.getInputStream();
            String detectedMime = tika.detect(in).toLowerCase();
            if (!ALLOWED_MIME.contains(detectedMime)) {
                throw new GlobalCommonException("文件格式违法");
            }

            int lastIndexIfDot = filename.lastIndexOf(".");
            if(lastIndexIfDot==-1){
                throw new GlobalCommonException("文件不存在或者格式错误");
            }
            //文件格式截取
            String suffix = filename.substring(lastIndexIfDot);
            //判断是否是支持的图片格式
            if(StringUtils.isNotEmpty(suffix)){
                //变成小写
                String s = suffix.toLowerCase();
                //判断格式是否正确
                boolean contains = picExtensions.contains(s);
                //如果不是我们支持的格式抛出异常
                if(!contains){
                    throw new GlobalCommonException("文件不存在或者格式错误");
                }
                //新文件名字
                String filename_new = IdUtil.getSnowflakeNextIdStr()+suffix;
                //文件保存的位置
                LocalDate now = LocalDate.now();
                //拼接生成
                String pinJoin="/file/avatar/"+now.getYear()+"/"+now.getMonthValue()+"/"+now.getDayOfMonth();
                //本地地址
                String localFileAddress=yaYaConfig.getPrefix()+pinJoin;
                //判断文件路径是否存在
                File f_ = new File(localFileAddress);
                if(!f_.exists()){
                    f_.mkdirs();//如果不存在逐层创建
                }

                //本地地址-完整
                String local_save_address=localFileAddress+"/"+filename_new;
                //用户访问地址-完整
                String local_server_url=pinJoin+"/"+filename_new;

                //保存到数据库
                SysFile sysFile = new SysFile();
                //数据库主键ID
                sysFile.setFileName(filename);
                sysFile.setFileType(s);
                sysFile.setTenantId(SecurityUtils.getTenantId());
                sysFile.setUploadUserId(SecurityUtils.getUserId());
                sysFile.setFileSaveUrl(local_save_address);
                sysFile.setFileServerUrl(local_server_url);
                sysFileService.addSave(sysFile);

                //保存文件到本地
                file.transferTo(new File(local_save_address));

                //返回服务器访问地址
                Map<String,Object> map = new HashMap<>();
                map.put("avatar",local_server_url);
                map.put("fileId",sysFile.getFileId());

                return Result.ok(map);
            }
        }
        return Result.error("头像上传失败,文件不存在或者文件格式不正确");
    }


    /**
     * 文件上传
     */
    @Operation(summary = "文件上传")
    @PostMapping(value = "/uploadFile")
    public Result uploadFile(@RequestParam(value = "file",required = true) MultipartFile file) throws IOException {
        //文件名
        String filename = file.getOriginalFilename();
        if(StringUtils.isNotEmpty(filename)){
            //文件格式校验
            int lastIndexIfDot = filename.lastIndexOf(".");
            if(lastIndexIfDot == -1){
                throw new GlobalCommonException("文件不存在或者格式错误");
            }
            //文件格式截取
            String suffix = filename.substring(lastIndexIfDot);
            if(StringUtils.isNotEmpty(suffix)){
                //变成小写
                String s = suffix.toLowerCase();
                //判断格式是否正确
                boolean contains = extensions.contains(s);
                //如果不是我们支持的格式抛出异常
                if(!contains){
                    throw new GlobalCommonException("文件格式["+s+"]错误");
                }
                //获取文件保存的位置的基地址
                String prefix = yaYaConfig.getPrefix();
                /*
                 * 根据文件格式进行分类存储
                 */
                String sub = s.substring(s.lastIndexOf(".") + 1);
                //防止本地文件存储重名,新生成名字基于雪花算法
                String newFileName = IdUtil.getSnowflakeNextIdStr()+suffix;
                //地址拼接（基于文件格式和时间）
                LocalDate now = LocalDate.now();
                //拼接生成
                String pinJoin="/file/"+now.getYear()+"/"+now.getMonthValue()+"/"+now.getDayOfMonth()+"/"+sub;
                //本地地址
                String localFileAddress=prefix+pinJoin;
                //判断文件路径是否存在
                File f_ = new File(localFileAddress);
                if(!f_.exists()){
                    f_.mkdirs();//如果不存在逐层创建
                }

                //本地地址-完整
                String local_save_address=localFileAddress+"/"+newFileName;
                //用户访问地址-完整
                String local_server_url=pinJoin+"/"+newFileName;

                //保存到数据库
                SysFile sysFile = new SysFile();
                //数据库主键ID
                sysFile.setFileName(filename);
                sysFile.setFileType(s);
                sysFile.setUploadUserId(SecurityUtils.getUserId());
                sysFile.setTenantId(SecurityUtils.getTenantId());
                sysFile.setFileSaveUrl(local_save_address);
                sysFile.setFileServerUrl(local_server_url);
                sysFileService.addSave(sysFile);

                //保存文件
                file.transferTo(new File(localFileAddress+"/"+newFileName));
                //服务器地址返回给客户端
                return Result.ok(local_server_url);
            }else {
                return Result.error("不支持的文件格式["+suffix+"]");
            }
        }else {
            return Result.error("文件不存在");
        }
    }


    /**
     * 分页
     */
    @Operation(summary = "文件分页")
    @Parameters(value = {
        @Parameter(name = "pageNo",description = "当前页",required = true),
        @Parameter(name = "pageSize",description = "页容量",required = true),
        @Parameter(name = "fileName",description = "文件名",required = false),
        @Parameter(name = "tenantId",description = "租户ID",required = false),
        @Parameter(name = "startTime",description = "开始时间",required = false),
        @Parameter(name = "endTime",description = "结束时间",required = false)
    })
    @GetMapping(value = "/getFilePage")
    public Result getFilePage(@RequestParam(value = "pageNo",required = true) Integer pageNo,
                              @RequestParam(value = "pageSize",required = true) Integer pageSize,
                              @RequestParam(value = "fileName",required = false) String fileName,
                              @RequestParam(value = "tenantId",required = false) Long tenantId,
                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "endTime",required = false) LocalDateTime startTime,
                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "endTime",required = false) LocalDateTime endTime){
        return Result.ok(sysFileService.getFilePage(pageNo, pageSize, fileName, startTime, endTime,tenantId));
    }
}
