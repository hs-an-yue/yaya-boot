package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysFile;
import com.yaya.boot.mapper.SysFileMapper;
import com.yaya.boot.service.SysFileService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class SysFileServiceImpl implements SysFileService {
    @Resource
    private SysFileMapper sysFileMapper;

    @Override
    public void addSave(SysFile sysFile) {
        sysFileMapper.insert(sysFile);
    }

    @Override
    public Page getFilePage(Integer pageNo, Integer pageSize, String fileName, LocalDateTime startTime, LocalDateTime endTime,Long tenantId) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysFile> sysFiles = sysFileMapper.selectList(new QueryWrapper<SysFile>()
                .like(StringUtils.isNotEmpty(fileName), "file_name", fileName)
                .ge("create_time", startTime)
                .le("create_time", endTime)
                .eq(tenantId!=null,"tenant_id",tenantId)
                .orderByAsc("create_time")
        );
        PageInfo<SysFile> info = new PageInfo<>(sysFiles);
        Page page = new Page();
        page.setPageNum(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setList(info.getList());
        page.setHasNext(info.isHasNextPage());
        page.setHasPre(info.isHasPreviousPage());
        page.setCount(info.getTotal());
        return page;
    }


}
