package com.yaya.boot.service;

import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.excel.UserExcel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户管理
 */
public interface SysUserService {

    /**
     * 添加
     * @param sysUser 用户信息
     */
    void addSysUser(SysUser sysUser);

    /**
     * 批量导入
     * @param userExcels 用户数据
     * @param cover 是否覆盖 1:覆盖 0:不覆盖
     */
    void addBatchSysUser(List<UserExcel> userExcels,int cover);

    /**
     * 删除
     * @param userId 用户ID
     */
    void deleteSysUser(String userId);

    /**
     * 批量删除
     * @param userIds 用户ID列表
     */
    void deleteBatchSysUser(List<String> userIds);

    /**
     * 批量重置密码
     * @param userIds 用户ID列表
     */
    void batchResetSysUserPassword(List<String> userIds);

    /**
     * 批量用户封禁和解封
     * @param userIds 用户ID列表
     * @param flag 1: 解封 0:封禁
     */
    void batchDisOrEnableSysUserStatus(List<String> userIds,Integer flag);

    /**
     * 更新
     * @param sysUser 用户信息
     */
    void updateSysUser(SysUser sysUser);

    /**
     * @param userId 用户ID
     * @return 详情
     */
    SysUser getSysUser(String userId);

    /**
     * @param pageNo        当前页
     * @param pageSize      页容量
     * @param tenantId      租户ID
     * @param deptIds       部门ID列表
     * @param roleId        角色ID
     * @param username      用户名
     * @param phone         手机号
     * @param email         邮箱
     * @param sex           性别 1:男 0:女
     * @param isEnabled     账号是否可用 1:可用 0:不可用
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @return 页
     */
    Page getSysUserPage(Integer pageNo, Integer pageSize, String tenantId, List<String> deptIds, String roleId, String username, String phone, String email, Integer sex, Integer isEnabled, LocalDateTime startTime, LocalDateTime endTime);
}
