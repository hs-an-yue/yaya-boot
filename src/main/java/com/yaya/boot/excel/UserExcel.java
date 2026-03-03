package com.yaya.boot.excel;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户批量导入模板配套类
 */
@Data
public class UserExcel {
    /**
     * 租户ID
     */
    @Alias(value = "租户ID")
    private String tenantId;
    /**
     * 部门ID
     */
    @Alias(value = "部门ID")
    private String deptId;
    /**
     * 角色ID
     */
    @Alias(value = "角色ID")
    private String roleId;
    /**
     * 用户名
     */
    @Alias(value = "用户名称")
    private String username;
    /**
     * 手机号
     */
    @Alias(value = "手机号(账号)")
    private String phone;
    /**
     * 邮箱
     */
    @Alias(value = "邮箱")
    private String email;
    /**
     * 性别 1:男 0:女
     */
    @Alias(value = "性别")
    private Integer sex;
    /**
     * 过期时间
     */
    @Alias(value = "账号过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime expiredTime;
    /**
     * 备注
     */
    @Alias(value = "备注")
    private String remark;
}
