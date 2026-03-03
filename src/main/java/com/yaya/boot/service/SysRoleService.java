package com.yaya.boot.service;

import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysRole;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色管理
 */
public interface SysRoleService {
    /**
     * 添加角色
     * @param sysRole 角色参数
     */
    void addSysRole(SysRole sysRole);

    /**
     * 删除角色
     * @param roleId 角色ID
     */
    void deleteSysRole(String roleId);

    /**
     * @param roleIds 角色id列表
     */
    void batchDeleteSysRole(List<String> roleIds);
    /**
     * 更新角色
     */
    void updateSysRole(SysRole sysRole);

    /**
     * @param roleId 角色ID
     * @return 角色详情
     */
    SysRole getSysRoleByRoleId(String roleId);

    /**
     * @param tenantId 租户ID
     * @return 租户下的角色列表
     */
    List<SysRole> getSysRoleListByTenantId(String tenantId);

    /**
     * @param pageNo        当前页
     * @param pageSize      页容量
     * @param roleName      角色名称
     * @param status        租户状态
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @return 角色分页
     */
    Page getSysRolePage(Integer pageNo, Integer pageSize, String roleName,String roleSymbol,Integer status, LocalDateTime startTime, LocalDateTime endTime,String tenantId);
}
