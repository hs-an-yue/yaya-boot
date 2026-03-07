package com.yaya.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaya.boot.entity.SysDepartment;
import com.yaya.boot.entity.SysNoticeDept;
import com.yaya.boot.mapper.SysDepartmentMapper;
import com.yaya.boot.mapper.SysNoticeDeptMapper;
import com.yaya.boot.service.SysNoticeDeptService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysNoticeDeptServiceImpl implements SysNoticeDeptService {

    @Resource
    private SysNoticeDeptMapper sysNoticeDeptMapper;
    @Resource
    private SysDepartmentMapper sysDepartmentMapper;


    @Override
    public void addSysNoticeDept(String noticeId, List<String> deptIds) {
        sysNoticeDeptMapper.delete(new QueryWrapper<SysNoticeDept>().eq("notice_id", noticeId));
        if(CollectionUtils.isNotEmpty(deptIds)) {
            deptIds.forEach(deptId->{
                SysNoticeDept sysNoticeDept=new SysNoticeDept();
                sysNoticeDept.setDeptId(deptId);
                sysNoticeDept.setNoticeId(noticeId);
                sysNoticeDeptMapper.insert(sysNoticeDept);
            });
        }
    }

    @Override
    public List<String> getDeptIdsByNoticeId(String noticeId) {
        List<SysNoticeDept> sysNoticeDepts = sysNoticeDeptMapper.selectList(new QueryWrapper<SysNoticeDept>().eq("notice_id", noticeId));
        if(CollectionUtils.isNotEmpty(sysNoticeDepts)){
            //回显的部门ID是没有子节点的部门ID，或者是最终节点的部门ID
            List<String> deptIds = sysNoticeDepts.stream().map(SysNoticeDept::getDeptId).distinct().toList();
            List<String> authDeptIds = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(deptIds)){
                deptIds.forEach(deptId->{
                    List<SysDepartment> departments = sysDepartmentMapper.selectList(new QueryWrapper<SysDepartment>()
                            .eq("parent_id", deptId));
                    if(CollectionUtils.isEmpty(departments)){
                        authDeptIds.add(deptId);
                    }
                });
            }
            return authDeptIds;
        }
        return List.of();
    }
}
