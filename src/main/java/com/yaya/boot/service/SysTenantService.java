package com.yaya.boot.service;

import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysTenant;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租户管理
 */
public interface SysTenantService {

    /**
     * 添加租户
     * @param sysTenant 租户参数
     */
    void addSysTenant(SysTenant sysTenant);

    /**
     * 删除租户
     * @param tenantId 租户ID
     */
    void deleteSysTenant(String tenantId);

    /*
     * 批量删除
     */
    void batchDeleteSysTenant(List<String>  tenantIds);

    /**
     * 更新租户
     */
    void updateSysTenant(SysTenant sysTenant);

    /**
     * @param tenantId 租户ID
     * @return 租户详情
     */
    SysTenant getSysTenantByTenantId(String tenantId);

    /**
     * @param pageNo        当前页
     * @param pageSize      页容量
     * @param tenantName    租户名称
     * @param status        租户状态
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @return 租户分页
     */
    Page getSysTenantPage(Integer pageNo,Integer pageSize,String tenantName, Integer status, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * @return 租户列表
     */
    List<SysTenant> getSysTenantAll();
}
