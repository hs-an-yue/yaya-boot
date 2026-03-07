package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysNotice;
import com.yaya.boot.entity.SysNoticeDept;
import com.yaya.boot.entity.SysNoticeType;
import com.yaya.boot.mapper.SysNoticeDeptMapper;
import com.yaya.boot.mapper.SysNoticeMapper;
import com.yaya.boot.mapper.SysNoticeTypeMapper;
import com.yaya.boot.mapper.SysUserMapper;
import com.yaya.boot.service.SysNoticeService;
import com.yaya.boot.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Resource
    private SysNoticeMapper sysNoticeMapper;
    @Resource
    private SysNoticeDeptMapper sysNoticeDeptMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysNoticeTypeMapper sysNoticeTypeMapper;

    @Override
    public void publishSysNotice(SysNotice sysNotice) {
        sysNotice.setPublishUserId(SecurityUtils.getUserId());
        sysNoticeMapper.insert(sysNotice);
    }

    @Override
    public void deleteSysNotice(String noticeId) {
        //删除消息
        sysNoticeMapper.deleteById(noticeId);
        //删除关联用户阅读消息
        sysNoticeDeptMapper.delete(new QueryWrapper<SysNoticeDept>()
            .eq("notice_id", noticeId)
        );
    }

    @Override
    public void updateSysNotice(SysNotice sysNotice) {
        sysNoticeMapper.updateById(sysNotice);
    }

    @Override
    public SysNotice getSysNoticeById(String noticeId) {
        SysNotice sysNotice = sysNoticeMapper.selectById(noticeId);
        if(sysNotice!=null){
            sysNotice.setSysNoticeType(sysNoticeTypeMapper.selectById(sysNotice.getNoticeTypeId()));
            sysNotice.setPublishUser(sysUserMapper.selectById(sysNotice.getPublishUserId()));
        }
        return sysNotice;
    }

    @Override
    public Page getSysNoticePageByPublishUserId(Integer pageNo, Integer pageSize, String publishUserId) {
        Page page = new  Page();
        PageHelper.startPage(pageNo, pageSize);
        List<SysNotice> sysNoticeList = sysNoticeMapper.selectList(new QueryWrapper<SysNotice>().eq("publish_user_id", publishUserId));
        sysNoticeList.forEach(sysNotice -> {
            sysNotice.setPublishUser(sysUserMapper.selectById(sysNotice.getPublishUserId()));
            SysNoticeType sysNoticeType = sysNoticeTypeMapper.selectById(sysNotice.getNoticeTypeId());
            sysNotice.setSysNoticeType(sysNoticeType);
        });
        PageInfo<SysNotice> info = new PageInfo<>(sysNoticeList);
        page.setPageNum(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setCount(info.getTotal());
        page.setList(info.getList());
        page.setHasPre(info.isHasPreviousPage());
        page.setHasNext(info.isHasNextPage());
        return page;
    }

    @Override
    public Page mySysNoticePageByDeptId(Integer pageNo, Integer pageSize, String deptId) {
        Page page = new  Page();
        PageHelper.startPage(pageNo, pageSize);
        List<SysNotice> sysNoticeList = sysNoticeDeptMapper.mySysNoticePageByDeptId(deptId);
        sysNoticeList.forEach(sysNotice -> {
            sysNotice.setPublishUser(sysUserMapper.selectById(sysNotice.getPublishUserId()));
        });
        PageInfo<SysNotice> info = new PageInfo<>(sysNoticeList);
        page.setPageNum(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setCount(info.getTotal());
        page.setList(info.getList());
        page.setHasPre(info.isHasPreviousPage());
        page.setHasNext(info.isHasNextPage());
        return page;
    }
}
