package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaya.boot.entity.SysMenu;
import com.yaya.boot.entity.SysRoleMenu;
import com.yaya.boot.mapper.SysMenuMapper;
import com.yaya.boot.mapper.SysRoleMenuMapper;
import com.yaya.boot.service.SysRoleMenuService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public void addOrUpdateSysRoleMenu(String roleId, List<String> menuIds) {
        //判断当前角色是否已经授权过菜单
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>()
                .eq("role_id", roleId)
        );
        if(CollectionUtils.isNotEmpty(roleMenus)) {
            //删除当前角色下的所有菜单
            sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        }
        //重新授权
        if(CollectionUtils.isNotEmpty(menuIds)) {
            menuIds.stream().distinct().forEach(menuId -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenuMapper.insert(sysRoleMenu);
            });
        }
    }

    @Override
    public List<String> getAuthMenuIdsByRoleId(String roleId) {
        //获取当前角色下的授权菜单ID
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        List<String> authMenuIds = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(roleMenus)) {
            List<String> menuIds = roleMenus.stream().map(SysRoleMenu::getMenuId).distinct().toList();
            //获取最终节点ID(没有子节点的菜单ID)
            menuIds.forEach(menuId -> {
                List<SysMenu> sysMenus = sysMenuMapper.selectList(new QueryWrapper<SysMenu>()
                        .eq("parent_id", menuId)
                );
                //如果为空，说明没有子节点
                if(CollectionUtils.isEmpty(sysMenus)){
                    authMenuIds.add(menuId);
                }
            });
        }
        return authMenuIds;
    }
}
