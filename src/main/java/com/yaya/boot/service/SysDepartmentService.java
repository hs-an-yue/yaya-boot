package com.yaya.boot.service;

import com.yaya.boot.entity.SysDepartment;

import java.util.List;

/**
 * 部门管理
 */
public interface SysDepartmentService {
    /**
     * 添加
     * @param sysDepartment 参数
     */
    void addSysDepartment(SysDepartment sysDepartment);

    /**
     * 删除
     * @param deptId 部门ID
     */
    void deleteSysDepartment(String deptId);

    /**
     * 批量删除
     */
    void batchDeleteSysDepartment(List<String> deptIds);

    /**
     * 更新
     * @param sysDepartment 参数
     */
    void updateSysDepartment(SysDepartment sysDepartment);

    /**
     * @param tenantId 租户ID
     * @param status   是否禁用 1:是 0:否
     * @param deptName   部门名称
     * @return 树状
     */
    List<SysDepartment> getSysDepartmentTree(String tenantId,Integer status,String deptName);

    /**
     * 指定部门ID下的子部门列表
     */
    List<SysDepartment> getSubSysDepartmentByDeptId(String tenantId, Integer status,String deptId);
}
