package com.yaya.boot.service;

import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysOperationLog;

import java.time.LocalDateTime;

/**
 * 日志管理
 */
public interface SysOperationLogService {

    /**
     * @param pageNo        当前页
     * @param pageSize      页容量
     * @param tenantId      租户ID
     * @param logType       日志类型 1:登陆日志 2:其它操作日志
     * @param status        日志状态 1:成功 0:失败
     * @param trackId       日志追踪id
     * @param username      用户名
     * @param phone         账号
     * @return 日志列表
     */
    Page getSysOperationLogPage(Integer pageNo, Integer pageSize, String tenantId, Integer logType, String trackId, String username, String phone, String operationParams, String ip, String address, LocalDateTime startTime,LocalDateTime endTime,Integer status);

    /**
     * 保存日志
     * @param sysOperationLog 日志信息
     */
    void saveSysOperationLog(SysOperationLog sysOperationLog);
}
