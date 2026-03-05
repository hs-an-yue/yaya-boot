package com.yaya.boot.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.yaya.boot.entity.SysNotice;
import com.yaya.boot.entity.SysNoticeUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysNoticeUserMapper extends MppBaseMapper<SysNoticeUser> {

    /**
     * @param userId 用户id
     * @return 推送给我的消息
     */
    List<SysNotice> mySysNoticePageByUserId(@Param("userId") String userId);
}
