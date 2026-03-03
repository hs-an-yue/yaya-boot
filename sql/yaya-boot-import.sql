/*
 Navicat Premium Dump SQL

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : localhost:3306
 Source Schema         : yaya-boot

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 02/03/2026 14:20:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `dept_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门ID,主键',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `parent_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '父ID,自关联部门表主键',
  `ancestors` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '祖宗ID列表,用逗号(,)分割,所有部门的祖宗ID都从最顶部开始,从0开始',
  `order_num` int NULL DEFAULT 0 COMMENT '排序序号',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1:删除 0:未删除',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `tenant_id_index`(`tenant_id` ASC) USING BTREE,
  INDEX `parent_id_index`(`parent_id` ASC) USING BTREE,
  INDEX `dept_name_index`(`dept_name` ASC) USING BTREE,
  INDEX `create_time_index`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('09cea060267d57b8ef9894bb86680ae4', '2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+科技集团', '0', '0', 2, 0, 0, 'AI+培训', '1', '1', '2026-02-27 14:26:44', '2026-03-02 14:18:53');
INSERT INTO `sys_department` VALUES ('0ec701147578464df702fa716db9d797', '2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+南京分公司', '09cea060267d57b8ef9894bb86680ae4', '0,09cea060267d57b8ef9894bb86680ae4', 2, 0, 0, '萌新AI+南京分公司', '1', '1', '2026-02-27 14:30:46', '2026-03-02 14:19:06');
INSERT INTO `sys_department` VALUES ('1', '1', '知芽M+实业集团', '0', '0', 1, 0, 0, '一级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:13');
INSERT INTO `sys_department` VALUES ('10', '1', '财务部', '4', '0,1,4', 2, 0, 0, '三级', '1', '1', '2026-02-09 15:01:50', '2026-02-09 15:01:50');
INSERT INTO `sys_department` VALUES ('2', '1', '知芽M+南京分公司', '1', '0,1', 1, 0, 0, '二级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:22');
INSERT INTO `sys_department` VALUES ('215a1224f1c24b4ffb8820af3059c334', '2da1f1492eb7df4e8c079f3ff540d5ae', '市场营销部', 'ee918991cbdc92e895bcf35a8a6501f5', '0,09cea060267d57b8ef9894bb86680ae4,ee918991cbdc92e895bcf35a8a6501f5', 1, 0, 0, '市场营销部', '1', '1', '2026-02-27 14:31:15', '2026-02-27 15:12:19');
INSERT INTO `sys_department` VALUES ('3', '1', '知芽M+苏州分公司', '1', '0,1', 2, 0, 0, '二级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:27');
INSERT INTO `sys_department` VALUES ('4', '1', '知芽M+无锡分公司', '1', '0,1', 3, 0, 0, '二级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:32');
INSERT INTO `sys_department` VALUES ('5', '1', '行政部', '2', '0,1,2', 1, 0, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-27 15:59:26');
INSERT INTO `sys_department` VALUES ('6', '1', '人事部', '2', '0,1,2', 2, 0, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-27 16:04:03');
INSERT INTO `sys_department` VALUES ('6d0e1e5bc180f958fa73c61146e5ea74', '2da1f1492eb7df4e8c079f3ff540d5ae', '人事部', '0ec701147578464df702fa716db9d797', '0,09cea060267d57b8ef9894bb86680ae4,0ec701147578464df702fa716db9d797', 1, 0, 0, '人事部', '1', '1', '2026-02-27 14:30:58', '2026-02-27 15:54:47');
INSERT INTO `sys_department` VALUES ('7', '1', '行政部', '3', '0,1,3', 1, 0, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-09 15:01:49');
INSERT INTO `sys_department` VALUES ('7c643e9ecd813e5a8a84a6ab31f0b74b', '2da1f1492eb7df4e8c079f3ff540d5ae', '财务部', '0ec701147578464df702fa716db9d797', '0,09cea060267d57b8ef9894bb86680ae4,0ec701147578464df702fa716db9d797', 2, 0, 0, '财务部', '1', '1', '2026-02-27 14:31:06', '2026-02-27 15:49:12');
INSERT INTO `sys_department` VALUES ('8', '1', '财务部', '3', '0,1,3', 2, 0, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-09 15:01:49');
INSERT INTO `sys_department` VALUES ('9', '1', '公关部', '4', '0,1,4', 1, 0, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-09 15:01:49');
INSERT INTO `sys_department` VALUES ('b8a128efd1f5991681e701b2c48aca86', '2da1f1492eb7df4e8c079f3ff540d5ae', '产品研发部', 'ee918991cbdc92e895bcf35a8a6501f5', '0,09cea060267d57b8ef9894bb86680ae4,ee918991cbdc92e895bcf35a8a6501f5', 2, 0, 0, '产品研发部', '1', '1', '2026-02-27 14:31:26', '2026-02-27 15:12:01');
INSERT INTO `sys_department` VALUES ('ee918991cbdc92e895bcf35a8a6501f5', '2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+北京分公司', '09cea060267d57b8ef9894bb86680ae4', '0,09cea060267d57b8ef9894bb86680ae4', 1, 0, 0, '萌新AI+北京分公司', '1', '1', '2026-02-27 14:30:33', '2026-03-02 14:19:00');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `file_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件ID,主键',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名称',
  `file_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件类型,文件后缀格式,没有点',
  `upload_user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作用户ID',
  `file_save_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件保存到服务器的地址',
  `file_server_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户访问文件的地址',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`file_id`) USING BTREE,
  INDEX `file_name_index`(`file_name` ASC) USING BTREE,
  INDEX `tenant_id_index`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单ID,主键',
  `menu_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单标题',
  `menu_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `menu_type` int NOT NULL COMMENT '菜单类型 1:目录2:菜单3:按钮',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单权限,当menu_type为3时有效,为按钮添加权限标识',
  `menu_url` varchar(521) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单跳转地址',
  `menu_level` int NOT NULL DEFAULT 1 COMMENT '菜单级别 eg:1:一级菜单 2:二级菜单 3:菜单菜单,从1开始',
  `parent_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '父ID,最顶层从0开始',
  `order_num` int NULL DEFAULT 1 COMMENT '排序序号,从1开始',
  `status` int NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1:删除 0:未删除',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `parent_id_index`(`parent_id` ASC) USING BTREE,
  INDEX `menu_title_index`(`menu_title` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('15', '菜单管理', 'layui-icon layui-icon-layouts', 2, NULL, 'views/menu-list.html', 1, '2', 4, 0, 0, '1', '1', '2026-02-09 15:09:43', '2026-02-09 15:16:29');
INSERT INTO `sys_menu` VALUES ('2', '系统配置', 'layui-icon layui-icon-set', 1, NULL, '', 1, '0', 2, 0, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'layui-icon layui-icon-friends', 2, NULL, 'views/user-list.html', 1, '2', 1, 0, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('4', '部门管理', 'layui-icon layui-icon-component', 2, NULL, 'views/dept-list.html', 1, '2', 2, 0, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('5', '日志监控', 'layui-icon layui-icon-engine', 1, NULL, '', 1, '0', 3, 0, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('6', '登陆日志', 'layui-icon layui-icon-form', 2, NULL, 'views/login-log.html', 1, '5', 1, 0, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('60c1536310c6f201b683138fa38ab744', '租户管理', 'layui-icon layui-icon-github', 2, '', 'views/tenant-list.html', 2, '2', 4, 0, 0, '1', '1', '2026-02-09 18:23:36', '2026-02-09 18:23:36');
INSERT INTO `sys_menu` VALUES ('7', '系统日志', 'layui-icon layui-icon-table', 2, NULL, 'views/system-log.html', 1, '5', 2, 0, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('fe9ee4bd63de119d8cf5a8889bc1a0a4', '角色管理', 'layui-icon layui-icon-gitee', 2, '', 'views/role-list.html', 2, '2', 3, 0, 0, '1', '1', '2026-02-09 15:16:08', '2026-02-09 15:16:08');

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `operation_log_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作日志ID,主键',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户ID',
  `log_type` int NOT NULL COMMENT '日志类型 1:登陆日志 2:其它操作日志',
  `operation_user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作用户ID',
  `operation_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求地址',
  `operation_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端IP',
  `os` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端操作系统',
  `browser` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端浏览器',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端定位',
  `track_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日志链路ID',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '请求状态 1:成功 0:失败',
  `error_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '错误堆栈信息',
  `operation_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`operation_log_id`) USING BTREE,
  INDEX `log_type_index`(`log_type` ASC) USING BTREE,
  INDEX `tenant_id_index`(`tenant_id` ASC) USING BTREE,
  INDEX `track_id_index`(`track_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日志记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES ('037b1bc091803472728cdbaf512e6f0a', '1', 2, '1', '/updateSysDepartment', '[{\"deptId\":\"3\",\"deptName\":\"知芽M+苏州分公司\",\"remark\":\"二级\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '30950c27-aa1f-4013-84cc-ad01eee457d2', 1, NULL, '2026-03-02 14:18:27', '2026-03-02 14:18:27');
INSERT INTO `sys_operation_log` VALUES ('0d0e86bcfcefa161ae3dbc6926217523', '1', 2, '1', '/getSysRolePage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '01bf204c-1659-4531-a88c-598503900206', 1, NULL, '2026-03-02 14:15:32', '2026-03-02 14:15:32');
INSERT INTO `sys_operation_log` VALUES ('0fd311c71395af1cb57ae1c6c85c1e7c', '1', 2, '1', '/getSysDepartmentTree', '[\"2da1f1492eb7df4e8c079f3ff540d5ae\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'df9a6a9b-207d-4589-ba39-4e1d51a84bea', 1, NULL, '2026-03-02 14:18:53', '2026-03-02 14:18:53');
INSERT INTO `sys_operation_log` VALUES ('12d948e9a0e1bc2dcb57d7071d5038ea', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '5409f155-4c67-46f8-a2cd-6d867065c95b', 1, NULL, '2026-03-02 14:15:32', '2026-03-02 14:15:32');
INSERT INTO `sys_operation_log` VALUES ('17ded71cf582cde3fdafde5df8cbd641', '1', 2, '1', '/updateSysTenant', '[{\"tenantId\":\"2da1f1492eb7df4e8c079f3ff540d5ae\",\"tenantName\":\"萌新AI+\",\"remark\":\"萌新AI+\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'bc279867-ac00-45ac-85c7-f4243b67c29b', 1, NULL, '2026-03-02 14:17:03', '2026-03-02 14:17:03');
INSERT INTO `sys_operation_log` VALUES ('20ff6243df8446b85874acd4b26685bd', '1', 2, '1', '/getSysRoleListByTenantId', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '1ab3319a-47c8-4c63-ab8d-efa1ff995ee4', 1, NULL, '2026-03-02 14:15:15', '2026-03-02 14:15:15');
INSERT INTO `sys_operation_log` VALUES ('2fab935936252b7aa121179431983549', '1', 2, '1', '/getSysDepartmentTree', '[\"2da1f1492eb7df4e8c079f3ff540d5ae\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '21bf33e7-4d97-4007-aa44-2813cb70d71b', 1, NULL, '2026-03-02 14:17:37', '2026-03-02 14:17:37');
INSERT INTO `sys_operation_log` VALUES ('37415f4c40c7f011566c2cab1dfe285e', '1', 2, '1', '/getSysUserPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'f22dbdf8-8426-472b-9b44-90b5b3d6d8fb', 1, NULL, '2026-03-02 14:15:11', '2026-03-02 14:15:11');
INSERT INTO `sys_operation_log` VALUES ('3b93419b80469474a8136e4995f87feb', '1', 2, '1', '/updateSysDepartment', '[{\"deptId\":\"ee918991cbdc92e895bcf35a8a6501f5\",\"deptName\":\"萌新AI+北京分公司\",\"remark\":\"萌新AI+北京分公司\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '264679f5-0fbd-4672-9147-487689ff3653', 1, NULL, '2026-03-02 14:19:00', '2026-03-02 14:19:00');
INSERT INTO `sys_operation_log` VALUES ('442954f73d34de0d9e3adf8864fda4a8', '1', 2, '1', '/getSysDepartmentTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '11e831f5-8d6b-4437-9fc8-662550cec13e', 1, NULL, '2026-03-02 14:19:59', '2026-03-02 14:19:59');
INSERT INTO `sys_operation_log` VALUES ('4514f56062ba2b0fd2b89da6ceacaafa', '1', 2, '1', '/updateSysDepartment', '[{\"deptId\":\"4\",\"deptName\":\"知芽M+无锡分公司\",\"remark\":\"二级\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '39ed1c7c-39dc-4540-95de-d3b8be59f44f', 1, NULL, '2026-03-02 14:18:32', '2026-03-02 14:18:32');
INSERT INTO `sys_operation_log` VALUES ('486267c6f886c6ec7e44ffc28b179798', '1', 2, '1', '/getSysDepartmentTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'df6680b4-74a4-438a-b0e5-036eb7941165', 1, NULL, '2026-03-02 14:17:33', '2026-03-02 14:17:33');
INSERT INTO `sys_operation_log` VALUES ('4ebc1e014b266e6bdfc5a772476f1793', '1', 2, '1', '/getSysDepartmentTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '7b8f4ea5-7e3f-411d-97d5-503fa19ad7bc', 1, NULL, '2026-03-02 14:17:28', '2026-03-02 14:17:28');
INSERT INTO `sys_operation_log` VALUES ('5158d9d8622b2d2de20680999b6d347a', '1', 2, '1', '/getSysMenuTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '92f32ba5-242a-414b-a380-7ba153558259', 1, NULL, '2026-03-02 14:19:20', '2026-03-02 14:19:20');
INSERT INTO `sys_operation_log` VALUES ('56400bf2296e8aa4961efc34aa9f8231', '1', 2, '1', '/getSysUserPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'f3c977c1-7875-4de0-8b2b-2b706f8964e3', 1, NULL, '2026-03-02 14:19:59', '2026-03-02 14:19:59');
INSERT INTO `sys_operation_log` VALUES ('56e3f082d873f46590eba52afce7ea85', '1', 2, '1', '/getSysDepartmentTree', '[\"1\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'e3932b41-54a9-47c4-a277-6e85e50916bc', 1, NULL, '2026-03-02 14:17:41', '2026-03-02 14:17:41');
INSERT INTO `sys_operation_log` VALUES ('5dad6ee60f3e8853af87c80ed37db266', '1', 2, '1', '/updateSysDepartment', '[{\"deptId\":\"0ec701147578464df702fa716db9d797\",\"deptName\":\"萌新AI+南京分公司\",\"remark\":\"萌新AI+南京分公司\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '1fc78672-c3ef-422d-9e00-fdc13d0dc552', 1, NULL, '2026-03-02 14:19:06', '2026-03-02 14:19:06');
INSERT INTO `sys_operation_log` VALUES ('64d4272cadd9d11c6e7b0bc8cf14d2a9', '1', 2, '1', '/updateSysDepartment', '[{\"deptId\":\"2\",\"deptName\":\"知芽M+南京分公司\",\"remark\":\"二级\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'bc25c5a3-84a3-4689-823a-faa0fb7706a5', 1, NULL, '2026-03-02 14:18:22', '2026-03-02 14:18:22');
INSERT INTO `sys_operation_log` VALUES ('65cf62d18754fcb8e1f2e1030c03f2e4', '1', 2, '1', '/getSysTenantPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '4c806fa3-aa27-4e38-b1a9-8d221da34420', 1, NULL, '2026-03-02 14:15:41', '2026-03-02 14:15:41');
INSERT INTO `sys_operation_log` VALUES ('6a9d61a4f48c766c3a372cf03b785ec1', '1', 2, '1', '/getSysDepartmentTree', '[\"\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'd4d5579d-5169-415d-88a6-cb84b19a9ccc', 1, NULL, '2026-03-02 14:18:32', '2026-03-02 14:18:32');
INSERT INTO `sys_operation_log` VALUES ('6ec14a6e52e461eccab9d0b60645bb2b', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '08420c01-c380-4979-a74d-6833763515f0', 1, NULL, '2026-03-02 14:19:19', '2026-03-02 14:19:19');
INSERT INTO `sys_operation_log` VALUES ('747966be71f6a9981572466712145766', '1', 2, '1', '/getSysTenantPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '55ce788f-3a0c-40c9-847e-757c08d0192a', 1, NULL, '2026-03-02 14:16:08', '2026-03-02 14:16:08');
INSERT INTO `sys_operation_log` VALUES ('8020f7e8c70e67a921d6e6991c4510a0', '1', 2, '1', '/getSysDepartmentTree', '[\"\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '6a5633be-9cd8-43b0-8ab5-380ef494fcce', 1, NULL, '2026-03-02 14:18:22', '2026-03-02 14:18:22');
INSERT INTO `sys_operation_log` VALUES ('811f5f6ca8595d14df4ef36224878fbc', '1', 2, '1', '/getSysDepartmentTree', '[\"2da1f1492eb7df4e8c079f3ff540d5ae\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '83b38e21-db50-4ecb-b313-5af194da7aee', 1, NULL, '2026-03-02 14:18:40', '2026-03-02 14:18:40');
INSERT INTO `sys_operation_log` VALUES ('85728d518e3e3b5a76205da967666e12', '1', 2, '1', '/getSysUserPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '3a44397f-4878-41f0-8f4e-e1e09f1095d8', 1, NULL, '2026-03-02 14:15:01', '2026-03-02 14:15:01');
INSERT INTO `sys_operation_log` VALUES ('86912fea6478b1110f43b2c98844d344', '1', 2, '1', '/updateSysTenant', '[{\"tenantId\":\"1\",\"tenantName\":\"知芽M+\",\"remark\":\"平台管理租户\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '4fdf5e3c-9101-4494-9557-6a3fc922273f', 1, NULL, '2026-03-02 14:16:07', '2026-03-02 14:16:07');
INSERT INTO `sys_operation_log` VALUES ('8718fcc82c79fad1114c1cca53ee8866', '1', 2, '1', '/checkToken', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '7c42a9bc-53b3-45df-8049-f7e836daeb05', 1, NULL, '2026-03-02 14:15:01', '2026-03-02 14:15:01');
INSERT INTO `sys_operation_log` VALUES ('88fceabc6f08c4a8eb8e7c872cf3b8ff', '1', 2, '1', '/getSysDepartmentTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'd155421b-8373-47c9-90e8-cfb20ab7a88c', 1, NULL, '2026-03-02 14:15:01', '2026-03-02 14:15:01');
INSERT INTO `sys_operation_log` VALUES ('8b3a14e589cf67a5682ef8c5ab61c8ad', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '03f5acbb-b919-4c37-8c7f-cf3467f667d2', 1, NULL, '2026-03-02 14:15:09', '2026-03-02 14:15:09');
INSERT INTO `sys_operation_log` VALUES ('8ced46437f582cd37c12f6226da6dbed', '1', 2, '1', '/getSysDepartmentTree', '[\"\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'b432c727-1d66-4271-a6f5-60769aa61203', 1, NULL, '2026-03-02 14:19:10', '2026-03-02 14:19:10');
INSERT INTO `sys_operation_log` VALUES ('8d6b91481171d8d8bba5e1a25eaea0d4', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '15523b80-4051-47b2-a15b-3d14b5535545', 1, NULL, '2026-03-02 14:15:15', '2026-03-02 14:15:15');
INSERT INTO `sys_operation_log` VALUES ('9545ffc1d41eed0318937ef929298f2f', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'b1ba8cc4-80a4-44d8-94ee-8c1d3df5e925', 1, NULL, '2026-03-02 14:19:16', '2026-03-02 14:19:16');
INSERT INTO `sys_operation_log` VALUES ('95ad57e9762c6470acdadf75e08a65cf', '1', 2, '1', '/getSysRoleListByTenantId', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '77bb6c90-c15b-40b4-8b5d-c590f78d6d30', 1, NULL, '2026-03-02 14:15:09', '2026-03-02 14:15:09');
INSERT INTO `sys_operation_log` VALUES ('9a59d30d7ccd3e7d30b5770abea37f37', '1', 2, '1', '/updateSysDepartment', '[{\"deptId\":\"09cea060267d57b8ef9894bb86680ae4\",\"deptName\":\"萌新AI+科技集团\",\"remark\":\"AI+培训\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '09578ee5-0018-4d68-a4c8-cfa5a7f86bfc', 1, NULL, '2026-03-02 14:18:53', '2026-03-02 14:18:53');
INSERT INTO `sys_operation_log` VALUES ('9d11e5e40e058f9e87d02d6f555b8963', '1', 2, '1', '/getSysTenantPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'aa112265-08c9-4916-9dd6-19d39086a25a', 1, NULL, '2026-03-02 14:19:21', '2026-03-02 14:19:21');
INSERT INTO `sys_operation_log` VALUES ('a942dcc1903c4d864c3e0b2d2ef56bda', '1', 2, '1', '/getSysDepartmentTree', '[\"\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '892ac609-899e-4764-b713-f96d2f7be35b', 1, NULL, '2026-03-02 14:18:27', '2026-03-02 14:18:27');
INSERT INTO `sys_operation_log` VALUES ('a9869c15f7964864f0c6ee76f718cd8d', '1', 2, '1', '/getSysDepartmentTree', '[\"\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'cf08b8d6-494a-42c5-a337-7e8ebb5a9d19', 1, NULL, '2026-03-02 14:18:13', '2026-03-02 14:18:13');
INSERT INTO `sys_operation_log` VALUES ('a9d7320d2b988044e0f2f88f7199713f', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'd402fad0-903b-4dc8-ac4a-00226ef7f320', 1, NULL, '2026-03-02 14:17:28', '2026-03-02 14:17:28');
INSERT INTO `sys_operation_log` VALUES ('afedc1ec8f93e0bf7a5e59c63eecf654', '1', 2, '1', '/getSysDepartmentTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'ad31d8c2-97c2-4712-95cb-6bfe1a298fed', 1, NULL, '2026-03-02 14:15:15', '2026-03-02 14:15:15');
INSERT INTO `sys_operation_log` VALUES ('b3f6e045e9c97169c0391494093d612d', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '9474b240-1a50-433e-8242-3db272a6da0d', 1, NULL, '2026-03-02 14:17:23', '2026-03-02 14:17:23');
INSERT INTO `sys_operation_log` VALUES ('be6d89447af2c52853081172f69d7ffe', '1', 2, '1', '/getSysRolePage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '0e1b8f74-7f83-4556-a303-0449076cc821', 1, NULL, '2026-03-02 14:19:19', '2026-03-02 14:19:19');
INSERT INTO `sys_operation_log` VALUES ('bfc46143dc6aad5cd2a2b4651bdf35cf', '1', 2, '1', '/getSysMenuTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '3f4a84d7-b64e-43cd-b7c7-b661fbec3456', 1, NULL, '2026-03-02 14:15:37', '2026-03-02 14:15:37');
INSERT INTO `sys_operation_log` VALUES ('c562010c005d122587e20e732c09d13f', '1', 2, '1', '/getSysTenantPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'ff23a180-5ee0-4593-81e5-3a9b9120e4d9', 1, NULL, '2026-03-02 14:17:13', '2026-03-02 14:17:13');
INSERT INTO `sys_operation_log` VALUES ('c779a76cb93c782996f616ad203140ce', '1', 2, '1', '/getSysDepartmentTree', '[\"2da1f1492eb7df4e8c079f3ff540d5ae\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '6886ec8f-9285-4b00-96fa-1e423ca29add', 1, NULL, '2026-03-02 14:19:06', '2026-03-02 14:19:06');
INSERT INTO `sys_operation_log` VALUES ('cbad108091dfc8123c02865236b3ecf6', '1', 2, '1', '/getSysTenantAll', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'cdc981bb-69eb-46ea-8e79-805ec4e0bf4e', 1, NULL, '2026-03-02 14:17:33', '2026-03-02 14:17:33');
INSERT INTO `sys_operation_log` VALUES ('cd01d4d7d17674ae364a276716b9b507', '1', 2, '1', '/getSysRolePage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '7ab16a10-266e-4898-a44a-e3250e6434c8', 1, NULL, '2026-03-02 14:17:23', '2026-03-02 14:17:23');
INSERT INTO `sys_operation_log` VALUES ('d3dc44ad476dddc8f3b44ee1bc69cfac', '1', 2, '1', '/updateSysDepartment', '[{\"deptId\":\"1\",\"deptName\":\"知芽M+实业集团\",\"remark\":\"一级\"}]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'c1246c93-e1f2-445d-8a34-be737e7b969d', 1, NULL, '2026-03-02 14:18:13', '2026-03-02 14:18:13');
INSERT INTO `sys_operation_log` VALUES ('df34f18931a81801e468a45d572f3ecf', '1', 2, '1', '/getSysUserPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '45332e73-76b9-4919-9361-28147ae743dd', 1, NULL, '2026-03-02 14:15:31', '2026-03-02 14:15:31');
INSERT INTO `sys_operation_log` VALUES ('e347469113ec8b6ec3abb5c41a1fee54', '1', 2, '1', '/getSysDepartmentTree', '[\"\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '330777ee-366c-43dc-a111-e16a017aef00', 1, NULL, '2026-03-02 14:17:45', '2026-03-02 14:17:45');
INSERT INTO `sys_operation_log` VALUES ('e4f51f79246a10155f6860be381a07bf', '1', 2, '1', '/getSysDepartmentTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '4247b49a-0aee-4308-9ca3-77a77aec5fd5', 1, NULL, '2026-03-02 14:19:16', '2026-03-02 14:19:16');
INSERT INTO `sys_operation_log` VALUES ('e5ad1692a51d45f3ee5600d6c5fa503d', '1', 2, '1', '/getSysDepartmentTree', NULL, '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '0558a90e-6cc0-49df-a400-90311f6f673f', 1, NULL, '2026-03-02 14:15:09', '2026-03-02 14:15:09');
INSERT INTO `sys_operation_log` VALUES ('ea63e04a10506a3ddbdd3820f9420647', '1', 2, '1', '/getSysDepartmentTree', '[\"2da1f1492eb7df4e8c079f3ff540d5ae\",\"\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', 'd13a8a39-a563-49e1-be29-f9ef27240821', 1, NULL, '2026-03-02 14:19:00', '2026-03-02 14:19:00');
INSERT INTO `sys_operation_log` VALUES ('f45b6e17661d17ade60943c99cfd6dab', '1', 2, '1', '/getSysTenantPage', '[1,10]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '85c71754-d83c-4e6a-bbf4-33abd266a8aa', 1, NULL, '2026-03-02 14:17:04', '2026-03-02 14:17:04');
INSERT INTO `sys_operation_log` VALUES ('f7c0992c701a4b27f7695ad4a3e37a1a', '1', 2, '1', '/getAuthSysMenuTree', '[\"1\"]', '127.0.0.1', 'Windows 10 or Windows Server 2016', 'Chrome', '本机地址', '065b472d-b058-47dd-aa6a-da57bf4dfc6d', 1, NULL, '2026-03-02 14:15:01', '2026-03-02 14:15:01');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `permission_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限ID,主键',
  `permission_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名',
  `permission_symbol` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限符号',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1:删除 0:未删除',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID,主键',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `role_symbol` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色符号',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1:删除 0:未删除',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `tenant_id_index`(`tenant_id` ASC) USING BTREE,
  INDEX `dept_name_index`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '平台管理员', 'PLATFORM_ADMIN', '1', 0, 0, '管理整个平台', '1', '1', '2026-02-09 15:01:50', '2026-02-09 18:19:02');
INSERT INTO `sys_role` VALUES ('2', '运营管理员', 'OPERATION_ADMIN', '1', 0, 0, '管理平台租户', '1', '1', '2026-02-09 15:01:50', '2026-02-09 16:52:36');
INSERT INTO `sys_role` VALUES ('ce3387271274c1b318aad04767f7f21c', '普通角色', 'COMMON', '1', 0, 0, '普通角色', '1', '1', '2026-02-15 17:07:56', '2026-02-25 09:47:49');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `permission_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`  (
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID,主键',
  `tenant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户名称',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1:删除 0:未删除',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`tenant_id`) USING BTREE,
  INDEX `tenant_name_index`(`tenant_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '租户管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
INSERT INTO `sys_tenant` VALUES ('1', '知芽M+', 0, 0, '平台管理租户', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:16:07');
INSERT INTO `sys_tenant` VALUES ('2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+', 0, 0, '萌新AI+', '1', '1', '2026-02-27 15:10:45', '2026-03-02 14:17:03');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID,主键',
  `dept_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门ID,关联部门表(department)主键',
  `role_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色ID,关联角色表(role)主键',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名称',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号,唯一',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱,唯一',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `sex` tinyint(1) NULL DEFAULT 1 COMMENT '性别 男:1,女:0',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'file/avatar.png' COMMENT '头像地址',
  `is_account_non_expired` tinyint(1) NULL DEFAULT 0 COMMENT '账号是否过期 1:过期 0:未过期',
  `is_enabled` tinyint(1) NULL DEFAULT 1 COMMENT '账号是否可用 1:可用 0:不可用',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 1:删除 0:未删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expired_time` datetime NOT NULL COMMENT '过期时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE,
  INDEX `dept_id_index`(`dept_id` ASC) USING BTREE,
  INDEX `tenant_id_index`(`tenant_id` ASC) USING BTREE,
  INDEX `username_index`(`username` ASC) USING BTREE,
  INDEX `phone_index`(`phone` ASC) USING BTREE,
  INDEX `create_time_index`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '5', '1', '1', '管理员', 'admin', 'admin123@163.com', '$2a$10$iTBYWW89ZPbyQMowkfw9YubzrMgMgpEdCU3WgperYH1NCouKBdwBS', 1, 'file/avatar.png', 0, 1, 0, '备注', '1', '1', '2026-02-09 15:01:50', '2026-12-31 23:59:59', '2026-03-01 12:47:01');
INSERT INTO `sys_user` VALUES ('2', '6', '2', '1', '运营', 'operation', 'operation123@163.com', '$2a$10$iTBYWW89ZPbyQMowkfw9YubzrMgMgpEdCU3WgperYH1NCouKBdwBS', 1, 'file/avatar.png', 0, 1, 0, '备注', '1', '1', '2026-02-09 15:01:50', '2026-12-31 23:59:59', '2026-03-01 14:50:02');

SET FOREIGN_KEY_CHECKS = 1;
