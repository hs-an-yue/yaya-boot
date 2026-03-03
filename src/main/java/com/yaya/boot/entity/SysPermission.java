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
 * 权限表 - sys_permission
 */
@Data
@TableName(value = "sys_permission")
public class SysPermission implements Serializable {

    @Schema(description = "权限ID")
    @TableId(value = "permission_id",type = IdType.ASSIGN_UUID)
    private String permissionId;

    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private String tenantId;

    @Schema(description = "权限名称")
    @TableField(value = "permission_name")
    private String permissionName;

    @Schema(description = "权限符号")
    @TableField(value = "permission_symbol")
    private String permissionSymbol;

    @Schema(description = "是否禁用 1:是 0:否")
    @TableField(value = "`status`")
    private Integer status;

    @Schema(description = "是否删除 1:删除 0:未删除")
    @TableField(value = "is_delete")
    private Integer isDelete;

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
}
