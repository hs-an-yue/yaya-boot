package com.yaya.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 日志表 - sys_operation_log
 */
@Data
@TableName(value = "sys_operation_log")
public class SysOperationLog implements Serializable {

    @Schema(description = "日志ID")
    @TableId(value = "operation_log_id",type = IdType.ASSIGN_UUID)
    private String operationLogId;

    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private String tenantId;

    @Schema(description = "日志类型 1:登陆日志 2:其它操作日志")
    @TableField(value = "log_type")
    private Integer logType;

    @Schema(description = "操作用户ID")
    @TableField(value = "operation_user_id")
    private String operationUserId;

    @Schema(description = "请求地址")
    @TableField(value = "operation_url")
    private String operationUrl;

    @Schema(description = "请求参数")
    @TableField(value = "operation_params")
    private String operationParams;

    @Schema(description = "客户端IP")
    @TableField(value = "ip")
    private String ip;

    @Schema(description = "客户端操作系统")
    @TableField(value = "os")
    private String os;

    @Schema(description = "客户端浏览器")
    @TableField(value = "browser")
    private String browser;

    @Schema(description = "客户端定位地址")
    @TableField(value = "address")
    private String address;

    @Schema(description = "日志链路ID")
    @TableField(value = "track_id")
    private String trackId;

    @Schema(description = "请求状态 1:成功 0:失败")
    @TableField(value = "`status`")
    private Integer status;

    @Schema(description = "错误堆栈信息")
    @TableField(value = "error_msg")
    private String errorMsg;

    @Schema(description = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "operation_time")
    private LocalDateTime operationTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 租户
     */
    @TableField(exist = false)
    private SysTenant sysTenant;
    /**
     * 操作人
     */
    @TableField(exist = false)
    private SysUser operationUser;
}
