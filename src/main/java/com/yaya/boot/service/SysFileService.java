package com.yaya.boot.service;

import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysFile;

import java.time.LocalDateTime;

public interface SysFileService {
    /**
     * 保存文件
     */
    void addSave(SysFile sysFile);

    /**
     * 分页
     */
    Page getFilePage(Integer pageNo, Integer pageSize, String fileName, LocalDateTime startTime, LocalDateTime endTime,Long tenantId);
}
