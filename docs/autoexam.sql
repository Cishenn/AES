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

 Date: 05/01/2021 15:45:09
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of enrollment_department
-- ----------------------------
INSERT INTO `enrollment_department` VALUES (1, '蜀山区教育局', 10);
INSERT INTO `enrollment_department` VALUES (2, '经开区教育局', 10);

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
INSERT INTO `enrollment_department_login` VALUES ('hefeiJinkai', '1234', 11);
INSERT INTO `enrollment_department_login` VALUES ('hefeiShushan', '123', 12);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_room
-- ----------------------------
INSERT INTO `exam_room` VALUES (1, 1, '1', '401', NULL);
INSERT INTO `exam_room` VALUES (2, 1, '1', '401', NULL);
INSERT INTO `exam_room` VALUES (3, 1, '3', '801', NULL);
INSERT INTO `exam_room` VALUES (4, 1, '2', '302', NULL);
INSERT INTO `exam_room` VALUES (5, 2, '5', '605', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_staff
-- ----------------------------
INSERT INTO `exam_staff` VALUES (1, '小明', NULL, NULL, 5, '13912345678', '高三', '数学', 0, 1, NULL, 0, NULL);
INSERT INTO `exam_staff` VALUES (2, '小蓝', NULL, NULL, 16, '19512345678', '初二', '生物', 1, 2, NULL, 2, NULL);
INSERT INTO `exam_staff` VALUES (3, '小红', NULL, NULL, 12, '13112345678', '初一', '语文', 0, 2, NULL, 1, NULL);
INSERT INTO `exam_staff` VALUES (4, '小黑', NULL, NULL, 1, '13245678910', '初一', '数学', 0, 2, NULL, 1, NULL);
INSERT INTO `exam_staff` VALUES (5, '小绿', NULL, NULL, 2, '15260274720', '初二', '化学', 1, 0, NULL, 0, NULL);
INSERT INTO `exam_staff` VALUES (6, '肖黑', NULL, NULL, 1, '19712345678', '初三', '化学', 0, 0, NULL, 0, '初三教师不得监考');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of floor
-- ----------------------------
INSERT INTO `floor` VALUES (1, '双子楼', 1, 3);
INSERT INTO `floor` VALUES (2, '双子楼', 1, 4);
INSERT INTO `floor` VALUES (3, '双子楼', 1, 5);
INSERT INTO `floor` VALUES (4, '综合楼', 1, 1);
INSERT INTO `floor` VALUES (5, '教学楼', 2, 2);

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
INSERT INTO `history` VALUES (1, 1, 2018, '参与2018中考监考');
INSERT INTO `history` VALUES (2, 1, 2019, '参与2019中考监考');
INSERT INTO `history` VALUES (3, 2, 2018, '参与2018中考监考');
INSERT INTO `history` VALUES (4, 3, 2019, '参与2019高考监考');
INSERT INTO `history` VALUES (5, 3, 2019, '参与2019中考监考');

-- ----------------------------
-- Table structure for inspection_team
-- ----------------------------
DROP TABLE IF EXISTS `inspection_team`;
CREATE TABLE `inspection_team`  (
  `inspectionTeamId` int(0) NOT NULL AUTO_INCREMENT COMMENT '巡考组id',
  `firstInspectionPersonId` int(0) NULL DEFAULT NULL COMMENT '巡考人员一id',
  `secondInspectionPersonId` int(0) NULL DEFAULT NULL COMMENT '巡考人员二id',
  `eduId` int(0) NULL DEFAULT NULL COMMENT '招办id',
  PRIMARY KEY (`inspectionTeamId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of inspection_team
-- ----------------------------
INSERT INTO `inspection_team` VALUES (1, 1, 2, 1);
INSERT INTO `inspection_team` VALUES (2, 3, 2, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inspection_team_arrangement
-- ----------------------------
INSERT INTO `inspection_team_arrangement` VALUES (1, 1, 1, 1, 1);
INSERT INTO `inspection_team_arrangement` VALUES (2, 1, 1, 2, 2);
INSERT INTO `inspection_team_arrangement` VALUES (3, 1, 1, 3, 3);
INSERT INTO `inspection_team_arrangement` VALUES (4, 1, 1, 4, 4);
INSERT INTO `inspection_team_arrangement` VALUES (5, 2, 2, 5, 1);

-- ----------------------------
-- Table structure for invigilator_group
-- ----------------------------
DROP TABLE IF EXISTS `invigilator_group`;
CREATE TABLE `invigilator_group`  (
  `invigilatorGroupId` int(0) NOT NULL AUTO_INCREMENT COMMENT '监考组id',
  `examinerId` int(0) NULL DEFAULT NULL COMMENT '主考人员id',
  `firstInvigilatorId` int(0) NULL DEFAULT NULL COMMENT '监考人员一id',
  `secondInvigilatorId` int(0) NULL DEFAULT NULL COMMENT '监考人员二id',
  `eduId` int(0) NULL DEFAULT NULL COMMENT '所属招办id',
  PRIMARY KEY (`invigilatorGroupId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of invigilator_group
-- ----------------------------
INSERT INTO `invigilator_group` VALUES (1, 1, 2, 3, 1);
INSERT INTO `invigilator_group` VALUES (2, 1, 4, 5, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invigilator_group_arrangement
-- ----------------------------
INSERT INTO `invigilator_group_arrangement` VALUES (1, 1, 1, 1, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (2, 1, 1, 3, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (3, 1, 1, 2, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (4, 1, 1, 4, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (5, 2, 2, 5, 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of number_of_candidates
-- ----------------------------
INSERT INTO `number_of_candidates` VALUES (1, 1, 2020, 2000, 1000, 50);
INSERT INTO `number_of_candidates` VALUES (2, 1, 2019, 1000, 1000, 50);
INSERT INTO `number_of_candidates` VALUES (3, 2, 2020, 2000, 3000, 200);
INSERT INTO `number_of_candidates` VALUES (4, 2, 2019, 3000, 2000, 200);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '合肥工业大学', 1, 0);
INSERT INTO `school` VALUES (2, '安徽大学', 1, 0);

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
INSERT INTO `school_admin_login` VALUES ('AHU-1654', '123900', 2);
INSERT INTO `school_admin_login` VALUES ('AHU-1695', '145682', 2);
INSERT INTO `school_admin_login` VALUES ('HFUT-1001', '123456', 1);
INSERT INTO `school_admin_login` VALUES ('HFUT-1012', '987654', 1);
INSERT INTO `school_admin_login` VALUES ('HFUT-1024', '152632', 1);

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
INSERT INTO `status_notes` VALUES (1, 1, '腿断了', '1');
INSERT INTO `status_notes` VALUES (2, 1, '手断了', '2');
INSERT INTO `status_notes` VALUES (3, 2, '被车撞了', '2');
INSERT INTO `status_notes` VALUES (4, 2, '被人打了', '0');

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
INSERT INTO `test_personnel_login` VALUES ('13112345678', '123');
INSERT INTO `test_personnel_login` VALUES ('13245678910', '123456');
INSERT INTO `test_personnel_login` VALUES ('13912345678', '45678');
INSERT INTO `test_personnel_login` VALUES ('15260274720', '123456');
INSERT INTO `test_personnel_login` VALUES ('19512345678', '45678');
INSERT INTO `test_personnel_login` VALUES ('19712345678', '123456');

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
INSERT INTO `violation_record` VALUES (1, 1, 2020, '辱骂考生', '5年');
INSERT INTO `violation_record` VALUES (2, 2, 2020, '帮助作弊', '永久');
INSERT INTO `violation_record` VALUES (3, 2, 2018, '殴打其他考务人员', '1年');

SET FOREIGN_KEY_CHECKS = 1;
