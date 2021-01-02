/*
 Navicat Premium Data Transfer

 Source Server         : ke
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : autoexam

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 02/01/2021 15:34:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enrollment_department
-- ----------------------------
DROP TABLE IF EXISTS `enrollment_department`;
CREATE TABLE `enrollment_department`  (
  `eduId` int(0) NOT NULL AUTO_INCREMENT COMMENT '教育局id',
  `eduName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教育局名称',
  `higherEduId` int(0) NULL DEFAULT NULL COMMENT '上级招生部门',
  PRIMARY KEY (`eduId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of enrollment_department
-- ----------------------------

-- ----------------------------
-- Table structure for enrollment_department_login
-- ----------------------------
DROP TABLE IF EXISTS `enrollment_department_login`;
CREATE TABLE `enrollment_department_login`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `eduId` int(0) NULL DEFAULT NULL COMMENT '教育局id',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of enrollment_department_login
-- ----------------------------
INSERT INTO `enrollment_department_login` VALUES ('hefei', '123', 12);

-- ----------------------------
-- Table structure for exam_room
-- ----------------------------
DROP TABLE IF EXISTS `exam_room`;
CREATE TABLE `exam_room`  (
  `exRoomId` int(0) NOT NULL AUTO_INCREMENT COMMENT '考场id',
  `schoolId` int(0) NOT NULL COMMENT '学校id',
  `floorId` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所在楼层id',
  `roomNum` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教室号',
  `isArrange` int(0) NULL DEFAULT NULL COMMENT '是否排考',
  PRIMARY KEY (`exRoomId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_room
-- ----------------------------

-- ----------------------------
-- Table structure for exam_staff
-- ----------------------------
DROP TABLE IF EXISTS `exam_staff`;
CREATE TABLE `exam_staff`  (
  `esId` int(0) NOT NULL AUTO_INCREMENT COMMENT '预备考务人员id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `schoolId` int(0) NULL DEFAULT NULL COMMENT '学校id',
  `telephoneNumber` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话号码',
  `grade` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '年级',
  `subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '科目',
  `qualified` int(0) NULL DEFAULT NULL COMMENT '是否具有主考资格',
  `schoolExamine` int(0) NULL DEFAULT NULL COMMENT '学校审核状态',
  `photograph` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '证件照',
  `eduExamine` int(0) NULL DEFAULT NULL COMMENT '招生部门审核状态',
  `finalRejection` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '驳回意见',
  PRIMARY KEY (`esId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_staff
-- ----------------------------
INSERT INTO `exam_staff` VALUES (1, NULL, NULL, NULL, 5, '13912345678', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `exam_staff` VALUES (2, NULL, NULL, NULL, 16, '19512345678', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for floor
-- ----------------------------
DROP TABLE IF EXISTS `floor`;
CREATE TABLE `floor`  (
  `floorId` int(0) NOT NULL AUTO_INCREMENT COMMENT '楼层id',
  `building` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所在楼',
  `schoolId` int(0) NULL DEFAULT NULL COMMENT '学校id',
  `floorStep` int(0) NULL DEFAULT NULL COMMENT '所在楼层',
  PRIMARY KEY (`floorId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of floor
-- ----------------------------

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `hsId` int(0) NOT NULL AUTO_INCREMENT COMMENT '历史记录id',
  `esId` int(0) NULL DEFAULT NULL COMMENT '考务人员id',
  `year` int(0) NULL DEFAULT NULL COMMENT '年份',
  `hsMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本年监考相关信息',
  PRIMARY KEY (`hsId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of history
-- ----------------------------

-- ----------------------------
-- Table structure for inspection_team
-- ----------------------------
DROP TABLE IF EXISTS `inspection_team`;
CREATE TABLE `inspection_team`  (
  `inspectionTeamId` int(0) NOT NULL AUTO_INCREMENT COMMENT '巡考组id',
  `firstInspectionPersonId` int(0) NULL DEFAULT NULL COMMENT '巡考人员一id',
  `secondInspectionPersonId` int(0) NULL DEFAULT NULL COMMENT '巡考人员二id',
  PRIMARY KEY (`inspectionTeamId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of inspection_team
-- ----------------------------

-- ----------------------------
-- Table structure for inspection_team_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `inspection_team_arrangement`;
CREATE TABLE `inspection_team_arrangement`  (
  `itArrangeId` int(0) NOT NULL AUTO_INCREMENT COMMENT '巡考组安排id',
  `inspectionTeamId` int(0) NULL DEFAULT NULL COMMENT '巡考组id',
  `schoolId` int(0) NULL DEFAULT NULL COMMENT '学校id',
  `floorId` int(0) NULL DEFAULT NULL COMMENT '楼层id',
  `sessions` int(0) NULL DEFAULT NULL COMMENT '场次（取值1,2,3,4）',
  PRIMARY KEY (`itArrangeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inspection_team_arrangement
-- ----------------------------

-- ----------------------------
-- Table structure for invigilator_group
-- ----------------------------
DROP TABLE IF EXISTS `invigilator_group`;
CREATE TABLE `invigilator_group`  (
  `invigilatorGroupId` int(0) NOT NULL AUTO_INCREMENT COMMENT '监考组id',
  `examinerId` int(0) NULL DEFAULT NULL COMMENT '主考人员id',
  `firstInvigilatorId` int(0) NULL DEFAULT NULL COMMENT '监考人员一id',
  `secondInvigilatorId` int(0) NULL DEFAULT NULL COMMENT '监考人员二id',
  PRIMARY KEY (`invigilatorGroupId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of invigilator_group
-- ----------------------------

-- ----------------------------
-- Table structure for invigilator_group_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `invigilator_group_arrangement`;
CREATE TABLE `invigilator_group_arrangement`  (
  `igArrangeId` int(0) NOT NULL AUTO_INCREMENT COMMENT '监考组安排id',
  `invigilatorGroupId` int(0) NULL DEFAULT NULL COMMENT '监考组id',
  `schoolId` int(0) NULL DEFAULT NULL COMMENT '学校id',
  `exRoomId` int(0) NULL DEFAULT NULL COMMENT '考场id',
  `sessions` int(0) NULL DEFAULT NULL COMMENT '场次（取值1,2,3,4）',
  PRIMARY KEY (`igArrangeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invigilator_group_arrangement
-- ----------------------------

-- ----------------------------
-- Table structure for number_of_candidates
-- ----------------------------
DROP TABLE IF EXISTS `number_of_candidates`;
CREATE TABLE `number_of_candidates`  (
  `numOfCanId` int(0) NOT NULL AUTO_INCREMENT COMMENT '考生数目Id',
  `eduId` int(0) NULL DEFAULT NULL COMMENT '教育局id',
  `year` year NULL DEFAULT NULL COMMENT '年份',
  `numOfScience` int(0) NULL DEFAULT NULL COMMENT '理科生人数',
  `numOfArt` int(0) NULL DEFAULT NULL COMMENT '文科生人数',
  `numOfExcellent` int(0) NULL DEFAULT NULL COMMENT '特长生人数',
  PRIMARY KEY (`numOfCanId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of number_of_candidates
-- ----------------------------

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `schoolId` int(0) NOT NULL AUTO_INCREMENT COMMENT '学校id',
  `schoolName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学校名称',
  `eduId` int(0) NULL DEFAULT NULL COMMENT '招生部门id',
  `exRoomExamine` int(0) NULL DEFAULT NULL COMMENT '考场审核状态',
  PRIMARY KEY (`schoolId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school
-- ----------------------------

-- ----------------------------
-- Table structure for school_admin_login
-- ----------------------------
DROP TABLE IF EXISTS `school_admin_login`;
CREATE TABLE `school_admin_login`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `schoolId` int(0) NULL DEFAULT NULL COMMENT '所属学校id',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school_admin_login
-- ----------------------------
INSERT INTO `school_admin_login` VALUES ('HFUT-1', '123456', 16);

-- ----------------------------
-- Table structure for status_notes
-- ----------------------------
DROP TABLE IF EXISTS `status_notes`;
CREATE TABLE `status_notes`  (
  `stateId` int(0) NOT NULL AUTO_INCREMENT COMMENT '状态备注id',
  `esId` int(0) NULL DEFAULT NULL COMMENT '考务人员id',
  `stateMseeage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注内容',
  `aduitState` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`stateId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of status_notes
-- ----------------------------

-- ----------------------------
-- Table structure for test_personnel_login
-- ----------------------------
DROP TABLE IF EXISTS `test_personnel_login`;
CREATE TABLE `test_personnel_login`  (
  `telephoneNumber` char(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '电话号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`telephoneNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test_personnel_login
-- ----------------------------
INSERT INTO `test_personnel_login` VALUES ('13912345678', '45678');
INSERT INTO `test_personnel_login` VALUES ('15260274720', '123456');
INSERT INTO `test_personnel_login` VALUES ('19512345678', '45678');

-- ----------------------------
-- Table structure for violation_record
-- ----------------------------
DROP TABLE IF EXISTS `violation_record`;
CREATE TABLE `violation_record`  (
  `vrId` int(0) NOT NULL AUTO_INCREMENT COMMENT '违规记录id',
  `esId` int(0) NULL DEFAULT NULL COMMENT '考务人员id',
  `year` int(0) NULL DEFAULT NULL COMMENT '年份',
  `vrMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '违规原因',
  `vrPunish` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '违规处罚',
  PRIMARY KEY (`vrId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of violation_record
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
