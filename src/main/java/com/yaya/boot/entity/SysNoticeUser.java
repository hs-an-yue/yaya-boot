package com.yaya.boot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 公告用户关联表 - sys_notice_user
 */
@Data
@TableName(value = "`sys_notice_user`")
public class SysNoticeUser {

    @Schema(description = "公告ID")
    @MppMultiId
    @TableField(value = "notice_id")
    private String noticeId;

    @Schema(description = "用户ID")
    @MppMultiId
    @TableField(value = "user_id")
    private String userId;

    @Schema(description = "阅读时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "read_time")
    private LocalDateTime readTime;

    @Schema(description = "阅读状态: 0-未阅读, 1-已阅读")
    @TableField(value = "`status`")
    private Integer status;
}
