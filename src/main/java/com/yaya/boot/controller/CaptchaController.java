package com.yaya.boot.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.yaya.boot.config.YaYaConfig;
import com.yaya.boot.dto.Result;
import com.yaya.boot.utils.RedisClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码管理
 */
@Tag(name = "验证码管理")
@RestController
public class CaptchaController {

    @Resource
    private RedisClient redisClient;
    @Resource
    private YaYaConfig yaYaConfig;

    /**
     * 验证码生成
     */
    @Operation(summary = "验证码生成")
    @GetMapping(value = "/captchaImage")
    public Result captchaImage() throws IOException {
        //创建CircleCaptcha对象(使用hutool工具进行验证码生成)
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(120, 40, 1, 15);
        //获取验证码内容
        String code = captcha.getCode();
        /*
         * 将生成的验证码保存到redis数据库中,redis的key自定义,我这里使用hutool工具中的雪花算法生成,你也可以使用其它方式,如 uuid等
         */
        String uuid = IdUtil.getSnowflakeNextIdStr();
        //给redis的key设置一个前缀，方便后续清空验证码
        String key = "captcha:"+uuid;
        //将验证码保存到redis中,以便于后面进行登录校验
        redisClient.set(key,code,yaYaConfig.getCaptchaTimeout());//带过期时间,过期时间在yml配置文件中进行配置
        /*
         * 将生成的验证码图片进行base64处理
         */
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        //生成图片的Base64编码
        ImageIO.write(captcha.getImage(), "png", os);
        byte[] bytes = os.toByteArray();
        //base64加密
        String encode = Base64.encode(bytes);
        Map<String,Object> map = new HashMap<>();
        map.put("img","data:image/png;base64,"+encode);//采用base64加密的方式向前端传递验证码图片
        map.put("uuid",uuid);//给前端(客户端)返回的redis中验证码的key
        map.put("code",code);//给前端(客户端)返回的验证码
        return Result.ok(map);
    }
}
