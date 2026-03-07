package com.yaya.boot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 公告部门关联表 - sys_notice_dept
 */
@Data
@TableName(value = "`sys_notice_dept`")
public class SysNoticeDept {

    @Schema(description = "公告ID")
    @MppMultiId
    @TableField(value = "notice_id")
    private String noticeId;

    @Schema(description = "部门ID")
    @MppMultiId
    @TableField(value = "dept_id")
    private String deptId;
}
