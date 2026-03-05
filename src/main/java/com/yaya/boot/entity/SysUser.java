package com.yaya.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 用户表 -- sys_user
 */
@Data
@TableName(value = "`sys_user`")
public class SysUser implements Serializable {

    @Schema(description = "用户ID")
    @TableId(value = "user_id",type = IdType.ASSIGN_UUID)
    private String userId;

    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private String tenantId;

    @Schema(description = "部门ID")
    @TableField(value = "dept_id")
    private String deptId;

    @Schema(description = "角色ID")
    @TableField(value = "role_id")
    private String roleId;

    @Schema(description = "用户名")
    @TableField(value = "username")
    private String username;

    @Schema(description = "手机号")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;

    @Schema(description = "密码")
    @TableField(value = "password")
    private String password;

    @Schema(description = "性别 1:男 0:女")
    @TableField(value = "sex")
    private Integer sex;

    @Schema(description = "头像地址")
    @TableField(value = "avatar")
    private String avatar;

    @Schema(description = "账号是否可用 1:可用 0:不可用")
    @TableField(value = "is_enabled")
    private Integer isEnabled;

    @Schema(description = "过期时间")
    @TableField(value = "expired_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime expiredTime;

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
     * 部门详情
     */
    @TableField(exist = false)
    private SysDepartment sysDepartment;

    /**
     * 角色详情
     */
    @TableField(exist = false)
    private SysRole sysRole;

    /**
     * 租户详情
     */
    @TableField(exist = false)
    private SysTenant sysTenant;
}
