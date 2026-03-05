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
 * 菜单表 - sys_menu
 */
@Data
@TableName(value = "`sys_menu`")
public class SysMenu  implements Serializable {

    @Schema(description = "菜单ID")
    @TableId(value = "menu_id",type = IdType.ASSIGN_UUID)
    private String menuId;

    @Schema(description = "菜单标题")
    @TableField(value = "menu_title")
    private String menuTitle;

    @Schema(description = "菜单图标")
    @TableField(value = "menu_icon")
    private String menuIcon;

    @Schema(description = "菜单类型 1:目录2:菜单3:按钮")
    @TableField(value = "menu_type")
    private Integer menuType;

    @Schema(description = "菜单权限,当menu_type为3时有效,为按钮添加权限标识")
    @TableField(value = "perms")
    private String perms;

    @Schema(description = "菜单跳转地址")
    @TableField(value = "menu_url")
    private String menuUrl;

    @Schema(description = "菜单级别 eg:1:一级菜单 2:二级菜单 3:菜单菜单,从1开始")
    @TableField(value = "menu_level")
    private Integer menuLevel;

    @Schema(description = "父部门ID")
    @TableField(value = "parent_id")
    private String parentId;

    @Schema(description = "排序序号")
    @TableField(value = "order_num")
    private Integer orderNum;

    @Schema(description = "是否禁用 1:是 0:否")
    @TableField(value = "`status`")
    private Integer status;

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
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children;
}
