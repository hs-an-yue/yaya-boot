package com.yaya.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 公告表 - sys_notice
 */
@Data
@TableName(value = "`sys_notice`")
public class SysNotice {

    @Schema(description = "公告ID")
    @TableId(value = "notice_id",type = IdType.ASSIGN_UUID)
    private String noticeId;

    @Schema(description = "公告标题")
    @TableField(value = "notice_title")
    private String noticeTitle;

    @Schema(description = "公告内容")
    @TableField(value = "notice_content")
    private String noticeContent;

    @Schema(description = "公告级别: 0-普通, 1-重要, 2-紧急")
    @TableField(value = "notice_level")
    private Integer noticeLevel;

    @Schema(description = "公告类型: 1-通知, 2-新闻, 3-活动, 4-公示")
    @TableField(value = "notice_type")
    private Integer noticeType;

    @Schema(description = "发布人ID")
    @TableField(value = "publish_user_id")
    private String publishUserId;

    @Schema(description = "是否置顶: 0-否, 1-是")
    @TableField(value = "is_top")
    private Integer isTop;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
