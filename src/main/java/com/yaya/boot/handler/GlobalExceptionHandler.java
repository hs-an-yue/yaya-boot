package com.yaya.boot.handler;

import com.yaya.boot.dto.Result;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.service.SysOperationLogService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 统一异常处理
 */
@Hidden
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Resource
    private SysOperationLogService sysOperationLogService;
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;


    /**
     * 统一异常处理方法
     */
    @ExceptionHandler(value = {Exception.class})
    public Result commonProcessException(Exception e){
        log.error("统一异常处理器:",e);
        if(e instanceof GlobalCommonException ex){
            return Result.error(ex.getCode(),ex.getMessage());
        }else if(e instanceof MaxUploadSizeExceededException ex){
            return Result.error(ex.getMessage()+"上传支持的单个文件大小:"+maxFileSize);
        } else if (e instanceof AuthorizationDeniedException ex) {
            return Result.error(ex.getMessage());
        }
        return Result.error("系统异常,请联系管理员");
    }

}
