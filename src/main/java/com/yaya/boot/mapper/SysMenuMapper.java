package com.yaya.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaya.boot.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * @param menuTitle 标题
     * @param status    是否禁用 1:是 0:否
     * @return 菜单-管理页面-树状表格(菜单和按钮管理[权限管理])
     */
    List<SysMenu> getSysMenuTree(@Param("menuTitle") String menuTitle,@Param("status") Integer status);
    /**
     * @param parentId  父ID
     * @param status    是否禁用
     * @return  子节点
     */
    List<SysMenu> getSubSysMenuTree(@Param("parentId") Long parentId,@Param("status") Integer status);

    /**
     * 左侧菜单-专用-全部(排除按钮)
     */
    List<SysMenu> getSysMenuTreeExcludeButton();

    /**
     * @param parentId 当前菜单id
     * @return 左侧菜单子节点-专用-全部(排除按钮)
     */
    List<SysMenu> getSubSysMenuTreeExcludeButton(@Param("parentId") Long parentId);


    /**
     * 左侧菜单 - 查询全部 - 不包含按钮
     */


    /**
     * 获取授权给指定角色的菜单列表
     */
    List<SysMenu> getAuthSysMenuByRoleId(@Param("roleId") String roleId);

    /**
     * 获取指定角色首先下的叶子节点
     */
    List<SysMenu> getAuthChildrenSysMenu(@Param("parentId") String parentId,@Param("roleId") String roleId);
}
