package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaya.boot.entity.SysDepartment;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.mapper.SysDepartmentMapper;
import com.yaya.boot.mapper.SysUserMapper;
import com.yaya.boot.service.SysDepartmentService;
import com.yaya.boot.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {
    @Resource
    private SysDepartmentMapper sysDepartmentMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void addSysDepartment(SysDepartment sysDepartment) {
        String tenantId = sysDepartment.getTenantId();
        String parentId = sysDepartment.getParentId();
        String deptName = sysDepartment.getDeptName();
        if(tenantId == null){
            throw new GlobalCommonException("租户不能为空");
        }
        if(StringUtils.isEmpty(deptName)){
            throw new GlobalCommonException("部门名称不能为空");
        }
        //获取编号最大值
        List<Map<String, Object>> maps = sysDepartmentMapper.selectMaps(new QueryWrapper<SysDepartment>()
                .select("MAX(order_num) AS order_num")
                .eq("tenant_id",tenantId)
                .eq("is_delete",0)
                .eq("parent_id",parentId==null || parentId.equals("0")?0:parentId)
        );
        if(!maps.isEmpty() && maps.getFirst()!=null && maps.getFirst().get("order_num")!=null){
            int orderNum = Integer.parseInt(maps.getFirst().get("order_num").toString());
            sysDepartment.setOrderNum(orderNum+1);
        }else {
            sysDepartment.setOrderNum(1);
        }
        if(parentId == null || parentId.equals("0")){ //顶级部门
            sysDepartment.setParentId("0");
            sysDepartment.setAncestors("0");//祖宗部门ID=父部门祖宗+当前子部门的parentId,顶级部门为0
        }else { //子级部门
            SysDepartment parentDept = sysDepartmentMapper.selectById(parentId);
            if(parentDept != null){
                String ancestors = parentDept.getAncestors();
                sysDepartment.setAncestors(ancestors+","+parentId);
            }
        }
        sysDepartment.setCreateById(SecurityUtils.getUserId());
        sysDepartment.setUpdateById(SecurityUtils.getUserId());
        sysDepartmentMapper.insert(sysDepartment);
    }

    @Override
    public void deleteSysDepartment(String deptId) {
        //判断是否存在未删除的子部门
        List<SysDepartment> subDepartments = sysDepartmentMapper.selectList(new QueryWrapper<SysDepartment>()
                .eq("parent_id", deptId)
                .eq("is_delete", 0)//未删除
        );
        if(CollectionUtils.isNotEmpty(subDepartments)){
            throw new GlobalCommonException("部门下存在有效的子部门,不能删除");
        }

        //判断当前部门以及所有子部门下是否存在用户
        List<SysDepartment> deptListInfo = sysDepartmentMapper.getSpecifiedDeptIdAndDeptInfoAndDescendantsDeptListInfo(deptId);
        if(CollectionUtils.isNotEmpty(deptListInfo)){
            //当前部门ID以及当前部门ID下的后代部门ID
            List<String> deptIdList = deptListInfo.stream().map(SysDepartment::getDeptId).toList();
            if(CollectionUtils.isNotEmpty(deptIdList)){
                List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                        .eq("is_delete", 0)
                        .in("dept_id", deptIdList)
                );
                //部门下存在有效用户,不能删除
                if(CollectionUtils.isNotEmpty(sysUsers)){
                    throw new GlobalCommonException("部门下存在有效用户不能删除");
                }
            }
        }

        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.setDeptId(deptId);
        sysDepartment.setIsDelete(1);//删除
        sysDepartment.setUpdateById(SecurityUtils.getUserId());//更新人
        sysDepartmentMapper.updateById(sysDepartment);
    }

    @Override
    public void batchDeleteSysDepartment(List<String> deptIds) {
        //判断是否为空
        if(CollectionUtils.isEmpty(deptIds)){
            throw new GlobalCommonException("请选择要删除的部门");
        }
        //判断是否存在有效子节点
        deptIds.forEach(deptId -> {
            List<SysDepartment> departments = sysDepartmentMapper.selectList(new QueryWrapper<SysDepartment>()
                    .eq("parent_id", deptId)
                    .eq("is_delete", 0)//有效子节点
            );
            if(CollectionUtils.isNotEmpty(departments)){
                throw new GlobalCommonException("部门下存在未删除的子部门");
            }

            //判断当前部门以及所有子部门下是否存在用户
            List<SysDepartment> deptListInfo = sysDepartmentMapper.getSpecifiedDeptIdAndDeptInfoAndDescendantsDeptListInfo(deptId);
            if(CollectionUtils.isNotEmpty(deptListInfo)){
                //当前部门ID以及当前部门ID下的后代部门ID
                List<String> deptIdList = deptListInfo.stream().map(SysDepartment::getDeptId).toList();
                if(CollectionUtils.isNotEmpty(deptIdList)){
                    List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                            .eq("is_delete", 0)
                            .in("dept_id", deptIdList)
                    );
                    //部门下存在有效用户,不能删除
                    if(CollectionUtils.isNotEmpty(sysUsers)){
                        throw new GlobalCommonException("部门下存在有效用户不能删除");
                    }
                }
            }
            //删除
            SysDepartment sysDepartment = new SysDepartment();
            sysDepartment.setDeptId(deptId);
            sysDepartment.setIsDelete(1);//删除
            sysDepartment.setUpdateById(SecurityUtils.getUserId());//更新人
            sysDepartmentMapper.updateById(sysDepartment);
        });

    }

    @Override
    public void updateSysDepartment(SysDepartment sysDepartment) {
        sysDepartment.setUpdateById(SecurityUtils.getUserId());//更新人
        sysDepartmentMapper.updateById(sysDepartment);
    }

    @Override
    public List<SysDepartment> getSysDepartmentTree(String tenantId, Integer status,String deptName) {
        return sysDepartmentMapper.getSysDepartmentTree(tenantId,status,deptName);
    }

    @Override
    public List<SysDepartment> getSubSysDepartmentByDeptId(String tenantId, Integer status,String deptId) {
        return sysDepartmentMapper.getSubSysDepartmentTree(tenantId,deptId,status);
    }
}
