package com.yaya.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置文件
 * 作用: 封装配置文件(application-*.yml)中的配置信息
 */
@Component
@Data
@ConfigurationProperties(prefix = "yaya") //配置文件中的配置前缀
public class YaYaConfig {

    /**
     * 版本
     */
    private String version;
    /**
     * 文件上传地址前缀: 方便SpringSecurity做权限控制
     * 文件类型包括
     */
    private String prefix;
    /**
     * 验证码过期时间 单位毫秒
     */
    private Long captchaTimeout;
    /**
     * token过期时间 单位毫秒
     */
    private Long tokenTimeout;
}
