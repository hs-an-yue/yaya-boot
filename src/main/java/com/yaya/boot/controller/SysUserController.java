package com.yaya.boot.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.excel.UserExcel;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户管理
 */
@Tag(name = "用户管理")
@RestController
public class SysUserController {

    @Resource
    private SysUserService sysUserService;


    @Operation(summary = "用户-单个添加")
    @PostMapping(value = "/addSysUser")
    public Result addSysUser(@RequestBody SysUser sysUser) {
        sysUserService.addSysUser(sysUser);
        return Result.ok();
    }

    @Operation(summary = "用户-用户模板下载")
    @GetMapping(value = "/downLoadSysUserTemplate",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downLoadSysUserTemplate() throws IOException {
        //读取输入流
        InputStream in = ResourceUtil.getStream("classpath:excel/sys_user_excel.xlsx");
        //创建下载缓冲区
        byte[] body = new byte[in.available()];
        //将输入流数据读入缓冲区
        in.read(body);
        //创建响应头
        HttpHeaders headers = new HttpHeaders();
        //构建文件名称
        String fileName="sys_user_excel.xlsx";
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        //创建响应状态码
        HttpStatus ok = HttpStatus.OK;
        return new ResponseEntity<>(body,headers,ok);
    }


    @Operation(summary = "用户-批量导入")
    @Parameters(value = {
        @Parameter(name = "cover",description = "是否覆盖 1:覆盖 0:不覆盖")
    })
    @PostMapping(value = "/importBatchSysUser")
    public Result importBatchSysUser(@RequestPart(value = "file") MultipartFile file,
                                     @RequestParam(value = "cover",required = false,defaultValue = "0") Integer cover) throws IOException {
        //hutool工具类读取excel文件,解析里面的用户信息
        //文件输入流
        InputStream in = file.getInputStream();
        // hutool工具加载文件流
        ExcelReader reader = ExcelUtil.getReader(in);
        //读取文件内容
        List<UserExcel> userExcels = reader.readAll(UserExcel.class);
        if(CollectionUtils.isEmpty(userExcels)){
            throw new GlobalCommonException("文档内容为空");
        }
        sysUserService.addBatchSysUser(userExcels,cover);
        return Result.ok();
    }


    @Operation(summary = "用户-单个删除")
    @Parameters(value = {
        @Parameter(name = "userId",description = "用户ID",required = true)
    })
    @GetMapping(value = "/deleteSysUser")
    public Result deleteSysUser(@RequestParam(value = "userId") String userId){
        sysUserService.deleteSysUser(userId);
        return Result.ok();
    }

    @Operation(summary = "用户-批量删除")
    @PostMapping(value = "/deleteBatchSysUser")
    public Result deleteBatchSysUser(@RequestBody List<String> userIds){
        sysUserService.deleteBatchSysUser(userIds);
        return Result.ok();
    }

    @Operation(summary = "用户-密码重置")
    @PostMapping(value = "/batchResetSysUserPassword")
    public Result batchResetSysUserPassword(@RequestBody List<String> userIds){
        sysUserService.batchResetSysUserPassword(userIds);
        return Result.ok();
    }

    @Operation(summary = "用户-批量封禁账户")
    @PostMapping(value = "/batchDisSysUserStatus")
    public Result batchDisSysUserStatus(@RequestBody List<String> userIds){
        sysUserService.batchDisOrEnableSysUserStatus(userIds,0);
        return Result.ok();
    }

    @Operation(summary = "用户-批量解封账户")
    @PostMapping(value = "/batchEnableSysUserStatus")
    public Result batchEnableSysUserStatus(@RequestBody List<String> userIds){
        sysUserService.batchDisOrEnableSysUserStatus(userIds,1);
        return Result.ok();
    }


    @Operation(summary = "用户-更新")
    @PostMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.ok();
    }

    @Operation(summary = "用户-更新头像")
    @PostMapping(value = "/updateSysUserAvatar")
    public Result updateSysUserAvatar(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.ok();
    }


    @Operation(summary = "用户-详情")
    @Parameters(value = {
        @Parameter(name = "userId",description = "用户ID",required = true)
    })
    @GetMapping(value = "/getSysUser")
    public Result getSysUser(@RequestParam(value = "userId") String userId){
        return Result.ok(sysUserService.getSysUser(userId));
    }

    @Operation(summary = "用户-分页")
    @Parameters(value = {
            @Parameter(name = "pageNo",description = "当前页",required = true),
            @Parameter(name = "pageSize",description = "页容量",required = true),
            @Parameter(name = "tenantId",description = "租户ID"),
            @Parameter(name = "deptIds",description = "部门ID列表,格式为1,2,3,4..."),
            @Parameter(name = "roleId",description = "角色ID"),
            @Parameter(name = "username",description = "用户名"),
            @Parameter(name = "phone",description = "手机号"),
            @Parameter(name = "email",description = "邮箱"),
            @Parameter(name = "sex",description = "性别 1:男 0:女"),
            @Parameter(name = "isEnabled",description = "账号是否可用 1:可用 0:不可用"),
            @Parameter(name = "startTime",description = "开始时间"),
            @Parameter(name = "endTime",description = "结束时间")
    })
    @GetMapping(value = "/getSysUserPage")
    public Result getSysUserPage(@RequestParam(value = "pageNo") Integer pageNo,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "tenantId",required = false) String tenantId,
                                 @RequestParam(value = "deptIds",required = false) List<String> deptIds,
                                 @RequestParam(value = "roleId",required = false) String roleId,
                                 @RequestParam(value = "username",required = false) String username,
                                 @RequestParam(value = "phone",required = false) String phone,
                                 @RequestParam(value = "email",required = false) String email,
                                 @RequestParam(value = "sex",required = false) Integer sex,
                                 @RequestParam(value = "isEnabled",required = false) Integer isEnabled,
                                 @RequestParam(value = "startTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                 @RequestParam(value = "endTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime){
        return Result.ok(sysUserService.getSysUserPage(pageNo, pageSize, tenantId, deptIds, roleId, username, phone, email, sex, isEnabled, startTime, endTime));
    }

}
