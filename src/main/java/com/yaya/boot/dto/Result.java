package com.yaya.boot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.slf4j.MDC;

import java.time.LocalDateTime;

/**
 * 统一响应结果
 */
@Data
public class Result {
    /**
     * 统一响应提示状态码
     */
    @Schema(description = "响应状态码 0:成功 -1:系统异常")
    private int code= 0;
    /**
     * 统一响应提示消息
     */
    @Schema(description = "响应提示消息 0:成功 其它:自定义消息")
    private String msg="成功";
    /**
     * 统一响应结果
     */
    @Schema(description = "响应数据")
    private Object data;
    /**
     * 服务器响应时间戳
     */
    @Schema(description = "响应时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * MDC链路追踪码
     */
    @Schema(description = "日志链路追踪码")
    private String trackId = MDC.get("trackId");

    public static Result ok(){
        return new Result();
    }
    public static Result ok(Object data){
        return new Result(data);
    }

    public static Result error(int code,String msg){
        return new Result(code,msg);
    }

    public static Result error(String msg){
        return new Result(-1,msg);
    }

    public Result() {
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
