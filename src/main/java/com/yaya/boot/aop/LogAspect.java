package com.yaya.boot.aop;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.jthinking.common.util.ip.IPInfo;
import com.jthinking.common.util.ip.IPInfoUtils;
import com.yaya.boot.entity.SysOperationLog;
import com.yaya.boot.service.SysOperationLogService;
import com.yaya.boot.utils.SecurityUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

/**
 * 统一日志处理
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger logger= LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private SysOperationLogService sysOperationLogService;


    /**
     * 统一切入点表达式
     * 给 controller 包下的所有类的所有方法,添加切面代理,动态织入日志
     */
    @Pointcut(value = "execution(* com.yaya.boot.controller.*.*(..))")
    public void log(){}

    /**
     * 环绕通知
     */
    @Around(value = "log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //日志链路追踪(向日志中添加唯一ID,这里使用UUID)
        String trackId = UUID.randomUUID().toString();
        MDC.put("trackId",trackId);
        //租户ID
        String tenantId = null;
        //用户ID
        String userId = null;
        //日志类型
        int logType = 1;//1: 登陆日志 2:其它
        //请求地址
        String uri = null;
        //请求参数
        String params = null;
        //请求IP
        String ip = null;
        //请求操作系统
        String os = null;
        //请求浏览器
        String browser = null;
        //请求状态
        int status = 1;
        //提示消息
        String errorMsg = null;
        try {
            //获取HttpServletRequest对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            tenantId = SecurityUtils.getTenantId()!=null?SecurityUtils.getTenantId():null;
            userId = SecurityUtils.getUserId()!=null?SecurityUtils.getUserId():null;
            uri = request.getRequestURI();
            ip =  StringUtils.isNotEmpty(request.getHeader("X-Real-IP"))?request.getHeader("X-Real-IP"):request.getRemoteAddr();
            String header = request.getHeader("User-Agent");
            UserAgent agent = UserAgentUtil.parse(header);
            browser = agent.getBrowser().toString();
            os = agent.getOs().toString();
            Object[] args = joinPoint.getArgs();
            if(ArrayUtil.isNotEmpty(args) && ArrayUtil.isNotEmpty(args[0])){
                params = JSONUtil.toJsonStr(args);
            }
            return joinPoint.proceed();
        }catch (Throwable e){
            status = 0;
            errorMsg = e.getMessage();
            throw e;
        }finally {
            SysOperationLog sysOperationLog = new SysOperationLog();
            sysOperationLog.setTrackId(trackId);
            sysOperationLog.setTenantId(tenantId);
            sysOperationLog.setOperationUserId(userId);
            if(StringUtils.isNotEmpty(uri) && !uri.contains("/login")){
                logType = 2;
            }
            sysOperationLog.setLogType(logType);
            sysOperationLog.setIp(ip);
            sysOperationLog.setOperationUrl(uri);
            sysOperationLog.setOs(os);
            sysOperationLog.setBrowser(browser);
            IPInfo ipInfo = IPInfoUtils.getIpInfo(ip);
            sysOperationLog.setAddress(ipInfo.getAddress());
            sysOperationLog.setStatus(status);
            sysOperationLog.setOperationParams(params);
            sysOperationLog.setErrorMsg(errorMsg);
            logger.info("YaYa Boot Log:{}",sysOperationLog);
            sysOperationLogService.saveSysOperationLog(sysOperationLog);
        }
    }
}
