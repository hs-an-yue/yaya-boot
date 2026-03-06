package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysNotice;
import com.yaya.boot.entity.SysNoticeType;
import com.yaya.boot.entity.SysTenant;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.mapper.SysNoticeMapper;
import com.yaya.boot.mapper.SysNoticeTypeMapper;
import com.yaya.boot.mapper.SysTenantMapper;
import com.yaya.boot.mapper.SysUserMapper;
import com.yaya.boot.service.SysNoticeTypeService;
import com.yaya.boot.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SysNoticeTypeServiceImpl implements SysNoticeTypeService {

    @Resource
    private SysNoticeMapper sysNoticeMapper;
    @Resource
    private SysNoticeTypeMapper sysNoticeTypeMapper;
    @Resource
    private SysTenantMapper sysTenantMapper;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public void addSysNoticeType(SysNoticeType sysNoticeType) {
        sysNoticeType.setTenantId(SecurityUtils.getTenantId());
        sysNoticeType.setCreateById(SecurityUtils.getUserId());
        sysNoticeType.setUpdateById(SecurityUtils.getUserId());
        sysNoticeTypeMapper.insert(sysNoticeType);
    }

    @Override
    public void deleteSysNoticeType(String noticeTypeId) {
        List<SysNotice> sysNoticeList = sysNoticeMapper.selectList(new QueryWrapper<SysNotice>().eq("notice_type_id", noticeTypeId));
        if(CollectionUtils.isNotEmpty(sysNoticeList)){
            throw new GlobalCommonException("有关联的公告,不能删除");
        }
        sysNoticeTypeMapper.deleteById(noticeTypeId);
    }

    @Override
    public void updateSysNoticeType(SysNoticeType sysNoticeType) {
        sysNoticeType.setUpdateById(SecurityUtils.getUserId());
        sysNoticeTypeMapper.updateById(sysNoticeType);
    }

    @Override
    public List<SysNoticeType> getSysNoticeTypeListByTenantId(String tenantId) {
        return sysNoticeTypeMapper.selectList(new QueryWrapper<SysNoticeType>().eq("tenant_id", tenantId));
    }

    @Override
    public Page getSysNoticeTypePageByTenantId(Integer pageNo, Integer pageSize, String tenantId, String noticeTypeName) {
        Page page = new  Page();
        PageHelper.startPage(pageNo, pageSize);
        List<SysNoticeType> sysNoticeTypeList = sysNoticeTypeMapper.selectList(new QueryWrapper<SysNoticeType>()
                .eq("tenant_id", tenantId)
                .like(StringUtils.isNotEmpty(noticeTypeName),"notice_type_name", noticeTypeName)
                .orderByDesc("update_time")
        );
        sysNoticeTypeList.forEach(sysNoticeType -> {
            SysTenant sysTenant = sysTenantMapper.selectById(sysNoticeType.getTenantId());
            sysNoticeType.setSysTenant(sysTenant);
            SysUser sysUser = sysUserMapper.selectById(sysNoticeType.getCreateById());
            sysNoticeType.setCreateUser(sysUser);
        });
        PageInfo<SysNoticeType> info = new PageInfo<>(sysNoticeTypeList);
        page.setPageNum(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setCount(info.getTotal());
        page.setList(info.getList());
        page.setHasPre(info.isHasPreviousPage());
        page.setHasNext(info.isHasNextPage());
        return page;
    }
}
