-- 创建数据库
CREATE DATABASE `yaya-boot` CHARACTER SET utf8mb4;
-- 切换数据库
USE `yaya-boot`;

-- 租户管理
CREATE TABLE `sys_tenant` (
    `tenant_id`               VARCHAR(100)    PRIMARY KEY    		            COMMENT '租户ID,主键',
    `tenant_name`             VARCHAR(255)    NOT NULL                          COMMENT '租户名称',
    `status`                  TINYINT(1)      NOT NULL DEFAULT 0                COMMENT '是否禁用 1:是 0:否',
    `remark`                  VARCHAR(255)    DEFAULT NULL                      COMMENT '备注',
    `create_by_id`            VARCHAR(100)                                      COMMENT '创建人ID,关联用户表(sys_user)主键',
    `update_by_id`            VARCHAR(100)                                      COMMENT '更新人ID,关联用户表(sys_user)主键',
    `create_time`             DATETIME        DEFAULT NOW()                     COMMENT '创建时间',
    `update_time`             TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT='租户管理表';

-- 添加索引
ALTER TABLE `sys_tenant` ADD INDEX tenant_name_index (`tenant_name`);

-- 部门表
CREATE TABLE `sys_department`(
    `dept_id`                       VARCHAR(100)    PRIMARY KEY     			    COMMENT '部门ID,主键',
    `tenant_id`                     VARCHAR(100)    NOT NULL    		            COMMENT '租户ID',
    `dept_name`                     VARCHAR(100)    NOT NULL                        COMMENT '部门名称',
    `parent_id`                     VARCHAR(100)    DEFAULT 0                       COMMENT '父ID,自关联部门表主键',
    `ancestors`                     VARCHAR(100)                                    COMMENT '祖宗ID列表,用逗号(,)分割,所有部门的祖宗ID都从最顶部开始,从0开始',
    `order_num`                     INT             DEFAULT 0                       COMMENT '排序序号',
    `status`                        TINYINT(1)      NOT NULL 	DEFAULT 0           COMMENT '是否禁用 1:是 0:否',
	`remark`            			VARCHAR(255)    DEFAULT NULL                    COMMENT '备注',
    `create_by_id`                  VARCHAR(100)                                    COMMENT '创建人ID,关联用户表(sys_user)主键',
    `update_by_id`                  VARCHAR(100)                                    COMMENT '更新人ID,关联用户表(sys_user)主键',
    `create_time`                   DATETIME        DEFAULT NOW()                   COMMENT '创建时间',
    `update_time`                   TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET utf8mb4 COMMENT = '部门表';

-- 添加索引
ALTER TABLE `sys_department` ADD INDEX tenant_id_index (`tenant_id`);
ALTER TABLE `sys_department` ADD INDEX parent_id_index (`parent_id`);
ALTER TABLE `sys_department` ADD INDEX dept_name_index (`dept_name`);
ALTER TABLE `sys_department` ADD INDEX create_time_index (`create_time`);

-- 角色表
CREATE TABLE `sys_role` (
    `role_id`           VARCHAR(100)    PRIMARY KEY						COMMENT '角色ID,主键',
    `role_name`         VARCHAR(100)    NOT NULL                        COMMENT '角色名',
    `role_symbol`       VARCHAR(100)    NOT NULL                        COMMENT '角色符号',
    `tenant_id`         VARCHAR(100)    NOT NULL    		            COMMENT '租户ID',
    `status`            TINYINT(1)      NOT NULL 		DEFAULT 0       COMMENT '是否禁用 1:是 0:否',
    `remark`            VARCHAR(255)    DEFAULT NULL                    COMMENT '备注',
    `create_by_id`      VARCHAR(100)                                    COMMENT '创建人ID,关联用户表(sys_user)主键',
    `update_by_id`      VARCHAR(100)                                    COMMENT '更新人ID,关联用户表(sys_user)主键',
    `create_time`       DATETIME        DEFAULT NOW()                   COMMENT '创建时间',
    `update_time`       TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT='角色表';

-- 添加索引
ALTER TABLE `sys_role` ADD INDEX tenant_id_index (`tenant_id`);
ALTER TABLE `sys_role` ADD INDEX dept_name_index (`role_name`);

-- 用户表
CREATE TABLE `sys_user`(
    `user_id`                     VARCHAR(100)    PRIMARY KEY                     COMMENT '用户ID,主键',
    `dept_id`                     VARCHAR(100)                                    COMMENT '部门ID,关联部门表(department)主键',
    `role_id`                     VARCHAR(100)                                    COMMENT '角色ID,关联角色表(role)主键',
    `tenant_id`                   VARCHAR(100)    NOT NULL    		              COMMENT '租户ID',
    `username`                    VARCHAR(100)    NOT NULL                        COMMENT '用户名称',
    `phone`                       VARCHAR(50)     UNIQUE          NOT NULL        COMMENT '手机号,唯一',
    `email`                       VARCHAR(255)    UNIQUE                          COMMENT '邮箱,唯一',
    `password`                    VARCHAR(255)    NOT NULL                        COMMENT '密码',
    `sex`                         TINYINT(1)      DEFAULT 1                       COMMENT '性别 男:1,女:0',
    `avatar`                      VARCHAR(255)    DEFAULT 'file/avatar.png'       COMMENT '头像地址',
    `is_enabled`                  TINYINT(1)      DEFAULT 1                       COMMENT '账号是否可用 1:可用 0:不可用',
	`remark`            		  VARCHAR(255)    DEFAULT NULL                    COMMENT '备注',
    `create_by_id`                VARCHAR(100)                                    COMMENT '创建人ID,关联用户表(user)主键',
    `update_by_id`                VARCHAR(100)                                    COMMENT '更新人ID,关联用户表(user)主键',
    `create_time`                 DATETIME        DEFAULT NOW()                   COMMENT '创建时间',
    `expired_time`                DATETIME        NOT NULL                        COMMENT '过期时间',
    `update_time`                 TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT = '用户表';

-- 添加索引
ALTER TABLE `sys_user` ADD INDEX dept_id_index (`dept_id`);
ALTER TABLE `sys_user` ADD INDEX tenant_id_index (`tenant_id`);
ALTER TABLE `sys_user` ADD INDEX username_index (`username`);
ALTER TABLE `sys_user` ADD INDEX phone_index (`phone`);
ALTER TABLE `sys_user` ADD INDEX create_time_index (`create_time`);

-- 权限表
CREATE TABLE `sys_permission` (
    `permission_id`         VARCHAR(100)    PRIMARY KEY    			        COMMENT '权限ID,主键',
    `permission_name`       VARCHAR(100)    NOT NULL                        COMMENT '权限名',
    `permission_symbol`     VARCHAR(100)    NOT NULL                        COMMENT '权限符号',
    `tenant_id`             VARCHAR(100)    NOT NULL    		            COMMENT '租户ID',
    `status`                TINYINT(1)      NOT NULL DEFAULT 0              COMMENT '是否禁用 1:是 0:否',
    `remark`                VARCHAR(255)    DEFAULT NULL                    COMMENT '备注',
    `create_by_id`          VARCHAR(100)                                    COMMENT '创建人ID,关联用户表(sys_user)主键',
    `update_by_id`          VARCHAR(100)                                    COMMENT '更新人ID,关联用户表(sys_user)主键',
    `create_time`           DATETIME        DEFAULT NOW()                   COMMENT '创建时间',
    `update_time`           TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT='权限表';

-- 角色权限关联表
CREATE TABLE `sys_role_permission` (
    `role_id`         VARCHAR(100)          NOT NULL        COMMENT '角色ID',
    `permission_id`   VARCHAR(100)          NOT NULL        COMMENT '权限ID',
    PRIMARY KEY (`role_id`,`permission_id`)
)CHARACTER SET=utf8mb4 COMMENT='角色权限关联表';

-- 菜单表
CREATE TABLE `sys_menu` (
    `menu_id`         VARCHAR(100)    PRIMARY KEY                     COMMENT '菜单ID,主键',
    `menu_title`      VARCHAR(100)    NOT NULL                        COMMENT '菜单标题',
    `menu_icon`       VARCHAR(100)    DEFAULT NULL                    COMMENT '菜单图标',
    `menu_type`       INT             NOT NULL                        COMMENT '菜单类型 1:目录2:菜单3:按钮',
    `perms`           VARCHAR(100)    DEFAULT NULL                    COMMENT '菜单权限,当menu_type为3时有效,为按钮添加权限标识',
    `menu_url`        VARCHAR(521)    DEFAULT NULL                    COMMENT '菜单跳转地址',
    `menu_level`      INT             NOT NULL DEFAULT 1              COMMENT '菜单级别 eg:1:一级菜单 2:二级菜单 3:菜单菜单,从1开始',
    `parent_id`       VARCHAR(100)    NOT NULL DEFAULT 0              COMMENT '父ID,最顶层从0开始',
    `order_num`       INT             DEFAULT 1                       COMMENT '排序序号,从1开始',
    `status`          INT             NOT NULL DEFAULT 0              COMMENT '是否禁用 1:是 0:否',
    `create_by_id`    VARCHAR(100)                                    COMMENT '创建人ID,关联用户表(sys_user)主键',
    `update_by_id`    VARCHAR(100)                                    COMMENT '更新人ID,关联用户表(sys_user)主键',
    `create_time`     DATETIME        DEFAULT NOW()                   COMMENT '创建时间',
    `update_time`     TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT='菜单表';

-- 添加索引
ALTER TABLE `sys_menu` ADD INDEX parent_id_index (`parent_id`);
ALTER TABLE `sys_menu` ADD INDEX menu_title_index (`menu_title`);

-- 角色菜单关联表
CREATE TABLE `sys_role_menu` (
    `role_id`         VARCHAR(100)          NOT NULL        COMMENT '角色ID',
    `menu_id`         VARCHAR(100)          NOT NULL        COMMENT '菜单ID',
    PRIMARY KEY (`role_id`,`menu_id`)
)CHARACTER SET=utf8mb4 COMMENT='角色菜单关联表';

-- 日志记录表
CREATE TABLE `sys_operation_log` (
    `operation_log_id`        VARCHAR(100)    PRIMARY KEY    				  COMMENT '操作日志ID,主键',
    `tenant_id`               VARCHAR(100)              		              COMMENT '租户ID',
    `log_type`                INT             NOT NULL                        COMMENT '日志类型 1:登陆日志 2:其它操作日志',
    `operation_user_id`       VARCHAR(100)                                    COMMENT '操作用户ID',
    `operation_url`           VARCHAR(255)                                    COMMENT '请求地址',
    `operation_params`        TEXT                                            COMMENT '请求参数',
    `ip`                      VARCHAR(255)                                    COMMENT '客户端IP',
    `os`                      VARCHAR(100)                                    COMMENT '客户端操作系统',
    `browser`                 VARCHAR(100)                                    COMMENT '客户端浏览器',
    `address`                 VARCHAR(255)                                    COMMENT '客户端定位',
    `track_id`                VARCHAR(255)                                    COMMENT '日志链路ID',
    `status`                  TINYINT(1)      DEFAULT 1                       COMMENT '请求状态 1:成功 0:失败',
    `error_msg`               TEXT                                            COMMENT '错误堆栈信息',
    `operation_time`          DATETIME        DEFAULT NOW()                   COMMENT '操作时间',
    `update_time`             TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT='日志记录表';

-- 添加索引
ALTER TABLE `sys_operation_log` ADD INDEX log_type_index (`log_type`);
ALTER TABLE `sys_operation_log` ADD INDEX tenant_id_index (`tenant_id`);
ALTER TABLE `sys_operation_log` ADD INDEX track_id_index (`track_id`);

-- 文件管理表
CREATE TABLE `sys_file` (
    `file_id`               VARCHAR(100)    PRIMARY KEY    					            COMMENT '文件ID,主键',
    `tenant_id`             VARCHAR(100)    NOT NULL    		                        COMMENT '租户ID',
    `file_name`             VARCHAR(255)    NOT NULL                                    COMMENT '文件名称',
    `file_type`             VARCHAR(50)     NOT NULL                                    COMMENT '文件类型,文件后缀格式,没有点',
    `upload_user_id`        VARCHAR(100)    NOT NULL                                    COMMENT '操作用户ID',
    `file_save_url`         VARCHAR(500)    NOT NULL                                    COMMENT '文件保存到服务器的地址',
    `file_server_url`       VARCHAR(500)    NOT NULL                                    COMMENT '用户访问文件的地址',
    `create_time`           DATETIME        DEFAULT NOW()                               COMMENT '上传时间',
    `update_time`           TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT='文件管理表';

-- 添加索引
ALTER TABLE `sys_file` ADD INDEX file_name_index (`file_name`);
ALTER TABLE `sys_file` ADD INDEX tenant_id_index (`tenant_id`);

-- 公告表
CREATE TABLE `sys_notice` (
    `notice_id`             VARCHAR(100)    PRIMARY KEY    					            COMMENT '公告ID,主键',
    `notice_title`          VARCHAR(100)    NOT NULL    		                        COMMENT '公告标题',
    `notice_content`        LONGTEXT        NOT NULL                                    COMMENT '公告内容,可以是文字，图片，视频，音频，表格等多媒体内容',
    `notice_level`          TINYINT(1)      NOT NULL                                    COMMENT '公告级别: 0-普通, 1-重要, 2-紧急',
    `notice_type`           TINYINT(1)      NOT NULL                                    COMMENT '公告类型: 1-通知, 2-新闻, 3-活动, 4-公示',
    `publish_user_id`       VARCHAR(100)    NOT NULL                                    COMMENT '发布人ID',
    `is_top`                TINYINT(1)      NOT NULL    DEFAULT '0'                     COMMENT '是否置顶: 0-否, 1-是',
    `create_time`           DATETIME        DEFAULT NOW()                               COMMENT '上传时间',
    `update_time`           TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间'
)CHARACTER SET=utf8mb4 COMMENT='公告表';

-- 公告用户关联表
CREATE TABLE `sys_notice_user` (
    `notice_id`         VARCHAR(100)          NOT NULL                                  COMMENT '公告ID',
    `user_id`           VARCHAR(100)          NOT NULL                                  COMMENT '用户ID',
    `read_time`         DATETIME              DEFAULT NULL                              COMMENT '阅读时间',
    `status`            TINYINT(1)            NOT NULL    DEFAULT '0'                   COMMENT '阅读状态: 0-未阅读, 1-已阅读',
    PRIMARY KEY (`notice_id`,`user_id`)
)CHARACTER SET=utf8mb4 COMMENT='公告用户关联表';