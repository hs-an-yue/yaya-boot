package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.service.SysOperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 日志管理
 */
@Tag(name = "日志管理")
@RestController
public class SysOperationLogController {
    @Resource
    private SysOperationLogService sysOperationLogService;

    @Operation(summary = "日志-分页")
    @Parameters(value = {
            @Parameter(name = "pageNo",description = "当前页",required = true),
            @Parameter(name = "pageSize",description = "页容量",required = true),
            @Parameter(name = "tenantId",description = "租户ID"),
            @Parameter(name = "logType",description = "日志类型 1:登陆日志 2:其它操作日志"),
            @Parameter(name = "status",description = "日志状态 1:成功 0:失败"),
            @Parameter(name = "trackId",description = "日志追踪id"),
            @Parameter(name = "username",description = "用户名"),
            @Parameter(name = "phone",description = "账号(手机号)"),
            @Parameter(name = "operationParams",description = "参数"),
            @Parameter(name = "ip",description = "IP地址")
    })
    @GetMapping(value = "/getSysOperationLogPage")
    public Result getSysOperationLogPage(@RequestParam(value = "pageNo") Integer pageNo,
                                         @RequestParam(value = "pageSize") Integer pageSize,
                                         @RequestParam(value = "tenantId",required = false) String tenantId,
                                         @RequestParam(value = "logType",required = false) Integer logType,
                                         @RequestParam(value = "status",required = false) Integer status,
                                         @RequestParam(value = "trackId",required = false) String trackId,
                                         @RequestParam(value = "username",required = false) String username,
                                         @RequestParam(value = "phone",required = false) String phone,
                                         @RequestParam(value = "operationParams",required = false) String operationParams,
                                         @RequestParam(value = "ip",required = false) String ip,
                                         @RequestParam(value = "address",required = false) String address,
                                         @RequestParam(value = "startTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                         @RequestParam(value = "endTime",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime){
        return Result.ok(sysOperationLogService.getSysOperationLogPage(pageNo,pageSize,tenantId,logType,trackId,username,phone,operationParams,ip,address,startTime,endTime,status));
    }
}
