package com.yaya.boot.service;

import java.util.List;

/**
 * 消息分配管理
 */
public interface SysNoticeDeptService {

    /**
     * 分配消息
     */
    void addSysNoticeDept(String noticeId, List<String> deptIds);
    /**
     * 指定消息被发布的部门ID列表
     */
    List<String> getDeptIdsByNoticeId(String noticeId);
}
