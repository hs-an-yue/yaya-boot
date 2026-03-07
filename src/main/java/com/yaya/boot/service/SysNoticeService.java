package com.yaya.boot.service;

import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysNotice;

/**
 * 公告管理
 */
public interface SysNoticeService {
    /**
     * 发布公告
     */
    void publishSysNotice(SysNotice sysNotice);
    /**
     * 删除公告
     */
    void deleteSysNotice(String noticeId);
    /**
     * 更新公告
     */
    void updateSysNotice(SysNotice sysNotice);
    /**
     * 公告详情
     */
    SysNotice getSysNoticeById(String noticeId);
    /**
     * 公告列表页 - 基于部门
     */
    Page getSysNoticePageByPublishUserId(Integer pageNo,Integer pageSize,String deptId);
    /**
     * 公告列表页 - 我的部门公告
     */
    Page mySysNoticePageByDeptId(Integer pageNo,Integer pageSize,String deptId);
}
