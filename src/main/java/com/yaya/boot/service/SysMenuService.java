package com.yaya.boot.service;

import com.yaya.boot.entity.SysMenu;

import java.util.List;

/**
 * 菜单管理
 */
public interface SysMenuService {
    /**
     * 添加
     * @param sysMenu 信息
     */
    void addSysMenu(SysMenu sysMenu);

    /**
     * 删除
     * @param menuId 菜单ID
     */
    void deleteSysMenu(String menuId);

    /**
     * 更新
     * @param sysMenu 信息
     */
    void updateSysMenu(SysMenu sysMenu);

    /**
     * @param menuTitle 标题
     * @param status    是否禁用
     * @param flag      是否查询按钮 1:查询全部 2:排除按钮
     * @return 菜单-管理页面-树状表格(菜单和按钮管理[权限管理])
     */
    List<SysMenu> getSysMenuTree(String menuTitle,Integer status,Integer flag);

    /**
     * @param roleId 角色ID
     * @return 已授权的菜单列表
     */
    List<SysMenu> getAuthSysMenuTree(String roleId);
}
