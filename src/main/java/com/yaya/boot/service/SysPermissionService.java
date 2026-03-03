package com.yaya.boot.service;

import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysPermission;

import java.time.LocalDateTime;

/**
 * 权限管理
 */
public interface SysPermissionService {
    /**
     * 添加
     * @param sysPermission 参数
     */
    void addSysPermission(SysPermission sysPermission);

    /**
     * 删除
     * @param permissionId ID
     */
    void deleteSysPermission(String permissionId);

    /**
     * 更新
     */
    void updateSysPermission(SysPermission sysPermission);

    /**
     * @param permissionId ID
     * @return 详情
     */
    SysPermission getSysPermissionByPermissionId(String permissionId);

    /**
     * @param pageNo            当前页
     * @param pageSize          页容量
     * @param permissionName    权限名称
     * @param permissionSymbol  权限符号
     * @param status            租户状态
     * @param startTime         开始时间
     * @param endTime           结束时间
     * @return 分页
     */
    Page getSysPermissionPage(Integer pageNo, Integer pageSize, String permissionName, String permissionSymbol, Integer status, LocalDateTime startTime, LocalDateTime endTime);

}
