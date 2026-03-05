package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysPermission;
import com.yaya.boot.entity.SysRolePermission;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.mapper.SysPermissionMapper;
import com.yaya.boot.mapper.SysRolePermissionMapper;
import com.yaya.boot.service.SysPermissionService;
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
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public void addSysPermission(SysPermission sysPermission) {
        sysPermission.setCreateById(SecurityUtils.getUserId());
        sysPermission.setUpdateById(SecurityUtils.getUserId());
        sysPermissionMapper.insert(sysPermission);
    }

    @Override
    public void deleteSysPermission(String permissionId) {
        List<SysRolePermission> rolePermissions = sysRolePermissionMapper.selectList(new QueryWrapper<SysRolePermission>()
                .eq("permission_id", permissionId)
        );
        if(CollectionUtils.isNotEmpty(rolePermissions)){
            throw new GlobalCommonException("权限被角色关联,不能删除");
        }
        sysPermissionMapper.deleteById(permissionId);
    }

    @Override
    public void updateSysPermission(SysPermission sysPermission) {
        sysPermission.setUpdateById(SecurityUtils.getUserId());
        sysPermissionMapper.updateById(sysPermission);
    }

    @Override
    public SysPermission getSysPermissionByPermissionId(String permissionId) {
        return sysPermissionMapper.selectById(permissionId);
    }

    @Override
    public Page getSysPermissionPage(Integer pageNo, Integer pageSize, String permissionName, String permissionSymbol, Integer status, LocalDateTime startTime, LocalDateTime endTime) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysPermission> sysPermissions = sysPermissionMapper.selectList(new QueryWrapper<SysPermission>()
                .like(StringUtils.isNotEmpty(permissionName), "permission_name", permissionName)
                .like(StringUtils.isNotEmpty(permissionSymbol), "permission_symbol", permissionSymbol)
                .eq(status != null, "`status`", status)
                .ge(startTime != null, "create_time", startTime)
                .le(endTime != null, "create_time", endTime)
                .orderByDesc("update_time")
        );
        PageInfo<SysPermission> pageInfo = new PageInfo<>(sysPermissions);
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
