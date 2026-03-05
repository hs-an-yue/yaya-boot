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
 * 租户表 - sys_tenant
 */
@Data
@TableName(value = "sys_tenant")
public class SysTenant implements Serializable {

    @Schema(description = "租户ID")
    @TableId(value = "tenant_id",type = IdType.ASSIGN_UUID)
    private String tenantId;

    @Schema(description = "租户名称")
    @TableField(value = "tenant_name")
    private String tenantName;

    @Schema(description = "是否禁用 1:是 0:否")
    @TableField(value = "`status`")
    private Integer status;

    @Schema(description = "备注")
    @TableField(value = "remark")
    private String remark;

    @Schema(description = "创建人ID")
    @TableField(value = "create_by_id")
    private String createById;

    @Schema(description = "更新人ID")
    @TableField(value = "update_by_id")
    private String updateById;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(exist = false)
    private SysUser createUser;
    /**
     * 更新人
     */
    @TableField(exist = false)
    private SysUser updateUser;
}
