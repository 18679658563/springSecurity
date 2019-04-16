/*
 Navicat MySQL Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : security

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 16/04/2019 16:48:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '哪个用户',
  `description` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `request_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的ip地址',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消耗的时间',
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `css` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `href` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('0f42407cb0c2414aaba038b89a096b6f', '87c6a9b7495f4b7c8bff7ed975efe46c', '新增', NULL, NULL, 2, 'sys:quartz:add', 100, '2019-04-11 17:14:20', '2019-04-11 17:14:20');
INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', 'fa-users', '', 1, '', 1, '2019-04-01 15:38:32', '2019-04-09 15:56:04');
INSERT INTO `sys_permission` VALUES ('10', '8', '新增', '', '', 2, 'sys:menu:add', 100, '2019-04-02 15:38:36', NULL);
INSERT INTO `sys_permission` VALUES ('11', '8', '删除', '', '', 2, 'sys:menu:del', 100, '2019-04-02 15:38:39', NULL);
INSERT INTO `sys_permission` VALUES ('12', '7', '角色', 'fa-user-secret', 'pages/role/roleList.html', 1, '', 7, '2019-04-02 15:38:44', NULL);
INSERT INTO `sys_permission` VALUES ('13', '12', '查询', '', '', 2, 'sys:role:query', 100, '2019-04-02 15:38:47', NULL);
INSERT INTO `sys_permission` VALUES ('13d4f6b7e10040b885f192028c259b8e', '2d81eb475fb74537b74aff688ceb7d2f', '字典查询', 'fa-search', NULL, 2, 'sys:dict:query', 100, '2019-04-02 16:38:03', '2019-04-02 16:38:03');
INSERT INTO `sys_permission` VALUES ('14', '12', '新增', '', '', 2, 'sys:role:add', 100, '2019-04-02 15:38:50', NULL);
INSERT INTO `sys_permission` VALUES ('15', '12', '删除', '', '', 2, 'sys:role:del', 100, '2019-04-02 15:38:52', NULL);
INSERT INTO `sys_permission` VALUES ('19', '0', '数据源监控', 'fa-eye', 'druid/index.html', 1, '', 34, '2019-04-02 15:38:55', '2019-04-09 15:56:15');
INSERT INTO `sys_permission` VALUES ('1a8e227a2e9440cdae130442659ce378', '87c6a9b7495f4b7c8bff7ed975efe46c', '查询', NULL, NULL, 2, 'sys:quartz:query', 100, '2019-04-11 17:14:43', '2019-04-11 17:14:43');
INSERT INTO `sys_permission` VALUES ('1c6404e8ad72431d9b495296bc86fce7', '87c6a9b7495f4b7c8bff7ed975efe46c', '修改', NULL, NULL, 2, 'sys:quartz:update', 100, '2019-04-11 17:15:55', '2019-04-11 17:15:55');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户查询', 'fa-user', 'pages/user/userList.html', 1, '', 2, '2019-04-02 15:38:58', NULL);
INSERT INTO `sys_permission` VALUES ('20', '0', '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', 1, '', 34, '2019-04-02 15:39:01', '2019-04-09 15:56:27');
INSERT INTO `sys_permission` VALUES ('2d81eb475fb74537b74aff688ceb7d2f', '7', '字典管理', 'fa-graduation-cap', 'pages/dict/dictList.html', 1, NULL, 100, '2019-04-02 16:35:37', '2019-04-02 16:35:37');
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', '', '', 2, 'sys:user:query', 100, '2019-04-02 15:39:04', NULL);
INSERT INTO `sys_permission` VALUES ('39c2d7396fc74fc6ba7abe552f70cbfe', '2d81eb475fb74537b74aff688ceb7d2f', '字典删除', 'fa-times-circle', NULL, 2, 'sys:dict:del', 100, '2019-04-02 16:40:03', '2019-04-02 16:40:03');
INSERT INTO `sys_permission` VALUES ('4', '2', '新增', '', '', 2, 'sys:user:add', 100, '2019-04-02 15:39:07', NULL);
INSERT INTO `sys_permission` VALUES ('5e71ccf91f7d490cba2b20413daabbeb', '2d81eb475fb74537b74aff688ceb7d2f', '字典新增', 'fa-legal', NULL, 2, 'sys:dict:add', 100, '2019-04-02 16:39:26', '2019-04-02 16:39:26');
INSERT INTO `sys_permission` VALUES ('6', '0', '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword.html', 1, 'sys:user:password', 4, '2019-04-02 15:39:10', '2019-04-09 15:56:08');
INSERT INTO `sys_permission` VALUES ('7', '0', '系统设置', 'fa-gears', '', 1, '', 5, '2019-04-02 15:39:12', '2019-04-09 15:56:12');
INSERT INTO `sys_permission` VALUES ('8', '7', '菜单', 'fa-cog', 'pages/menu/menuList.html', 1, '', 6, '2019-04-02 15:39:15', NULL);
INSERT INTO `sys_permission` VALUES ('87c6a9b7495f4b7c8bff7ed975efe46c', '7', '定时任务', 'fa-bell-o', 'pages/quartz/quartzList.html', 1, NULL, 100, '2019-04-11 17:13:36', '2019-04-11 17:13:36');
INSERT INTO `sys_permission` VALUES ('9', '8', '查询', '', '', 2, 'sys:menu:query', 100, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('ac9e6e9316f84dedb3d2b30e7d36130a', '2', '用户删除', 'fa-times-circle', NULL, 2, 'sys:user:del', 100, '2019-04-02 15:58:52', '2019-04-02 15:59:23');
INSERT INTO `sys_permission` VALUES ('f36e3860d9b44272a9bd3344524b9063', '87c6a9b7495f4b7c8bff7ed975efe46c', '删除', NULL, NULL, 2, 'sys:quartz:del', 100, '2019-04-11 17:15:23', '2019-04-11 17:15:23');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2019-03-28 13:25:39', '2019-04-11 17:27:42');
INSERT INTO `sys_role` VALUES ('2', 'USER', '', '2019-04-01 21:47:31', '2019-04-11 16:08:03');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permissionId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '0f42407cb0c2414aaba038b89a096b6f');
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '13d4f6b7e10040b885f192028c259b8e');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '1a8e227a2e9440cdae130442659ce378');
INSERT INTO `sys_role_permission` VALUES ('1', '1c6404e8ad72431d9b495296bc86fce7');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '20');
INSERT INTO `sys_role_permission` VALUES ('1', '2d81eb475fb74537b74aff688ceb7d2f');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '39c2d7396fc74fc6ba7abe552f70cbfe');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5e71ccf91f7d490cba2b20413daabbeb');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '87c6a9b7495f4b7c8bff7ed975efe46c');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', 'ac9e6e9316f84dedb3d2b30e7d36130a');
INSERT INTO `sys_role_permission` VALUES ('1', 'f36e3860d9b44272a9bd3344524b9063');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '13d4f6b7e10040b885f192028c259b8e');
INSERT INTO `sys_role_permission` VALUES ('2', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '2d81eb475fb74537b74aff688ceb7d2f');
INSERT INTO `sys_role_permission` VALUES ('2', '3');
INSERT INTO `sys_role_permission` VALUES ('2', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '6');
INSERT INTO `sys_role_permission` VALUES ('2', '7');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`userId`, `roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `head_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telephone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$7kSIUtKaY5KB2QaEB4yaoO9QcPeSXp1LHUmoXv.8/5P2SS77aLpC2', '管理员', '66c937206369ce637db77118b4195d88.jpeg', '123', '345', '123', '1998-06-30', 0, 1, '2019-04-01 15:21:38', '2019-04-16 16:44:13');
INSERT INTO `sys_user` VALUES ('2', 'user', '$2a$10$ooGb4wjT7Hg3zgU2RhZp6eVu3jvG29i/U4L6VRwiZZ4.DZ0OOEAHu', 'silence2', NULL, '18678765632', '222354e', 'm765@163.com', '2019-03-28', 1, 1, '2019-03-20 21:47:18', '2019-04-09 15:55:38');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `k` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type`(`type`, `k`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('0884d9b01c1d4515a8879dd443a03f73', 'sex', '0', '女', '2017-11-17 09:58:24', '2019-04-09 15:56:42');
INSERT INTO `t_dict` VALUES ('1dabdcec4a694f36adbc7032c4bdf967', 'sex', '1', '男', '2017-11-17 10:03:46', '2017-11-17 10:03:46');
INSERT INTO `t_dict` VALUES ('23sfc355455b9f3e70c9ded01a73', 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('5ca44ba9d15b40cdb33876dac3f0e2fa', 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES ('9de5dea39355455b9f3e70c9ded01a73', 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09');

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文件大小',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件位置',
  `type` int(11) NULL DEFAULT 1 COMMENT '文件是否删除 ,1不删,0删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_file
-- ----------------------------
INSERT INTO `t_file` VALUES ('66c937206369ce637db77118b4195d88', 'image/jpeg', '287291', '/home/silence/workspace/spring-security/src/main/resources/static/img/66c937206369ce637db77118b4195d88.jpeg', '66c937206369ce637db77118b4195d88.jpeg', 1, '2019-04-16 16:44:13', '2019-04-16 16:44:13');

-- ----------------------------
-- Table structure for t_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `t_quartz_job`;
CREATE TABLE `t_quartz_job`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式，任务执行代码时间',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '是否暂停',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行方法',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_quartz_job
-- ----------------------------
INSERT INTO `t_quartz_job` VALUES ('248f3529a20d409484d030ab439c6314', 'quartzTask', '0 0 0 * * ? ', b'1', 'quartzlog清理', 'delQuartzLog', NULL, '每天凌晨清理三天前log信息', '2019-04-15 15:10:21', '2019-04-15 15:10:21');
INSERT INTO `t_quartz_job` VALUES ('7de8332dfca846cb92c0885a59c389e2', 'quartzTask', '0 0 0 * * ? ', b'1', 'log信息定时任务', 'delLog', NULL, '每日凌晨清除三天前的log信息', '2019-04-12 16:20:55', '2019-04-12 16:20:55');

-- ----------------------------
-- Table structure for t_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `t_quartz_log`;
CREATE TABLE `t_quartz_log`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式，任务执行代码时间',
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `is_success` bit(1) NULL DEFAULT NULL COMMENT '是否成功',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_token
-- ----------------------------
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `val` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'LoginUser的json串',
  `expireTime` datetime(0) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
