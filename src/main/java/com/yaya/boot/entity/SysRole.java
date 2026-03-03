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
 * 角色表 - sys_role
 */
@Data
@TableName(value = "sys_role")
public class SysRole implements Serializable {

    @Schema(description = "角色ID")
    @TableId(value = "role_id",type = IdType.ASSIGN_UUID)
    private String roleId;

    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private String tenantId;

    @Schema(description = "角色名称")
    @TableField(value = "role_name")
    private String roleName;

    @Schema(description = "角色符号")
    @TableField(value = "role_symbol")
    private String roleSymbol;

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

    /**
     * 租户详情
     */
    @TableField(exist = false)
    private SysTenant sysTenant;
}
