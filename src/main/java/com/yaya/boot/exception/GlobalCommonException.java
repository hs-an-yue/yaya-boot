package com.yaya.boot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
@Setter
@Getter
public class GlobalCommonException extends RuntimeException{

    private int code; //状态码

    public GlobalCommonException(int code,String message) {
        super(message);
        this.code = code;
    }

    public GlobalCommonException(String message) {
        super(message);
        this.code = -1;
    }
}
