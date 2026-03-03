package com.yaya.boot.service;

import java.util.List;

/**
 * 角色菜单关联关联
 */
public interface SysRoleMenuService {

    /**
     * 添加 OR 更新 角色菜单
     * @param roleId    角色ID
     * @param menuIds   菜单ID列表
     */
    void addOrUpdateSysRoleMenu(String roleId, List<String> menuIds);

    /**
     * @param roleId 角色ID
     * @return 已授权的最终节点菜单ID列表
     */
    List<String> getAuthMenuIdsByRoleId(String roleId);
}
