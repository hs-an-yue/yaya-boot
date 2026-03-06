package com.yaya.boot.service;

import com.yaya.boot.config.YaYaConfig;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysNoticeType;

import java.util.List;

/**
 * 公告管理
 */
public interface SysNoticeTypeService {
    /**
     * 创建公告类型
     */
    void addSysNoticeType(SysNoticeType sysNoticeType);

    /**
     * 删除公告类型
     */
    void deleteSysNoticeType(String noticeTypeId);

    /**
     * 更新公告类型
     */
    void updateSysNoticeType(SysNoticeType sysNoticeType);

    /**
     * 公告类型列表
     */
    List<SysNoticeType> getSysNoticeTypeListByTenantId(String tenantId);

    /**
     * 公告类型分页
     */
    Page getSysNoticeTypePageByTenantId(Integer pageNo, Integer pageSize,String tenantId,String noticeTypeName);
}
