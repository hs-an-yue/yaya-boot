package com.yaya.boot.utils;

import com.yaya.boot.entity.SysUser;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.security.LoginUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * 获取登陆信息用户详情
 */
public class SecurityUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    /**
     * 获取认证信息
     */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    /**
     * 获取当前登录SpringSecurity用户信息
     */
    public static LoginUserDetails getLoginUser(){
        try {
            Authentication authentication = getAuthentication();
            if(authentication.isAuthenticated()){
                Object principal = authentication.getPrincipal();
                if(Objects.nonNull(principal)){
                    if(principal instanceof LoginUserDetails){
                        return (LoginUserDetails) principal;
                    }
                }
            }
            return null;
        }catch (Exception e){
            LOGGER.error("getLoginUser",e);
            throw new GlobalCommonException("未认证");
        }
    }

    /**
     * 获取当前登录用户信息
     */
    public static SysUser getUser(){
        LoginUserDetails user = getLoginUser();
        if(null!=user){
            return user.getSysUser();
        }
        return null;
    }

    /**
     * 获取当前登录用户的ID
     */
    public static String getUserId(){
        SysUser user = getUser();
        if(null!=user){
            return user.getUserId();
        }
        return null;
    }
    /**
     * 获取当前登录用户的名称
     */
    public static String getUsername(){
        SysUser user = getUser();
        if(null!=user){
            return user.getUsername();
        }
        return null;
    }

    /**
     * 当前用户的账号(手机号)
     */
    public static String getPhone(){
        SysUser user = getUser();
        if(null!=user){
            return user.getPhone();
        }
        return null;
    }


    /**
     * 获取租户ID
     */
    public static String getTenantId(){
        SysUser user = getUser();
        if(null!=user){
            return user.getTenantId();
        }
        return null;
    }

    /**
     * 判断是不是管理员
     */
    public static Boolean isAdmin(){
        String phone = getPhone();
        if(StringUtils.isNotEmpty(phone)){
            //忽略大小写
            return phone.equalsIgnoreCase("admin");
        }
        return false;
    }

    /**
     * 判断是不是运营管理员
     */
    public static Boolean isOperation(){
        String phone = getPhone();
        if(StringUtils.isNotEmpty(phone)){
            //忽略大小写
            return phone.equalsIgnoreCase("operation");
        }
        return false;
    }

    /**
     * 判断是不是管理员或者运营管理员
     */
    public static Boolean isAdminOrOperation(){
        return isAdmin() || isOperation();
    }
}
