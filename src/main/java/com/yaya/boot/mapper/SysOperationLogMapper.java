package com.yaya.boot.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.yaya.boot.entity.SysOperationLog;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SysOperationLogMapper extends MppBaseMapper<SysOperationLog> {
    /**
     * @param tenantId      租户ID
     * @param logType       日志类型 1:登陆日志 2:其它操作日志
     * @param status        日志状态 1:成功 0:失败
     * @param trackId       日志追踪id
     * @param username      用户名
     * @param phone         账号
     * @return 日志列表
     */
    List<SysOperationLog> getSysOperationLogList(@Param("tenantId") String tenantId,
                                                 @Param("logType") Integer logType,
                                                 @Param("trackId") String trackId,
                                                 @Param("username") String username,
                                                 @Param("phone") String phone,
                                                 @Param("operationParams") String operationParams,
                                                 @Param("ip") String ip,
                                                 @Param("address") String address,
                                                 @Param("startTime") LocalDateTime startTime,
                                                 @Param("endTime") LocalDateTime endTime,
                                                 @Param("status") Integer status);
}
