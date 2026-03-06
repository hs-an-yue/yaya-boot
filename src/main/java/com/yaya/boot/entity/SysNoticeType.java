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
 * 公告类型表 - sys_notice_type
 */
@Data
@TableName(value = "`sys_notice_type`")
public class SysNoticeType {

    @Schema(description = "公告ID")
    @TableId(value = "notice_type_id",type = IdType.ASSIGN_UUID)
    private String noticeTypeId;

    @Schema(description = "公告类型名称")
    @TableField(value = "notice_type_name")
    private String noticeTypeName;

    @Schema(description = "公告类型名称")
    @TableField(value = "tenant_id")
    private String tenantId;

    @Schema(description = "创建人ID")
    @TableField(value = "create_by_id")
    private String createById;

    @Schema(description = "更新人ID")
    @TableField(value = "update_by_id")
    private String updateById;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 租户详情
     */
    @TableField(exist = false)
    private SysTenant sysTenant;

    /**
     * 创建人详情
     */
    @TableField(exist = false)
    private SysUser createUser;

}
