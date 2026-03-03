package com.yaya.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaya.boot.entity.SysDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {

    /**
     * 部门树状列表
     */
    List<SysDepartment> getSysDepartmentTree(@Param("tenantId") String tenantId,
                                             @Param("status") Integer status,
                                             @Param("deptName") String deptName);
    /**
     * 通过父ID查询子部门列表
     */
    List<SysDepartment> getSubSysDepartmentTree(@Param("tenantId") String tenantId,@Param("parentId") String parentId,@Param("status") Integer status);

    /**
     * 通过部门ID查询当前部门以及当前部门下的所有后代部门列表
     */
    List<SysDepartment> getSpecifiedDeptIdAndDeptInfoAndDescendantsDeptListInfo(@Param("deptId") String deptId);
}
