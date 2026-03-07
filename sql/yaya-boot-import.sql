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

 Date: 07/03/2026 17:11:11
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('09cea060267d57b8ef9894bb86680ae4', '2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+科技集团', '0', '0', 2, 0, 'AI+培训', '1', '1', '2026-02-27 14:26:44', '2026-03-02 14:18:53');
INSERT INTO `sys_department` VALUES ('0ec701147578464df702fa716db9d797', '2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+南京分公司', '09cea060267d57b8ef9894bb86680ae4', '0,09cea060267d57b8ef9894bb86680ae4', 2, 0, '萌新AI+南京分公司', '1', '1', '2026-02-27 14:30:46', '2026-03-02 14:19:06');
INSERT INTO `sys_department` VALUES ('1', '1', '知芽M+实业集团', '0', '0', 1, 0, '一级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:13');
INSERT INTO `sys_department` VALUES ('10', '1', '财务部', '4', '0,1,4', 2, 0, '三级', '1', '1', '2026-02-09 15:01:50', '2026-02-09 15:01:50');
INSERT INTO `sys_department` VALUES ('2', '1', '知芽M+南京分公司', '1', '0,1', 1, 0, '二级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:22');
INSERT INTO `sys_department` VALUES ('215a1224f1c24b4ffb8820af3059c334', '2da1f1492eb7df4e8c079f3ff540d5ae', '市场营销部', 'ee918991cbdc92e895bcf35a8a6501f5', '0,09cea060267d57b8ef9894bb86680ae4,ee918991cbdc92e895bcf35a8a6501f5', 1, 0, '市场营销部', '1', '1', '2026-02-27 14:31:15', '2026-02-27 15:12:19');
INSERT INTO `sys_department` VALUES ('3', '1', '知芽M+苏州分公司', '1', '0,1', 2, 0, '二级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:27');
INSERT INTO `sys_department` VALUES ('4', '1', '知芽M+无锡分公司', '1', '0,1', 3, 0, '二级', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:18:32');
INSERT INTO `sys_department` VALUES ('5', '1', '行政部', '2', '0,1,2', 1, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-27 15:59:26');
INSERT INTO `sys_department` VALUES ('6', '1', '人事部', '2', '0,1,2', 2, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-27 16:04:03');
INSERT INTO `sys_department` VALUES ('6d0e1e5bc180f958fa73c61146e5ea74', '2da1f1492eb7df4e8c079f3ff540d5ae', '人事部', '0ec701147578464df702fa716db9d797', '0,09cea060267d57b8ef9894bb86680ae4,0ec701147578464df702fa716db9d797', 1, 0, '人事部', '1', '1', '2026-02-27 14:30:58', '2026-02-27 15:54:47');
INSERT INTO `sys_department` VALUES ('7', '1', '行政部', '3', '0,1,3', 1, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-09 15:01:49');
INSERT INTO `sys_department` VALUES ('7c643e9ecd813e5a8a84a6ab31f0b74b', '2da1f1492eb7df4e8c079f3ff540d5ae', '财务部', '0ec701147578464df702fa716db9d797', '0,09cea060267d57b8ef9894bb86680ae4,0ec701147578464df702fa716db9d797', 2, 0, '财务部', '1', '1', '2026-02-27 14:31:06', '2026-02-27 15:49:12');
INSERT INTO `sys_department` VALUES ('8', '1', '财务部', '3', '0,1,3', 2, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-09 15:01:49');
INSERT INTO `sys_department` VALUES ('9', '1', '公关部', '4', '0,1,4', 1, 0, '三级', '1', '1', '2026-02-09 15:01:49', '2026-02-09 15:01:49');
INSERT INTO `sys_department` VALUES ('b8a128efd1f5991681e701b2c48aca86', '2da1f1492eb7df4e8c079f3ff540d5ae', '产品研发部', 'ee918991cbdc92e895bcf35a8a6501f5', '0,09cea060267d57b8ef9894bb86680ae4,ee918991cbdc92e895bcf35a8a6501f5', 2, 0, '产品研发部', '1', '1', '2026-02-27 14:31:26', '2026-02-27 15:12:01');
INSERT INTO `sys_department` VALUES ('ee918991cbdc92e895bcf35a8a6501f5', '2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+北京分公司', '09cea060267d57b8ef9894bb86680ae4', '0,09cea060267d57b8ef9894bb86680ae4', 1, 0, '萌新AI+北京分公司', '1', '1', '2026-02-27 14:30:33', '2026-03-02 14:19:00');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表' ROW_FORMAT = Dynamic;

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
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `parent_id_index`(`parent_id` ASC) USING BTREE,
  INDEX `menu_title_index`(`menu_title` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('029b26bb4b309ef5f2e8a71cfd20b599', '用户删除(单个)', 'iconfont icon-CRMEB-zichanguanli-xianxing1', 3, 'user-del', '', 2, '3', 4, 0, '1', '1', '2026-03-07 16:18:12', '2026-03-07 16:19:14');
INSERT INTO `sys_menu` VALUES ('10fe0172d9625dc73820886e3012b23f', '租户更新', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'tenant-edit', '', 3, '60c1536310c6f201b683138fa38ab744', 5, 0, '1', '1', '2026-03-07 16:48:22', '2026-03-07 16:52:08');
INSERT INTO `sys_menu` VALUES ('15', '菜单管理', 'iconfont icon-CRMEB-lianjie', 2, '', 'views/menu-list.html', 1, '2', 4, 0, '1', '1', '2026-02-09 15:09:43', '2026-03-05 13:53:09');
INSERT INTO `sys_menu` VALUES ('1d66e8070f9764978728378201d0fe3c', '菜单编辑', 'iconfont icon-CRMEB-pingtaishouxufei-xianxing1', 3, 'menu-edit', '', 2, '15', 5, 0, '1', '1', '2026-03-07 16:56:55', '2026-03-07 16:56:55');
INSERT INTO `sys_menu` VALUES ('2', '系统配置', 'iconfont icon-xitongzujian', 1, '', '', 1, '0', 1, 0, '1', '1', '2026-02-09 15:07:15', '2026-03-07 17:09:48');
INSERT INTO `sys_menu` VALUES ('25d758bc0e1f29f43018c372dd0d34b7', '用户添加', 'iconfont icon-CRMEB-zichanguanli-xianxing', 3, 'user-add', '', 2, '3', 2, 0, '1', '1', '2026-03-07 16:15:55', '2026-03-07 16:28:39');
INSERT INTO `sys_menu` VALUES ('2ab14b4c128b31e5445687e8cc2a7e7a', '菜单搜索', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'menu-search', '', 2, '15', 1, 0, '1', '1', '2026-03-07 16:54:03', '2026-03-07 16:54:03');
INSERT INTO `sys_menu` VALUES ('2eec6b556c4848c04e1b6a61f2b980c3', '部门搜索', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'dept-search', '', 2, '4', 1, 0, '1', '1', '2026-03-07 16:29:12', '2026-03-07 16:29:12');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'iconfont icon-CRMEB-yuangong-xianxing', 2, '', 'views/user-list.html', 1, '2', 1, 0, '1', '1', '2026-02-09 15:07:15', '2026-03-05 13:53:55');
INSERT INTO `sys_menu` VALUES ('4', '部门管理', 'iconfont icon-zhinengmofangicon_bumenguanli', 2, '', 'views/dept-list.html', 1, '2', 2, 0, '1', '1', '2026-02-09 15:07:15', '2026-03-05 13:55:59');
INSERT INTO `sys_menu` VALUES ('47861be2b438c00626d05fff8c3926c8', '部门删除(批量)', 'iconfont icon-CRMEB-caiwujilu-xianxing', 3, 'dept-del-batch', '', 2, '4', 5, 0, '1', '1', '2026-03-07 16:31:19', '2026-03-07 16:33:16');
INSERT INTO `sys_menu` VALUES ('4ab6f8c9cc3c96ccfd150b0b19856d64', '用户搜索', 'iconfont icon-CRMEB-kefuguanli-xianxing', 3, 'user-search', '', 2, '3', 1, 0, '1', '1', '2026-03-07 16:16:37', '2026-03-07 16:28:33');
INSERT INTO `sys_menu` VALUES ('5', '日志监控', 'iconfont icon-yibiaopan1', 1, '', '', 1, '0', 2, 0, '1', '1', '2026-02-09 15:07:15', '2026-03-07 17:09:54');
INSERT INTO `sys_menu` VALUES ('534250b43a66dfcf9ed7080405c472af', '部门更新', 'iconfont icon-CRMEB-zichanguanli-xianxing', 3, 'dept-edit', '', 2, '4', 6, 0, '1', '1', '2026-03-07 16:34:50', '2026-03-07 16:34:50');
INSERT INTO `sys_menu` VALUES ('535d5101b7e8b7be3836e98638c640b9', '服务监控', 'iconfont icon-CRMEB-neirongzixun-mianxing', 2, '', 'views/server-monitor.html', 2, 'df7b6bfda0695b5735c5b56a9e5c2450', 2, 0, '1', '1', '2026-03-05 13:44:14', '2026-03-05 13:50:48');
INSERT INTO `sys_menu` VALUES ('56383ed807dba3a55c7804897b6b1233', '公告类型添加', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'notice-type-add', '', 3, 'e55f5abe33940b40f45fe3c85fbf75ce', 1, 0, '1', '1', '2026-03-07 16:59:46', '2026-03-07 17:01:28');
INSERT INTO `sys_menu` VALUES ('5dac0634410f343b6ebc93969b8d64b3', '公告类型删除', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'notice-type-del', '', 3, 'e55f5abe33940b40f45fe3c85fbf75ce', 2, 0, '1', '1', '2026-03-07 17:00:13', '2026-03-07 17:01:18');
INSERT INTO `sys_menu` VALUES ('6', '登陆日志', 'layui-icon layui-icon-form', 2, NULL, 'views/login-log.html', 1, '5', 1, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('60c1536310c6f201b683138fa38ab744', '租户管理', 'iconfont icon-CRMEB-kefuguanli-xianxing', 2, '', 'views/tenant-list.html', 2, '2', 5, 0, '1', '1', '2026-02-09 18:23:36', '2026-03-05 13:53:43');
INSERT INTO `sys_menu` VALUES ('6699320550d2ca73dbec04124540ca27', '用户解封', 'iconfont icon-CRMEB-biaoshi-xianxing', 3, 'user-unblock', '', 2, '3', 7, 0, '1', '1', '2026-03-07 16:21:09', '2026-03-07 16:21:09');
INSERT INTO `sys_menu` VALUES ('6c925dc983e7f5d1293c05625f7e0b15', '部门添加(子级)', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'dept-add-sub', '', 2, '4', 3, 0, '1', '1', '2026-03-07 16:33:58', '2026-03-07 16:34:09');
INSERT INTO `sys_menu` VALUES ('7', '系统日志', 'layui-icon layui-icon-table', 2, NULL, 'views/system-log.html', 1, '5', 2, 0, '1', '1', '2026-02-09 15:07:15', '2026-02-09 15:07:15');
INSERT INTO `sys_menu` VALUES ('701c10b0058f0c81f197a92ebb7556ae', '文件列表', 'iconfont icon-CRMEB-caiwujilu-mianxing', 2, '', 'views/file-list.html', 2, 'bc1cd48e9d67d31d8fd4e7d16918c541', 1, 0, '1', '1', '2026-03-05 13:45:54', '2026-03-05 13:45:54');
INSERT INTO `sys_menu` VALUES ('735512526810e222bb0c3a607a0c3a9a', '租户添加', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'tenant-add', '', 3, '60c1536310c6f201b683138fa38ab744', 2, 0, '1', '1', '2026-03-07 16:42:23', '2026-03-07 16:51:42');
INSERT INTO `sys_menu` VALUES ('782c6c367c2891f80c408eb7ef8163ba', '菜单删除', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'menu-del', '', 2, '15', 4, 0, '1', '1', '2026-03-07 16:56:14', '2026-03-07 16:56:14');
INSERT INTO `sys_menu` VALUES ('83287059791dcd139e32e91bf96265b8', '角色添加', 'iconfont icon-CRMEB-zichanguanli-xianxing', 3, 'role-add', '', 3, 'fe9ee4bd63de119d8cf5a8889bc1a0a4', 2, 0, '1', '1', '2026-03-07 16:36:57', '2026-03-07 16:36:57');
INSERT INTO `sys_menu` VALUES ('87641892cc74a965c0a098366437836b', '菜单添加(子级)', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'menu-add-sub', '', 2, '15', 3, 0, '1', '1', '2026-03-07 16:55:34', '2026-03-07 16:55:34');
INSERT INTO `sys_menu` VALUES ('893fd1f6244a0bc9c265121c37e6446d', '公告管理', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 1, '', '', 1, '0', 5, 0, '1', '1', '2026-03-06 08:20:54', '2026-03-07 17:10:10');
INSERT INTO `sys_menu` VALUES ('8a2ab16ff7a1132ac16b5a7f75f644af', '角色更新', 'iconfont icon-CRMEB-yinliang-mianxing', 3, 'role-edit', '', 3, 'fe9ee4bd63de119d8cf5a8889bc1a0a4', 5, 0, '1', '1', '2026-03-07 16:39:12', '2026-03-07 16:39:12');
INSERT INTO `sys_menu` VALUES ('9331da3ea50456cd1da50011119a124b', '菜单授权', 'iconfont icon-CRMEB-liebiao-mianxing', 3, 'role-auth', '', 3, 'fe9ee4bd63de119d8cf5a8889bc1a0a4', 6, 0, '1', '1', '2026-03-07 16:40:02', '2026-03-07 16:40:02');
INSERT INTO `sys_menu` VALUES ('967438ed43196b422a2fa015df23beee', '密码重置', 'iconfont icon-CRMEB-yongquanjine-xianxing', 3, 'user-reset', '', 2, '3', 6, 0, '1', '1', '2026-03-07 16:19:59', '2026-03-07 16:19:59');
INSERT INTO `sys_menu` VALUES ('98a6b199f4208de4cedbcc4a6cded12c', '用户删除(批量)', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'user-del-batch', '', 2, '3', 5, 0, '1', '1', '2026-03-07 16:19:05', '2026-03-07 16:19:05');
INSERT INTO `sys_menu` VALUES ('9a3e091fd8f12112ec95258d249d1f2a', '部门删除(单个)', 'iconfont icon-CRMEB-daituikuandingdan-xianxing', 3, 'dept-del', '', 2, '4', 4, 0, '1', '1', '2026-03-07 16:30:48', '2026-03-07 16:33:25');
INSERT INTO `sys_menu` VALUES ('a04f725017ccd0ae834e8f21046bf160', '部门添加(一级)', 'iconfont icon-CRMEB-yongquanjine-xianxing', 3, 'dept-add-one', '', 2, '4', 2, 0, '1', '1', '2026-03-07 16:28:07', '2026-03-07 16:32:37');
INSERT INTO `sys_menu` VALUES ('bc1cd48e9d67d31d8fd4e7d16918c541', '文件管理', 'iconfont icon-CRMEB-qitaruku-xianxing', 1, '', '', 1, '0', 4, 0, '1', '1', '2026-03-05 13:45:17', '2026-03-07 17:10:05');
INSERT INTO `sys_menu` VALUES ('bced832645ec8b901c8e1b57b1d1e8b9', '角色删除(批量)', 'iconfont icon-CRMEB-yongquanjine-xianxing', 3, 'role-del-batch', '', 3, 'fe9ee4bd63de119d8cf5a8889bc1a0a4', 4, 0, '1', '1', '2026-03-07 16:38:23', '2026-03-07 16:38:23');
INSERT INTO `sys_menu` VALUES ('c4658a333dd447a1782703ec6e61b95e', '缓存监控', 'iconfont icon-CRMEB-miaoshahuodong-xianxing', 2, '', 'views/cache-monitor.html', 2, 'df7b6bfda0695b5735c5b56a9e5c2450', 1, 0, '1', '1', '2026-03-05 13:43:29', '2026-03-05 13:43:29');
INSERT INTO `sys_menu` VALUES ('c915808369e64c140b16880d121f4489', '角色搜索', 'iconfont icon-CRMEB-zichanguanli-xianxing', 3, 'role-search', '', 3, 'fe9ee4bd63de119d8cf5a8889bc1a0a4', 1, 0, '1', '1', '2026-03-07 16:36:29', '2026-03-07 16:36:29');
INSERT INTO `sys_menu` VALUES ('d4649a9efbc7b895e21bf7c037c3dc42', '租户删除(批量)', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'tenant-del-batch', '', 3, '60c1536310c6f201b683138fa38ab744', 3, 0, '1', '1', '2026-03-07 16:43:40', '2026-03-07 16:43:40');
INSERT INTO `sys_menu` VALUES ('d7b013444842403d81a9bb0a52117569', '用户封禁', 'iconfont icon-CRMEB-qitaruku-xianxing', 3, 'user-block', '', 2, '3', 8, 0, '1', '1', '2026-03-07 16:23:49', '2026-03-07 16:23:49');
INSERT INTO `sys_menu` VALUES ('d7d62a0fd4b36151331e0e215230f49f', '公告类型更新', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'notice-type-edit', '', 3, 'e55f5abe33940b40f45fe3c85fbf75ce', 4, 0, '1', '1', '2026-03-07 17:02:09', '2026-03-07 17:02:09');
INSERT INTO `sys_menu` VALUES ('da71427c4eadd804107af1c6b3683c9e', '角色删除(单个)', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'role-del', '', 3, 'fe9ee4bd63de119d8cf5a8889bc1a0a4', 3, 0, '1', '1', '2026-03-07 16:37:53', '2026-03-07 16:37:53');
INSERT INTO `sys_menu` VALUES ('df7b6bfda0695b5735c5b56a9e5c2450', '系统监控', 'iconfont icon-CRMEB-daichuliyonghufankui-xianxing', 1, '', '', 1, '0', 3, 0, '1', '1', '2026-03-05 13:42:52', '2026-03-07 17:09:59');
INSERT INTO `sys_menu` VALUES ('e55f5abe33940b40f45fe3c85fbf75ce', '公告类型', 'iconfont icon-tubiao', 2, '', 'views/notice-type-list.html', 2, '893fd1f6244a0bc9c265121c37e6446d', 2, 0, '1', '1', '2026-03-06 08:25:06', '2026-03-06 08:56:56');
INSERT INTO `sys_menu` VALUES ('e7fcfdc61ba1bd740aa38112883b4453', '公告列表', 'iconfont icon-CRMEB-qiandao-xianxing', 2, '', 'views/notice-list.html', 2, '893fd1f6244a0bc9c265121c37e6446d', 1, 0, '1', '1', '2026-03-06 08:21:29', '2026-03-06 08:21:29');
INSERT INTO `sys_menu` VALUES ('ed2bf744bc72ddb8ffc0f1c2a3ce5d6a', '租户搜索', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'tenant-search', '', 3, '60c1536310c6f201b683138fa38ab744', 1, 0, '1', '1', '2026-03-07 16:51:23', '2026-03-07 16:51:23');
INSERT INTO `sys_menu` VALUES ('f18772df00b09f74945f2f0651e5c4f1', '租户删除(单个)', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'tenant-del', '', 3, '60c1536310c6f201b683138fa38ab744', 4, 0, '1', '1', '2026-03-07 16:42:57', '2026-03-07 16:51:58');
INSERT INTO `sys_menu` VALUES ('f2b6ebd196dcfc59d6cb8f5909daa599', '菜单添加(一级)', 'iconfont icon-CRMEB-zongdingdanjine-xianxing1', 3, 'menu-add-one', '', 2, '15', 2, 0, '1', '1', '2026-03-07 16:54:42', '2026-03-07 16:55:08');
INSERT INTO `sys_menu` VALUES ('f711c4ac8aeac630b1a805855ef615d8', '用户导入', 'iconfont icon-CRMEB-qiandao-xianxing', 3, 'user-import', '', 2, '3', 3, 0, '1', '1', '2026-03-07 16:17:34', '2026-03-07 16:17:34');
INSERT INTO `sys_menu` VALUES ('f723cff21d9e04239a0b7af05abde597', '公告类型更新', 'iconfont icon-CRMEB-mendiandingdan-xianxing', 3, 'notice-type-edit', '', 3, 'e55f5abe33940b40f45fe3c85fbf75ce', 3, 0, '1', '1', '2026-03-07 17:01:07', '2026-03-07 17:01:07');
INSERT INTO `sys_menu` VALUES ('f9199b23565614dd7f8c13e4c62678f3', '用户更新', 'iconfont icon-CRMEB-daishenhe-mianxing', 3, 'user-edit', '', 2, '3', 9, 0, '1', '1', '2026-03-07 16:26:34', '2026-03-07 16:26:34');
INSERT INTO `sys_menu` VALUES ('fe9ee4bd63de119d8cf5a8889bc1a0a4', '角色管理', 'iconfont icon-CRMEB-daishenhe-mianxing', 2, '', 'views/role-list.html', 2, '2', 3, 0, '1', '1', '2026-02-09 15:16:08', '2026-03-05 13:53:31');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告ID,主键',
  `notice_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `notice_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容,可以是文字，图片，视频，音频，表格等多媒体内容',
  `notice_level` tinyint(1) NOT NULL COMMENT '公告级别: 0-普通, 1-重要, 2-紧急',
  `notice_type_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告类型ID关联(sys_notice_type)表主键',
  `publish_user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布人ID',
  `is_top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶: 0-否, 1-是',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_dept`;
CREATE TABLE `sys_notice_dept`  (
  `notice_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告ID',
  `dept_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`notice_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_type`;
CREATE TABLE `sys_notice_type`  (
  `notice_type_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告类型ID,主键',
  `notice_type_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告类型名称',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`notice_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice_type
-- ----------------------------
INSERT INTO `sys_notice_type` VALUES ('001d77a67c2464bfc56ce9b961ce8da7', '通知', '1', '1', '1', '2026-03-06 10:34:47', '2026-03-06 10:34:47');
INSERT INTO `sys_notice_type` VALUES ('1aed7300d494b163d12acde9c1120016', '活动', '1', '1', '1', '2026-03-06 10:36:46', '2026-03-06 10:36:46');
INSERT INTO `sys_notice_type` VALUES ('6a5070d2078bd7395e43d5f675458b0c', '公示', '1', '1', '1', '2026-03-06 10:42:28', '2026-03-06 10:46:41');
INSERT INTO `sys_notice_type` VALUES ('776ec87967ba7295011c1a904f529f56', '新闻', '1', '1', '1', '2026-03-06 10:36:41', '2026-03-06 10:36:41');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日志记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `permission_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限ID,主键',
  `permission_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名',
  `permission_symbol` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限符号',
  `tenant_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

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
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `tenant_id_index`(`tenant_id` ASC) USING BTREE,
  INDEX `dept_name_index`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '平台管理员', 'PLATFORM_ADMIN', '1', 0, '管理整个平台', '1', '1', '2026-02-09 15:01:50', '2026-02-09 18:19:02');
INSERT INTO `sys_role` VALUES ('2', '运营管理员', 'OPERATION_ADMIN', '1', 0, '管理平台租户', '1', '1', '2026-02-09 15:01:50', '2026-02-09 16:52:36');
INSERT INTO `sys_role` VALUES ('9f803d2576b32d9d7f556a34cc65a386', '普通角色', 'COMMON', '2da1f1492eb7df4e8c079f3ff540d5ae', 0, '普通角色', '1', '1', '2026-03-05 11:07:17', '2026-03-05 11:07:17');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

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
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1:是 0:否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人ID,关联用户表(sys_user)主键',
  `update_by_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人ID,关联用户表(sys_user)主键',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '设置当前时间，并且自动更新时间',
  PRIMARY KEY (`tenant_id`) USING BTREE,
  INDEX `tenant_name_index`(`tenant_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '租户管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
INSERT INTO `sys_tenant` VALUES ('1', '知芽M+', 0, '平台管理租户', '1', '1', '2026-02-09 15:01:49', '2026-03-02 14:16:07');
INSERT INTO `sys_tenant` VALUES ('2da1f1492eb7df4e8c079f3ff540d5ae', '萌新AI+', 0, '萌新AI+', '1', '1', '2026-02-27 15:10:45', '2026-03-02 14:17:03');

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
  `is_enabled` tinyint(1) NULL DEFAULT 1 COMMENT '账号是否可用 1:可用 0:不可用',
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '5', '1', '1', '管理员', 'admin', 'admin123@163.com', '$2a$10$2Z2UEjaqgtPZru9M.26JDe.jPPtMQO/v0RNITEbZ3UEAzyacZZ22O', 1, 'file/avatar.png', 1, '备注', '1', '1', '2026-02-09 15:01:50', '2026-12-31 23:59:59', '2026-03-06 09:59:39');
INSERT INTO `sys_user` VALUES ('2', '6', '2', '1', '运营', 'operation', 'operation123@163.com', '$2a$10$iTBYWW89ZPbyQMowkfw9YubzrMgMgpEdCU3WgperYH1NCouKBdwBS', 1, 'file/avatar.gif', 1, '备注', '1', '1', '2026-02-09 15:01:50', '2026-12-31 23:59:59', '2026-03-01 14:50:02');

SET FOREIGN_KEY_CHECKS = 1;
