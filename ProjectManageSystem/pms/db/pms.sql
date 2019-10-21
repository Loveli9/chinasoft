/*
 Navicat Premium Data Transfer

 Source Server         : Huppert
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : pms

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 30/08/2019 14:19:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cr_codechecklog
-- ----------------------------
DROP TABLE IF EXISTS `cr_codechecklog`;
CREATE TABLE `cr_codechecklog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `projectid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `taskid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编号',
  `bugid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缺陷编号',
  `bugtype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缺陷类型',
  `buglevel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缺陷等级',
  `sourcefile` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '源文件路径',
  `beginline` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始行',
  `endline` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束行',
  `version` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本信息',
  `checkuser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监视人',
  `checktime` datetime(0) NULL DEFAULT NULL COMMENT '检视时间',
  `edituser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `editsuggest` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '修改建议',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cr_codechecklog
-- ----------------------------
INSERT INTO `cr_codechecklog` VALUES (1, '101', '101001', '11', 'bug', '1', 'c:/hyp/test/', '11', '22', '5.20', 'hyp', '2019-05-02 00:00:00', 'cl', 'wancheng', 'OK');
INSERT INTO `cr_codechecklog` VALUES (2, '101', '101002', '12', 'bug', '2', 'c:/hyp/test/', '111', '222', '5.21', 'hyp', '2019-05-24 00:00:00', 'li', 'wancheng', 'OK');
INSERT INTO `cr_codechecklog` VALUES (3, '101', '101003', '13', 'bug', '3', 'c:/hyp/test/', '222', '333', '5.22', 'hyp', '2019-05-28 00:00:00', 'yp', 'qqq', 'OK');
INSERT INTO `cr_codechecklog` VALUES (4, '101', '101004', '14', 'bug', '3', 'c:/hyp/test/', '12', '55', '5.20', 'hyp', '2019-05-01 00:00:00', 'cl', 'love', 'OK');
INSERT INTO `cr_codechecklog` VALUES (5, '101', '101005', '13', 'bug', '2', 'c:/hyp/test/', '15', '66', '5.20', 'hyp', '2019-05-02 00:00:00', 'yy', 'get', 'OK');
INSERT INTO `cr_codechecklog` VALUES (6, '101', '101006', '14', 'bug', '3', 'c:/hyp/test/', '66', '88', '5.20', 'hyp', '2019-05-02 00:00:00', 'll', 'heloo', 'OK');

-- ----------------------------
-- Table structure for tms_area_code
-- ----------------------------
DROP TABLE IF EXISTS `tms_area_code`;
CREATE TABLE `tms_area_code`  (
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代码',
  `city` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_area_code
-- ----------------------------
INSERT INTO `tms_area_code` VALUES ('sc-A', '成都');
INSERT INTO `tms_area_code` VALUES ('sc-B', '绵阳');
INSERT INTO `tms_area_code` VALUES ('sc-C', '德阳');
INSERT INTO `tms_area_code` VALUES ('sc-D', '宜宾');
INSERT INTO `tms_area_code` VALUES ('sc-E', '南充');

-- ----------------------------
-- Table structure for tms_business_type
-- ----------------------------
DROP TABLE IF EXISTS `tms_business_type`;
CREATE TABLE `tms_business_type`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `business_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商务类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = sjis COLLATE = sjis_japanese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_business_type
-- ----------------------------
INSERT INTO `tms_business_type` VALUES (1, 'TM');
INSERT INTO `tms_business_type` VALUES (2, 'FP');
INSERT INTO `tms_business_type` VALUES (3, '其他');

-- ----------------------------
-- Table structure for tms_iterative_state
-- ----------------------------
DROP TABLE IF EXISTS `tms_iterative_state`;
CREATE TABLE `tms_iterative_state`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `iterative_state` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '迭代状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_iterative_state
-- ----------------------------
INSERT INTO `tms_iterative_state` VALUES (1, '已计划');
INSERT INTO `tms_iterative_state` VALUES (2, '进行中');
INSERT INTO `tms_iterative_state` VALUES (3, '已关闭');
INSERT INTO `tms_iterative_state` VALUES (4, '延期');

-- ----------------------------
-- Table structure for tms_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tms_operation_log`;
CREATE TABLE `tms_operation_log`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `table_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '表名',
  `field_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '字段名',
  `operation` enum('') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '操作',
  `original_value` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '原始值',
  `modified_value` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '修改值',
  `operator` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '操作人',
  `operating_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `is_succeed` bit(1) NULL DEFAULT NULL COMMENT '是否成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_project_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_project_basic_info`;
CREATE TABLE `tms_project_basic_info`  (
  `project_code` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '项目编码',
  `project_name` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '项目名称',
  `project_description` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '项目简介',
  `project_type` int(11) NULL DEFAULT NULL COMMENT '项目类型',
  `business_type` int(11) NULL DEFAULT NULL COMMENT '商务类型',
  `business_group` enum('') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '业务群',
  `business_line` enum('') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '业务线',
  `business_department` enum('') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '事业部',
  `delivery_department` enum('') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '交付部',
  `attribution_area` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '归属地域',
  `project_creation_time` time(0) NULL DEFAULT NULL COMMENT '项目创建时间',
  `founder` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人',
  `project_start_time` date NULL DEFAULT NULL COMMENT '项目计划启动时间',
  `project_plan_end_time` date NULL DEFAULT NULL COMMENT '项目计划结束时间',
  `actual_project_start_time` date NULL DEFAULT NULL COMMENT '项目实际启动时间',
  `actual_project_end_time` date NULL DEFAULT NULL COMMENT '项目实际结束时间',
  `project_status` int(11) NULL DEFAULT NULL COMMENT '项目状态',
  `delivery_manager` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '交付经理',
  `spm` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'SPM',
  `pm` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'PM',
  `qa` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'QA',
  `customer_interface_person` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '客户接口人',
  PRIMARY KEY (`project_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_project_iteration_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_project_iteration_info`;
CREATE TABLE `tms_project_iteration_info`  (
  `iterative_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '迭代编码',
  `project_ownership` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '所属项目',
  `iterative_name` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '迭代名称',
  `iteration_goal` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '迭代目标',
  `iterative_state` int(11) NOT NULL COMMENT '迭代状态',
  `iterative_description` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '迭代描述',
  `iteration_plan_start_date` date NULL DEFAULT NULL COMMENT '迭代计划开始日期',
  `iteration_plan_end_date` date NULL DEFAULT NULL COMMENT '迭代计划结束日期',
  `iterate_actual_start_date` date NULL DEFAULT NULL COMMENT '迭代实际开始日期',
  `iterative_actual_end_date` date NULL DEFAULT NULL COMMENT '迭代实际结束日期',
  `progress_deviation_value` int(11) NULL DEFAULT NULL COMMENT '进度偏差值',
  `schedule_deviation` float NULL DEFAULT NULL COMMENT '进度偏差',
  `founder` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人',
  `creation_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `recently_updated_staff` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '最近更新人员',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`iterative_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_project_member_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_project_member_info`;
CREATE TABLE `tms_project_member_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '项目编码',
  `iterative_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '迭代编码',
  `domain_account` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '域账号',
  `input_ratio` int(11) NULL DEFAULT NULL COMMENT '投入比',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `enter_project_time` datetime(0) NULL DEFAULT NULL COMMENT '进入项目时间',
  `exit_project_time` datetime(0) NULL DEFAULT NULL COMMENT '退出项目时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_project_role
-- ----------------------------
DROP TABLE IF EXISTS `tms_project_role`;
CREATE TABLE `tms_project_role`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `role_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_project_role
-- ----------------------------
INSERT INTO `tms_project_role` VALUES (1, 'PM');
INSERT INTO `tms_project_role` VALUES (2, 'TC');
INSERT INTO `tms_project_role` VALUES (3, 'SE');
INSERT INTO `tms_project_role` VALUES (4, 'TSE');
INSERT INTO `tms_project_role` VALUES (5, 'MDE');

-- ----------------------------
-- Table structure for tms_project_role_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_project_role_info`;
CREATE TABLE `tms_project_role_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '工号',
  `project` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '项目',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `start_time_for_role` date NULL DEFAULT NULL COMMENT '担任角色起时间',
  `end_time_for_role` date NULL DEFAULT NULL COMMENT '担任角色止时间',
  `evaluation` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '评价',
  `evaluation_time` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  `evaluator` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '评价人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_project_status
-- ----------------------------
DROP TABLE IF EXISTS `tms_project_status`;
CREATE TABLE `tms_project_status`  (
  `status_id` int(11) NOT NULL COMMENT '状态id',
  `project_status` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目状态',
  PRIMARY KEY (`status_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_project_status
-- ----------------------------
INSERT INTO `tms_project_status` VALUES (1, '已立项');
INSERT INTO `tms_project_status` VALUES (2, '已启动');
INSERT INTO `tms_project_status` VALUES (3, '中断');
INSERT INTO `tms_project_status` VALUES (4, '挂起');
INSERT INTO `tms_project_status` VALUES (5, '结项');

-- ----------------------------
-- Table structure for tms_project_type
-- ----------------------------
DROP TABLE IF EXISTS `tms_project_type`;
CREATE TABLE `tms_project_type`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `project_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_project_type
-- ----------------------------
INSERT INTO `tms_project_type` VALUES (1, '开发');
INSERT INTO `tms_project_type` VALUES (2, '测试');
INSERT INTO `tms_project_type` VALUES (3, '其他');

-- ----------------------------
-- Table structure for tms_responsible_field_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_responsible_field_info`;
CREATE TABLE `tms_responsible_field_info`  (
  `responsible_field_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '责任田编码',
  `project_ownership` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '所属项目',
  `iterative_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '迭代编码',
  `responsible_field_name` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '责任田名称',
  `remarks` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '备注信息',
  `is_enabled` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`responsible_field_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_responsible_field_member_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_responsible_field_member_info`;
CREATE TABLE `tms_responsible_field_member_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `responsible_field_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '责任田编码',
  `employee_number` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '成员工号',
  `member_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成员姓名',
  `is_responsible` bit(1) NULL DEFAULT NULL COMMENT '是否责任人',
  `is_reserve` bit(1) NULL DEFAULT NULL COMMENT '是否后备',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_responsible_field_member_info
-- ----------------------------
INSERT INTO `tms_responsible_field_member_info` VALUES (1, 'A', '171511', '王二', b'1', b'1');
INSERT INTO `tms_responsible_field_member_info` VALUES (2, 'B', '171512', '张三', b'1', b'0');
INSERT INTO `tms_responsible_field_member_info` VALUES (3, 'C', '171513', '李四', b'0', b'0');

-- ----------------------------
-- Table structure for tms_system_role
-- ----------------------------
DROP TABLE IF EXISTS `tms_system_role`;
CREATE TABLE `tms_system_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '角色名称',
  `resource_authority` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '资源权限',
  `id_enable` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_system_role_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_system_role_info`;
CREATE TABLE `tms_system_role_info`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `role_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_permissions` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '角色权限',
  `is_enable` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_system_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_system_user_info`;
CREATE TABLE `tms_system_user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `number` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中软工号',
  `domain_account` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '域账号',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `mailbox` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `business_group` enum('华为业务群','中软业务群') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务群',
  `business_line` enum('IT业务线','无线网络业务线') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务线',
  `business_department` enum('无线事业部','平台事业部') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事业部',
  `delivery_department` enum('PLG交付部','OSS交付部') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交付部',
  `account_expiration_time` date NULL DEFAULT NULL COMMENT '账号失效时间',
  `password_expiration_time` date NULL DEFAULT NULL COMMENT '密码失效时间',
  `role` enum('管理员','PO','项目经理','交付经理','领导','客户') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `is_locked` bit(1) NULL DEFAULT NULL COMMENT '锁定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tms_system_user_info
-- ----------------------------
INSERT INTO `tms_system_user_info` VALUES (1, '171511', 'zhangsan', '19931229', '张三', 'zhangsan@chinasoft.com', '华为业务群', 'IT业务线', '无线事业部', 'OSS交付部', '2020-03-01', '2020-03-01', '管理员', b'1');
INSERT INTO `tms_system_user_info` VALUES (2, '171512', 'lisi', '19931229', '李四', 'lisi@chinasoft.com', '中软业务群', 'IT业务线', '无线事业部', 'OSS交付部', '2021-03-01', '2021-03-01', '管理员', b'0');

-- ----------------------------
-- Table structure for tms_task_daily
-- ----------------------------
DROP TABLE IF EXISTS `tms_task_daily`;
CREATE TABLE `tms_task_daily`  (
  `id` int(11) NOT NULL,
  `task_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '任务编码',
  `update_date` date NULL DEFAULT NULL COMMENT '更新日期',
  `update_time` time(0) NULL DEFAULT NULL COMMENT '更新时间',
  `used_time` float(20, 0) NULL DEFAULT NULL COMMENT '已用工时',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `schedule` float(20, 0) NULL DEFAULT NULL COMMENT '进度',
  `task_daily` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '任务日报',
  `daily_newspaper` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '日报填写人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_task_info
-- ----------------------------
DROP TABLE IF EXISTS `tms_task_info`;
CREATE TABLE `tms_task_info`  (
  `task_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '任务编码',
  `project_belongs` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '项目所属',
  `iterative_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '迭代编码',
  `parent_task_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '父任务编码',
  `responsible_field_code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '责任田编码',
  `task_level` int(11) NOT NULL DEFAULT 0 COMMENT '任务级别',
  `task_type` int(11) NULL DEFAULT NULL COMMENT '任务类型',
  `task_priority` enum('') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '任务优先级',
  `creation_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `task_start_time` date NULL DEFAULT NULL COMMENT '任务起时间',
  `task_end_time` date NULL DEFAULT NULL COMMENT '任务止时间',
  `planned_time` float(20, 0) NULL DEFAULT NULL COMMENT '计划用时',
  `founder` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人',
  `responsible` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '责任人',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `schedule` float(20, 0) NULL DEFAULT NULL COMMENT '进度',
  `remarks` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`task_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_task_priority
-- ----------------------------
DROP TABLE IF EXISTS `tms_task_priority`;
CREATE TABLE `tms_task_priority`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `task_priority` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '任务优先级',
  `task_weight` float(20, 0) NULL DEFAULT NULL COMMENT '任务权重',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_task_status
-- ----------------------------
DROP TABLE IF EXISTS `tms_task_status`;
CREATE TABLE `tms_task_status`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `task_status` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '任务状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tms_task_type
-- ----------------------------
DROP TABLE IF EXISTS `tms_task_type`;
CREATE TABLE `tms_task_type`  (
  `task_type_encode` int(11) NOT NULL COMMENT '任务类型编码',
  `task_type_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`task_type_encode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
