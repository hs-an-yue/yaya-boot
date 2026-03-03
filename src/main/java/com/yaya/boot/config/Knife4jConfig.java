package com.yaya.boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 */
@Configuration
public class Knife4jConfig {

    /**
     * Knife4j基础信息配置
     */
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("YaYa-Boot 在线API文档")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("YaYa-Boot")
                                .email("hd1611756908@163.com")
                                .url("https://hs-an-yue.github.io"))
                        .termsOfService("https://hs-an-yue.github.io")
                        .description("YaYa-Boot是一款结合前端模板YaYa-Layui-Admin-Plus实现的精简版SaaS系统"));
    }

}
