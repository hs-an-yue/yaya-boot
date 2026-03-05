package com.yaya.boot.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysDepartment;
import com.yaya.boot.entity.SysRole;
import com.yaya.boot.entity.SysTenant;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.excel.UserExcel;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.mapper.SysDepartmentMapper;
import com.yaya.boot.mapper.SysRoleMapper;
import com.yaya.boot.mapper.SysTenantMapper;
import com.yaya.boot.mapper.SysUserMapper;
import com.yaya.boot.service.SysUserService;
import com.yaya.boot.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysDepartmentMapper sysDepartmentMapper;
    @Resource
    private SysTenantMapper sysTenantMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void addSysUser(SysUser sysUser) {
        //密码需要加密
        String password = sysUser.getPassword();
        sysUser.setPassword(passwordEncoder.encode(StringUtils.isNotEmpty(password)?password:"123456"));
        sysUser.setCreateById(SecurityUtils.getUserId());
        sysUser.setUpdateById(SecurityUtils.getUserId());
        //校验租户和角色ID
        String tenantId = sysUser.getTenantId();
        String roleId = sysUser.getRoleId();
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        if(!sysRole.getTenantId().equals(tenantId)){
            throw new GlobalCommonException("角色和租户不匹配");
        }
        //校验租户和部门ID
        String deptId = sysUser.getDeptId();
        SysDepartment sysDepartment = sysDepartmentMapper.selectById(deptId);
        if(!sysDepartment.getTenantId().equals(tenantId)){
            throw new GlobalCommonException("部门和租户不匹配");
        }
        sysUserMapper.insert(sysUser);
    }

    @Override
    public void addBatchSysUser(List<UserExcel> userExcels,int cover) {
        userExcels.forEach(userExcel -> {
            String phone = userExcel.getPhone();
            String tenantId = userExcel.getTenantId();
            String roleId = userExcel.getRoleId();
            String deptId = userExcel.getDeptId();
            //查询是否在库中已存在
            SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("phone", phone).eq("tenant_id", tenantId));
            if(sysUser==null){//不存在直接入库
                sysUser = new SysUser();
                sysUser.setPhone(phone);
                sysUser.setTenantId(tenantId);
                sysUser.setCreateById(SecurityUtils.getUserId());
                sysUser.setUpdateById(SecurityUtils.getUserId());
                sysUser.setDeptId(deptId);
                sysUser.setRoleId(roleId);
                sysUser.setUsername(userExcel.getUsername());
                sysUser.setEmail(userExcel.getEmail());
                sysUser.setSex(userExcel.getSex());
                sysUser.setRemark(userExcel.getRemark());
                //密码 如果账号是手机号，那么密码默认是手机号后6位，如果账号不是手机号，密码默认123456
                String password = "123456";
                boolean mobile = Validator.isMobile(phone);
                if(mobile){
                    password = phone.substring(5);
                }
                //密码加密
                password = passwordEncoder.encode(password);
                sysUser.setPassword(password);
                //账号过期时间
                LocalDateTime expiredTime = userExcel.getExpiredTime();
                sysUser.setExpiredTime(expiredTime);
                //账号是否过期(如果过期时间已经过期,那么标记账号过期,如果过期时间还没有过期,标记账号未过期)
                LocalDateTime now = LocalDateTime.now();
                //租户角色校验(当前角色ID是否属于当前租户下的角色)
                SysRole sysRole = sysRoleMapper.selectById(roleId);
                if(sysRole==null){
                    throw new GlobalCommonException("角色编号["+roleId+"]不存在");
                }
                if(!sysRole.getTenantId().equals(tenantId)){
                    throw new GlobalCommonException("角色和租户不匹配");
                }
                //校验租户和部门ID(当前部门ID是否属于当前租户下的部门)
                SysDepartment sysDepartment = sysDepartmentMapper.selectById(deptId);
                if(sysDepartment==null){
                    throw new GlobalCommonException("部门编号["+deptId+"]不存在");
                }
                if(!sysDepartment.getTenantId().equals(tenantId)){
                    throw new GlobalCommonException("部门和租户不匹配");
                }
                sysUserMapper.insert(sysUser);
            }else { //已存在,判断是否覆盖,还是忽略
                if(cover==1){//覆盖(更新操作)
                    sysUser.setCreateById(SecurityUtils.getUserId());
                    sysUser.setUpdateById(SecurityUtils.getUserId());
                    sysUser.setDeptId(userExcel.getDeptId());
                    sysUser.setRoleId(userExcel.getRoleId());
                    sysUser.setUsername(userExcel.getUsername());
                    sysUser.setEmail(userExcel.getEmail());
                    sysUser.setSex(userExcel.getSex());
                    sysUser.setIsEnabled(1);//账号是否可用 1:可用 0:不可用
                    sysUser.setRemark(userExcel.getRemark());
                    //密码 如果账号是手机号，那么密码默认是手机号后6位，如果账号不是手机号，密码默认123456
                    String password = "123456";
                    boolean mobile = Validator.isMobile(phone);
                    if(mobile){
                        password = phone.substring(5);
                    }
                    //密码加密
                    password = passwordEncoder.encode(password);
                    sysUser.setPassword(password);
                    //账号过期时间
                    LocalDateTime expiredTime = userExcel.getExpiredTime();
                    sysUser.setExpiredTime(expiredTime);
                    //账号是否过期(如果过期时间已经过期,那么标记账号过期,如果过期时间还没有过期,标记账号未过期)
                    LocalDateTime now = LocalDateTime.now();
                    //租户角色校验(当前角色ID是否属于当前租户下的角色)
                    SysRole sysRole = sysRoleMapper.selectById(roleId);
                    if(sysRole==null){
                        throw new GlobalCommonException("角色编号["+roleId+"]不存在");
                    }
                    if(!sysRole.getTenantId().equals(tenantId)){
                        throw new GlobalCommonException("角色和租户不匹配");
                    }
                    //校验租户和部门ID(当前部门ID是否属于当前租户下的部门)
                    SysDepartment sysDepartment = sysDepartmentMapper.selectById(deptId);
                    if(sysDepartment==null){
                        throw new GlobalCommonException("部门编号["+deptId+"]不存在");
                    }
                    if(!sysDepartment.getTenantId().equals(tenantId)){
                        throw new GlobalCommonException("部门和租户不匹配");
                    }
                    sysUserMapper.updateById(sysUser);
                }
            }
        });
    }

    @Override
    public void deleteSysUser(String userId) {
        sysUserMapper.deleteById(userId);
    }

    @Override
    public void deleteBatchSysUser(List<String> userIds) {
        if(CollectionUtils.isEmpty(userIds)){
            throw new GlobalCommonException("请选择要删除的用户");
        }
        userIds.forEach(userId -> {
            SysUser sysUser = sysUserMapper.selectById(userId);
            if(sysUser!=null){
                if(sysUser.getPhone().equals("admin") || sysUser.getPhone().equals("operation")){
                    throw new GlobalCommonException("系统自带管理员账户或者运营账户不能被删除");
                }else {
                    //删除
                    sysUserMapper.deleteById(userId);
                }
            }
        });
    }

    @Override
    public void batchResetSysUserPassword(List<String> userIds) {
        if(CollectionUtils.isEmpty(userIds)){
            throw new GlobalCommonException("请选择要重置密码的用户");
        }
        userIds.forEach(userId -> {
            SysUser sysUser = sysUserMapper.selectById(userId);
            if(sysUser!=null){
                sysUser.setPassword(passwordEncoder.encode("123456"));
                sysUserMapper.updateById(sysUser);
            }
        });
    }

    @Override
    public void batchDisOrEnableSysUserStatus(List<String> userIds, Integer flag) {
        if(CollectionUtils.isEmpty(userIds)){
            throw new GlobalCommonException("请选择要操作的用户");
        }
        if(flag==null){
            throw new GlobalCommonException("选择状态位:封禁账户或者解封账户");
        }
        userIds.forEach(userId -> {
            SysUser sysUser = sysUserMapper.selectById(userId);
            if(sysUser!=null){
                sysUser.setUpdateById(SecurityUtils.getUserId());//更新人
                if(flag==1){ //解封
                    sysUser.setIsEnabled(1);
                    sysUserMapper.updateById(sysUser);
                }else {
                    String phone = sysUser.getPhone();
                    if(phone.equals("admin") || phone.equals("operation")){
                        throw new GlobalCommonException("系统自带管理员账户或者运营账户不能被封禁");
                    }else {
                        sysUser.setIsEnabled(0);
                        sysUserMapper.updateById(sysUser);
                    }
                }
            }
        });
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUser.setUpdateById(SecurityUtils.getUserId());
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public void changePassword(String userId,String oldPassword, String newPassword, String repeatPassword) {
        if(!newPassword.equals(repeatPassword)){
            throw new GlobalCommonException("两次输入的新密码不相同");
        }
        //查询用户
        SysUser sysUser = sysUserMapper.selectById(userId);
        //用户加密后的原密码
        String password = sysUser.getPassword();
        boolean matches = passwordEncoder.matches(oldPassword, password);
        if(!matches){
            throw new GlobalCommonException("原密码输入错误");
        }
        String encode_new = passwordEncoder.encode(newPassword);
        sysUser.setPassword(encode_new);//更新密码
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public SysUser getSysUser(String userId) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser != null) {
            sysUser.setPassword(null);//密码设置为空
            sysUser.setSysTenant(sysTenantMapper.selectById(sysUser.getTenantId()));
            sysUser.setSysRole(sysRoleMapper.selectById(sysUser.getRoleId()));
            sysUser.setSysDepartment(sysDepartmentMapper.selectById(sysUser.getDeptId()));
        }
        return sysUser;
    }

    @Override
    public Page getSysUserPage(Integer pageNo, Integer pageSize, String tenantId, List<String> deptIds, String roleId, String username, String phone, String email, Integer sex, Integer isEnabled, LocalDateTime startTime, LocalDateTime endTime) {
        List<String> dIds = new ArrayList<>();
        //计算部门以及部门下的所有子部门
        if(!CollectionUtils.isEmpty(deptIds)){
            deptIds.forEach(deptId -> {
                List<SysDepartment> deptListInfo = sysDepartmentMapper.getSpecifiedDeptIdAndDeptInfoAndDescendantsDeptListInfo(deptId);
                if(!CollectionUtils.isEmpty(deptListInfo)){
                    dIds.addAll(deptListInfo.stream().map(SysDepartment::getDeptId).toList());
                }
            });
        }

        PageHelper.startPage(pageNo, pageSize);
        List<SysUser> sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                .like(StringUtils.isNotEmpty(username), "username", username)
                .like(StringUtils.isNotEmpty(phone), "phone", phone)
                .like(StringUtils.isNotEmpty(email), "email", email)
                .eq(StringUtils.isNotEmpty(tenantId), "`tenant_id`", tenantId)
                .eq(StringUtils.isNotEmpty(roleId), "`role_id`", roleId)
                .in(!CollectionUtils.isEmpty(dIds),"dept_id",dIds.stream().distinct().toList())
                .eq(sex != null, "`sex`", sex)
                .eq(isEnabled != null, "`is_enabled`", isEnabled)
                .ge(startTime != null, "create_time", startTime)
                .le(endTime != null, "create_time", endTime)
                .orderByAsc("create_time")
        );
        if(!CollectionUtils.isEmpty(sysUsers)){
            sysUsers.forEach(sysUser -> {
                String deptId_ = sysUser.getDeptId();
                String roleId_ = sysUser.getRoleId();
                String tenantId_ = sysUser.getTenantId();
                SysDepartment sysDepartment = sysDepartmentMapper.selectById(deptId_);
                SysRole sysRole = sysRoleMapper.selectById(roleId_);
                SysTenant sysTenant = sysTenantMapper.selectById(tenantId_);
                sysUser.setSysRole(sysRole);
                sysUser.setSysDepartment(sysDepartment);
                sysUser.setSysTenant(sysTenant);
                sysUser.setPassword(null);//密码设置为null
            });
        }
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
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
