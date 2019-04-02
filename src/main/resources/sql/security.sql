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

 Date: 02/04/2019 17:06:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', 'fa-users', '', 1, '', 1, '2019-04-01 15:38:32', NULL);
INSERT INTO `sys_permission` VALUES ('10', '8', '新增', '', '', 2, 'sys:menu:add', 100, '2019-04-02 15:38:36', NULL);
INSERT INTO `sys_permission` VALUES ('11', '8', '删除', '', '', 2, 'sys:menu:del', 100, '2019-04-02 15:38:39', NULL);
INSERT INTO `sys_permission` VALUES ('12', '7', '角色', 'fa-user-secret', 'pages/role/roleList.html', 1, '', 7, '2019-04-02 15:38:44', NULL);
INSERT INTO `sys_permission` VALUES ('13', '12', '查询', '', '', 2, 'sys:role:query', 100, '2019-04-02 15:38:47', NULL);
INSERT INTO `sys_permission` VALUES ('13d4f6b7e10040b885f192028c259b8e', '2d81eb475fb74537b74aff688ceb7d2f', '字典查询', 'fa-search', NULL, 2, 'sys:dict:query', 100, '2019-04-02 16:38:03', '2019-04-02 16:38:03');
INSERT INTO `sys_permission` VALUES ('14', '12', '新增', '', '', 2, 'sys:role:add', 100, '2019-04-02 15:38:50', NULL);
INSERT INTO `sys_permission` VALUES ('15', '12', '删除', '', '', 2, 'sys:role:del', 100, '2019-04-02 15:38:52', NULL);
INSERT INTO `sys_permission` VALUES ('19', '0', '数据源监控', 'fa-eye', 'druid/index.html', 1, '', 34, '2019-04-02 15:38:55', NULL);
INSERT INTO `sys_permission` VALUES ('2', '1', '用户查询', 'fa-user', 'pages/user/userList.html', 1, '', 2, '2019-04-02 15:38:58', NULL);
INSERT INTO `sys_permission` VALUES ('20', '0', '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', 1, '', 34, '2019-04-02 15:39:01', NULL);
INSERT INTO `sys_permission` VALUES ('2d81eb475fb74537b74aff688ceb7d2f', '7', '字典管理', 'fa-graduation-cap', 'pages/dict/dictList.html', 1, NULL, 100, '2019-04-02 16:35:37', '2019-04-02 16:35:37');
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', '', '', 2, 'sys:user:query', 100, '2019-04-02 15:39:04', NULL);
INSERT INTO `sys_permission` VALUES ('39c2d7396fc74fc6ba7abe552f70cbfe', '2d81eb475fb74537b74aff688ceb7d2f', '字典删除', 'fa-times-circle', NULL, 2, 'sys:dict:del', 100, '2019-04-02 16:40:03', '2019-04-02 16:40:03');
INSERT INTO `sys_permission` VALUES ('4', '2', '新增', '', '', 2, 'sys:user:add', 100, '2019-04-02 15:39:07', NULL);
INSERT INTO `sys_permission` VALUES ('5e71ccf91f7d490cba2b20413daabbeb', '2d81eb475fb74537b74aff688ceb7d2f', '字典新增', 'fa-legal', NULL, 2, 'sys:dict:add', 100, '2019-04-02 16:39:26', '2019-04-02 16:39:26');
INSERT INTO `sys_permission` VALUES ('6', '0', '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword.html', 1, 'sys:user:password', 4, '2019-04-02 15:39:10', NULL);
INSERT INTO `sys_permission` VALUES ('7', '0', '系统设置', 'fa-gears', '', 1, '', 5, '2019-04-02 15:39:12', NULL);
INSERT INTO `sys_permission` VALUES ('8', '7', '菜单', 'fa-cog', 'pages/menu/menuList.html', 1, '', 6, '2019-04-02 15:39:15', NULL);
INSERT INTO `sys_permission` VALUES ('9', '8', '查询', '', '', 2, 'sys:menu:query', 100, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('ac9e6e9316f84dedb3d2b30e7d36130a', '2', '用户删除', 'fa-times-circle', NULL, 2, 'sys:user:del', 100, '2019-04-02 15:58:52', '2019-04-02 15:59:23');

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
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2019-03-28 13:25:39', '2019-04-02 16:40:15');
INSERT INTO `sys_role` VALUES ('2', 'USER', '', '2019-04-01 21:47:31', '2019-04-25 17:01:10');

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
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '13d4f6b7e10040b885f192028c259b8e');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
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
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', 'ac9e6e9316f84dedb3d2b30e7d36130a');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '3');
INSERT INTO `sys_role_permission` VALUES ('2', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '6');

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
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$7kSIUtKaY5KB2QaEB4yaoO9QcPeSXp1LHUmoXv.8/5P2SS77aLpC2', '管理员', NULL, '123', '345', '123', '1998-06-30', 0, 1, '2019-04-01 15:21:38', '2019-04-02 17:05:51');
INSERT INTO `sys_user` VALUES ('2', 'user', '$2a$10$ooGb4wjT7Hg3zgU2RhZp6eVu3jvG29i/U4L6VRwiZZ4.DZ0OOEAHu', 'silence2', NULL, '18678765632', '222354e', 'm765@163.com', '2019-03-28', 1, 1, '2019-03-20 21:47:18', '2019-03-28 14:56:13');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `k` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type`(`type`, `k`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES (1, 'sex', '0', '女', '2017-11-17 09:58:24', '2017-11-18 14:21:05');
INSERT INTO `t_dict` VALUES (2, 'sex', '1', '男', '2017-11-17 10:03:46', '2017-11-17 10:03:46');
INSERT INTO `t_dict` VALUES (3, 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (4, 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (5, 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09');
INSERT INTO `t_dict` VALUES (6, 'sex', '2', '未', '2019-04-02 16:11:43', '2019-04-02 16:11:46');

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
