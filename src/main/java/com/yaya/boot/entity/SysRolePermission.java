package com.yaya.boot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关联表 - sys_role_permission
 */
@Data
@TableName(value = "sys_role_permission")
public class SysRolePermission implements Serializable {
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    @MppMultiId
    @TableField(value = "role_id")
    private String roleId;
    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    @MppMultiId
    @TableField(value = "permission_id")
    private String permissionId;
}
