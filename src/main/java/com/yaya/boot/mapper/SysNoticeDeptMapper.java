package com.yaya.boot.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.yaya.boot.entity.SysNotice;
import com.yaya.boot.entity.SysNoticeDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysNoticeDeptMapper extends MppBaseMapper<SysNoticeDept> {

    /**
     * @param deptId 部门id
     * @return 推送给我的消息
     */
    List<SysNotice> mySysNoticePageByDeptId(@Param("deptId") String deptId);
}
