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
     * 公告列表页 - 基于发布人
     */
    Page getSysNoticePageByPublishUserId(Integer pageNo,Integer pageSize,String publishUserId);
    /**
     * 公告列表页 - 我的公告
     */
    Page mySysNoticePageByUserId(Integer pageNo,Integer pageSize,String userId);
    /**
     * 公告阅读
     */
    void readSysNoticeByNoticeIdAndUserId(String noticeId,String userId);
}
