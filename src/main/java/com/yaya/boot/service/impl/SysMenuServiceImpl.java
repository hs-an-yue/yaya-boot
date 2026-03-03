package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaya.boot.entity.SysMenu;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.mapper.SysMenuMapper;
import com.yaya.boot.service.SysMenuService;
import com.yaya.boot.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public void addSysMenu(SysMenu sysMenu) {
        String menuTitle = sysMenu.getMenuTitle();
        Integer menuType = sysMenu.getMenuType();
        if(StringUtils.isEmpty(menuTitle)){
            throw new GlobalCommonException("菜单标题不能为空");
        }
        if(menuType==null){
            throw new GlobalCommonException("菜单类型不能为空");
        }
        String parentId = sysMenu.getParentId();
        //计算order_num
        if(sysMenu.getOrderNum()==null){
            //获取编号最大值
            List<Map<String, Object>> maps = sysMenuMapper.selectMaps(new QueryWrapper<SysMenu>()
                    .select("MAX(order_num) AS order_num")
                    .eq("parent_id",parentId!=null?parentId:"0")
            );
            if(!maps.isEmpty() && maps.getFirst()!=null && maps.getFirst().get("order_num")!=null){
                int orderNum = Integer.parseInt(maps.getFirst().get("order_num").toString());
                sysMenu.setOrderNum(orderNum);
            }
        }

        if(parentId==null || parentId.equals("0")){
            sysMenu.setMenuLevel(1);//顶级菜单
        }else {
            SysMenu sysMenu_ = sysMenuMapper.selectById(parentId);
            //下一级加1
            sysMenu.setMenuLevel(sysMenu_.getMenuLevel()+1);
        }
        /*
            如果是子菜单需要判断子菜单创建范围
            1. 子菜单分为按钮和链接和目录,所以子菜单生成有逻辑限制
            2. 限制条件
                2.1 如果是顶级菜单或者父菜单是目录不能创建按钮
                2.2 如果父菜单是链接菜单，那么子菜单不能创建目录
                2.3 如果父菜单是按钮，不能创建子菜单
         */
        //1:目录2:菜单3:按钮
        if(parentId==null || parentId.equals("0")){//顶级菜单
            if(menuType==3){//按钮
                throw new GlobalCommonException("顶级菜单不能创建按钮");
            }
        }else {//非顶级菜单
            //查询父菜单类型
            SysMenu sysMenu_ = sysMenuMapper.selectById(parentId);
            Integer menuType_ = sysMenu_.getMenuType();
            if(menuType_==1){//父菜单是目录
                if(menuType==3){//子菜单是按钮
                    throw new GlobalCommonException("目录下不能创建按钮");
                }
            } else if (menuType_==2) {//父菜单是链接菜单
                if(menuType==1){//子菜单是目录
                    throw new GlobalCommonException("链接菜单下不能创建目录");
                }
            }else if(menuType_==3){//父菜单是按钮菜单
                throw new GlobalCommonException("按钮下不能创建子菜单");
            }
        }
        sysMenu.setCreateById(SecurityUtils.getUserId());
        sysMenu.setUpdateById(SecurityUtils.getUserId());
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    public void deleteSysMenu(String menuId) {
        //查询是否存在有效子节点
        List<SysMenu> sysMenus = sysMenuMapper.selectList(new QueryWrapper<SysMenu>()
                .eq("is_delete", 0)
                .eq("parent_id", menuId)
        );
        if(CollectionUtils.isNotEmpty(sysMenus)){
            throw new GlobalCommonException("存在有效子节点,不能删除");
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(menuId);
        sysMenu.setIsDelete(1);//删除
        sysMenu.setUpdateById(SecurityUtils.getUserId());
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void updateSysMenu(SysMenu sysMenu) {
        //菜单类型不能更新
        String menuId = sysMenu.getMenuId();
        SysMenu sysMenu_ = sysMenuMapper.selectById(menuId);
        //原菜单类型
        Integer menuType = sysMenu_.getMenuType();
        //新菜单类型
        Integer menuType_new = sysMenu.getMenuType();
        if(!menuType.equals(menuType_new)){
            throw new GlobalCommonException("菜单类型不能更新");
        }
        sysMenu.setUpdateById(SecurityUtils.getUserId());
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public List<SysMenu> getSysMenuTree(String menuTitle, Integer status,Integer flag) {
        if(flag!=null && flag==1){ //查询全部类型的菜单
            return sysMenuMapper.getSysMenuTree(menuTitle, status);
        }else {
            //排除按钮
            return sysMenuMapper.getSysMenuTreeExcludeButton();
        }
    }

    @Override
    public List<SysMenu> getAuthSysMenuTree(String roleId) {
        //是否是管理者，管理者包括 平台管理员admin和平台运营管理员operation
        Boolean manager = SecurityUtils.isAdminOrOperation();
        if(manager){
            return sysMenuMapper.getSysMenuTreeExcludeButton();
        }else {
            return sysMenuMapper.getAuthSysMenuByRoleId(roleId);
        }
    }
}
