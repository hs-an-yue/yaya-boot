package com.yaya.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件表 - sys_file
 */
@Data
@TableName(value = "sys_file")
public class SysFile implements Serializable{

    @Schema(description = "文件ID")
    @TableId(value = "file_id",type = IdType.ASSIGN_UUID)
    private String fileId;

    @Schema(description = "租户ID")
    @TableField(value = "tenant_id")
    private String tenantId;

    @Schema(description = "文件名")
    @TableField(value = "file_name")
    private String fileName;

    @Schema(description = "文件类型,文件后缀格式,没有点")
    @TableField(value = "file_type")
    private String fileType;

    @Schema(description = "操作用户ID")
    @TableField(value = "upload_user_id")
    private String uploadUserId;

    @Schema(description = "文件保存到服务器的地址")
    @TableField(value = "file_save_url")
    private String fileSaveUrl;

    @Schema(description = "用户访问文件的地址")
    @TableField(value = "file_server_url")
    private String fileServerUrl;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
