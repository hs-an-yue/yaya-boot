package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysRole;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.mapper.SysRoleMapper;
import com.yaya.boot.mapper.SysTenantMapper;
import com.yaya.boot.mapper.SysUserMapper;
import com.yaya.boot.service.SysRoleService;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysTenantMapper sysTenantMapper;

    @Override
    public void addSysRole(SysRole sysRole) {
        sysRole.setCreateById(SecurityUtils.getUserId());
        sysRole.setUpdateById(SecurityUtils.getUserId());
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void deleteSysRole(String roleId) {
        //是否被用户使用
        List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                .eq("role_id", roleId)
                .eq("is_delete", 0)
        );
        if(CollectionUtils.isNotEmpty(sysUsers)){
            throw new GlobalCommonException("当前角色被用户引用,不能删除");
        }
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(roleId);
        sysRole.setIsDelete(1);//删除
        sysRole.setUpdateById(SecurityUtils.getUserId());
        sysRoleMapper.updateById(sysRole);
    }

    @Override
    public void batchDeleteSysRole(List<String> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)){
            throw new GlobalCommonException("请选择要删除的角色");
        }
        roleIds.forEach(roleId -> {
            SysRole sysRole_ = sysRoleMapper.selectById(roleId);
            //是否被用户使用
            List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                    .eq("role_id", roleId)
                    .eq("is_delete", 0)
            );
            if(CollectionUtils.isNotEmpty(sysUsers)){
                throw new GlobalCommonException("当前角色["+sysRole_.getRoleName()+"]被用户引用,不能删除");
            }
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRole.setIsDelete(1);//删除
            sysRole.setUpdateById(SecurityUtils.getUserId());
            sysRoleMapper.updateById(sysRole);
        });
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRole.setUpdateById(SecurityUtils.getUserId());
        sysRoleMapper.updateById(sysRole);
    }

    @Override
    public SysRole getSysRoleByRoleId(String roleId) {
        return sysRoleMapper.selectById(roleId);
    }

    @Override
    public List<SysRole> getSysRoleListByTenantId(String tenantId) {
        return sysRoleMapper.selectList(new QueryWrapper<SysRole>()
                .eq(StringUtils.isNotEmpty(tenantId),"tenant_id", tenantId)
                .eq("is_delete", 0)
                .eq("`status`", 0)
                .orderByAsc("create_time")
        );
    }

    @Override
    public Page getSysRolePage(Integer pageNo, Integer pageSize, String roleName,String roleSymbol,Integer status, LocalDateTime startTime, LocalDateTime endTime,String tenantId) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysRole> sysRoles = sysRoleMapper.selectList(new QueryWrapper<SysRole>()
                .like(StringUtils.isNotEmpty(roleName), "role_name", roleName)
                .like(StringUtils.isNotEmpty(roleSymbol), "role_symbol", roleSymbol)
                .eq(status != null, "`status`", status)
                .eq(StringUtils.isNotEmpty(tenantId), "`tenant_id`", tenantId)
                .eq("`is_delete`", 0)//未删除的租户
                .ge(startTime != null, "create_time", startTime)
                .le(endTime != null, "create_time", endTime)
                .orderByDesc("update_time")
        );
        if(CollectionUtils.isNotEmpty(sysRoles)){
            sysRoles.forEach(sysRole -> {
                sysRole.setSysTenant(sysTenantMapper.selectById(sysRole.getTenantId()));
            });
        }
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoles);
        Page page = new Page();
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setHasNext(pageInfo.isHasNextPage());
        page.setHasPre(pageInfo.isHasPreviousPage());
        page.setCount(pageInfo.getTotal());
        page.setList(pageInfo.getList());
        return page;
    }
}
