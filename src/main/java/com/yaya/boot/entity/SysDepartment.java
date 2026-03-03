package com.yaya.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门表 - sys_department
 */
@Data
@TableName(value = "sys_department")
public class SysDepartment implements Serializable {

    @Schema(description = "部门ID")
    @TableId(value = "dept_id",type = IdType.ASSIGN_UUID)
    private String deptId;

    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private String tenantId;

    @Schema(description = "部门名称")
    @TableField(value = "dept_name")
    private String deptName;

    @Schema(description = "父部门ID")
    @TableField(value = "parent_id")
    private String parentId;

    @Schema(description = "祖宗部门ID,以逗号分割")
    @TableField(value = "ancestors")
    private String ancestors;

    @Schema(description = "序号,用于排序")
    @TableField(value = "order_num")
    private Integer orderNum;

    @Schema(description = "是否禁用 1:是 0:否")
    @TableField(value = "`status`")
    private Integer status;

    @Schema(description = "是否删除 1:删除 0:未删除")
    @TableField(value = "is_delete")
    private Integer isDelete;

    @Schema(description = "备注")
    @TableField(value = "remark")
    private String remark;

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
     * 子部门列表
     */
    @TableField(exist = false)
    private List<SysDepartment> children;

    /**
     * 租户详情
     */
    @TableField(exist = false)
    private SysTenant sysTenant;
}
