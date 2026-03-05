package com.yaya.boot.security;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaya.boot.entity.SysDepartment;
import com.yaya.boot.entity.SysPermission;
import com.yaya.boot.entity.SysRole;
import com.yaya.boot.entity.SysRolePermission;
import com.yaya.boot.entity.SysTenant;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.mapper.SysDepartmentMapper;
import com.yaya.boot.mapper.SysPermissionMapper;
import com.yaya.boot.mapper.SysRoleMapper;
import com.yaya.boot.mapper.SysRolePermissionMapper;
import com.yaya.boot.mapper.SysTenantMapper;
import com.yaya.boot.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 从数据库获取用户信息,将查询的用户信息送给SpringSecurity上下文
 * 用户信息包含: 基本用户信息+用户角色信息+角色权限信息  RBAC模式
 */
@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysTenantMapper sysTenantMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private SysDepartmentMapper sysDepartmentMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("phone", username));
        if(null==user){
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        //获取部门
        SysDepartment department = sysDepartmentMapper.selectById(user.getDeptId());
        if(null!=department){
            user.setSysDepartment(department);
        }

        //获取租户
        SysTenant sysTenant = sysTenantMapper.selectById(user.getTenantId());
        if(null!=sysTenant){
            user.setSysTenant(sysTenant);
        }

        List<String> roleNameList = new ArrayList<>();
        List<String> permissionNameList = new ArrayList<>();

        //获取角色
        SysRole role = sysRoleMapper.selectById(user.getRoleId());
        if(Objects.nonNull(role)){
            user.setSysRole(role); //设置角色
            //添加到角色名称集合中用于授权
            roleNameList.add(role.getRoleSymbol());
        }

        //获取权限
        List<SysRolePermission> rolePermissionList = sysRolePermissionMapper.selectList(new QueryWrapper<SysRolePermission>().eq("role_id", user.getRoleId()));
        if(CollectionUtil.isNotEmpty(rolePermissionList)){
            List<String> permissionIds = rolePermissionList.stream().map(SysRolePermission::getPermissionId).toList();
            if(CollectionUtil.isNotEmpty(permissionIds)){
                List<SysPermission> permissions = sysPermissionMapper.selectByIds(permissionIds);
                if(CollectionUtil.isNotEmpty(permissions)){
                    List<String> permissionNames = permissions.stream().map(SysPermission::getPermissionSymbol).toList();
                    permissionNameList.addAll(permissionNames);
                }
            }
        }
        return new LoginUserDetails(user,roleNameList,permissionNameList);
    }
}
