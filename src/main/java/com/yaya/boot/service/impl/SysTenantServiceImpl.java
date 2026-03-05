package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysDepartment;
import com.yaya.boot.entity.SysRole;
import com.yaya.boot.entity.SysTenant;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.mapper.SysDepartmentMapper;
import com.yaya.boot.mapper.SysRoleMapper;
import com.yaya.boot.mapper.SysTenantMapper;
import com.yaya.boot.mapper.SysUserMapper;
import com.yaya.boot.service.SysTenantService;
import com.yaya.boot.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class SysTenantServiceImpl implements SysTenantService {

    @Resource
    private SysTenantMapper sysTenantMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysDepartmentMapper sysDepartmentMapper;
    @Resource
    private SysRoleMapper  sysRoleMapper;

    @Override
    public void addSysTenant(SysTenant sysTenant) {
        sysTenant.setCreateById(SecurityUtils.getUserId());
        sysTenant.setUpdateById(SecurityUtils.getUserId());
        sysTenantMapper.insert(sysTenant);
    }

    @Override
    public void deleteSysTenant(String tenantId) {
        //关联角色不能删除
        List<SysRole> sysRoles = sysRoleMapper.selectList(new QueryWrapper<SysRole>().eq("tenant_id", tenantId));
        if(CollectionUtils.isNotEmpty(sysRoles)) {
            throw new GlobalCommonException("租户下存在有效角色,不能删除");
        }
        //关联部门不能删除
        List<SysDepartment> departments = sysDepartmentMapper.selectList(new QueryWrapper<SysDepartment>().eq("tenant_id", tenantId));
        if(CollectionUtils.isNotEmpty(departments)) {
            throw new GlobalCommonException("租户下存在有效部门,不能删除");
        }
        //关联用户不能删除
        List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>().eq("tenant_id", tenantId));
        if(CollectionUtils.isNotEmpty(sysUsers)) {
            throw new GlobalCommonException("租户下存在有效用户,不能删除");
        }
        //删除
        sysTenantMapper.deleteById(tenantId);
    }

    @Override
    public void batchDeleteSysTenant(List<String> tenantIds) {
        if(CollectionUtils.isEmpty(tenantIds)) {
            throw new GlobalCommonException("请选择要删除的租户");
        }
        tenantIds.forEach(tenantId -> {
            //关联角色不能删除
            List<SysRole> sysRoles = sysRoleMapper.selectList(new QueryWrapper<SysRole>().eq("tenant_id", tenantId));
            if(CollectionUtils.isNotEmpty(sysRoles)) {
                throw new GlobalCommonException("租户下存在有效角色,不能删除");
            }
            //关联部门不能删除
            List<SysDepartment> departments = sysDepartmentMapper.selectList(new QueryWrapper<SysDepartment>().eq("tenant_id", tenantId));
            if(CollectionUtils.isNotEmpty(departments)) {
                throw new GlobalCommonException("租户下存在有效部门,不能删除");
            }
            //关联用户不能删除
            List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>().eq("tenant_id", tenantId));
            if(CollectionUtils.isNotEmpty(sysUsers)) {
                throw new GlobalCommonException("租户下存在有效用户,不能删除");
            }
            //删除
            sysTenantMapper.deleteById(tenantId);
        });
    }

    @Override
    public void updateSysTenant(SysTenant sysTenant) {
        sysTenant.setUpdateById(SecurityUtils.getUserId());
        sysTenantMapper.updateById(sysTenant);
    }

    @Override
    public SysTenant getSysTenantByTenantId(String tenantId) {
        return sysTenantMapper.selectById(tenantId);
    }

    @Override
    public Page getSysTenantPage(Integer pageNo,Integer pageSize,String tenantName, Integer status, LocalDateTime startTime, LocalDateTime endTime) {
        PageHelper.startPage(pageNo,pageSize);
        List<SysTenant> sysTenants = sysTenantMapper.selectList(new QueryWrapper<SysTenant>()
                .like(StringUtils.isNotEmpty(tenantName), "tenant_name", tenantName)
                .eq(status != null, "`status`", status)
                .ge(startTime != null, "create_time", startTime)
                .le(endTime != null, "create_time", endTime)
                .orderByAsc("create_time")
        );
        if(CollectionUtils.isNotEmpty(sysTenants)){
            sysTenants.forEach(sysTenant -> {
                String createById = sysTenant.getCreateById();
                String updateById = sysTenant.getUpdateById();
                SysUser createUser = sysUserMapper.selectById(createById);
                if(createUser != null){
                    createUser.setPassword(null);
                    sysTenant.setCreateUser(createUser);
                }
                SysUser updateUser = sysUserMapper.selectById(updateById);
                if(updateUser != null){
                    updateUser.setPassword(null);
                    sysTenant.setUpdateUser(updateUser);
                }
            });
        }
        PageInfo<SysTenant> pageInfo = new PageInfo<>(sysTenants);
        Page page = new Page();
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setHasNext(pageInfo.isHasNextPage());
        page.setHasPre(pageInfo.isHasPreviousPage());
        page.setCount(pageInfo.getTotal());
        page.setList(pageInfo.getList());
        return page;
    }

    @Override
    public List<SysTenant> getSysTenantAll() {
        return sysTenantMapper.selectList(new QueryWrapper<SysTenant>().eq("status",0).orderByDesc("update_time"));
    }
}
