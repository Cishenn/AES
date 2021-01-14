/*
 Navicat MySQL Data Transfer

 Source Server         : Tencent Cloud DB
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : tc-mysql-0.services.infra.tree-diagram.site:10034
 Source Schema         : autoexam

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 14/01/2021 20:12:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enrollment_department
-- ----------------------------
DROP TABLE IF EXISTS `enrollment_department`;
CREATE TABLE `enrollment_department`  (
  `eduId` int(11) NOT NULL AUTO_INCREMENT COMMENT '教育局id',
  `eduName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教育局名称',
  `higherEduId` int(11) NULL DEFAULT NULL COMMENT '上级招生部门',
  `eduLevel` int(11) NULL DEFAULT NULL COMMENT '招生部门等级',
  `arrangeLevel` int(11) NULL DEFAULT NULL COMMENT '排考层次',
  PRIMARY KEY (`eduId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of enrollment_department
-- ----------------------------
INSERT INTO `enrollment_department` VALUES (1, '蜀山区教育局', 3, 1, 2);
INSERT INTO `enrollment_department` VALUES (2, '经开区教育局', 3, 1, 2);
INSERT INTO `enrollment_department` VALUES (3, '合肥市教育局', 5, 2, 2);
INSERT INTO `enrollment_department` VALUES (5, '安徽省教育厅', 12, 3, 1);

-- ----------------------------
-- Table structure for enrollment_department_login
-- ----------------------------
DROP TABLE IF EXISTS `enrollment_department_login`;
CREATE TABLE `enrollment_department_login`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `eduId` int(11) NULL DEFAULT NULL COMMENT '教育局id',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of enrollment_department_login
-- ----------------------------
INSERT INTO `enrollment_department_login` VALUES ('hefei', '1234', 3);
INSERT INTO `enrollment_department_login` VALUES ('hefeiJinkai', '1234', 2);
INSERT INTO `enrollment_department_login` VALUES ('hefeiShushan', '1234', 1);

-- ----------------------------
-- Table structure for exam_room
-- ----------------------------
DROP TABLE IF EXISTS `exam_room`;
CREATE TABLE `exam_room`  (
  `exRoomId` int(11) NOT NULL AUTO_INCREMENT COMMENT '考场id',
  `schoolId` int(11) NOT NULL COMMENT '学校id',
  `floorId` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所在楼层id',
  `roomNum` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教室号',
  `isArrange` int(11) NULL DEFAULT NULL COMMENT '是否排考',
  PRIMARY KEY (`exRoomId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_room
-- ----------------------------
INSERT INTO `exam_room` VALUES (2, 1, '1', '302', 1);
INSERT INTO `exam_room` VALUES (3, 1, '2', '403', 1);
INSERT INTO `exam_room` VALUES (4, 1, '3', '504', 1);
INSERT INTO `exam_room` VALUES (5, 1, '6', '905', 1);
INSERT INTO `exam_room` VALUES (6, 1, '1', '306', 0);
INSERT INTO `exam_room` VALUES (7, 1, '2', '407', 0);
INSERT INTO `exam_room` VALUES (8, 2, '5', '201', 1);
INSERT INTO `exam_room` VALUES (9, 2, '7', '102', 1);
INSERT INTO `exam_room` VALUES (10, 2, '5', '103', 1);
INSERT INTO `exam_room` VALUES (11, 2, '7', '204', 0);
INSERT INTO `exam_room` VALUES (12, 2, '5', '105', 0);
INSERT INTO `exam_room` VALUES (13, 3, '8', '201', 1);
INSERT INTO `exam_room` VALUES (14, 3, '4', '102', 1);
INSERT INTO `exam_room` VALUES (15, 3, '8', '203', 0);
INSERT INTO `exam_room` VALUES (16, 3, '4', '104', 0);
INSERT INTO `exam_room` VALUES (17, 3, '8', '205', 0);
INSERT INTO `exam_room` VALUES (31, 3, '4', '105', 0);
INSERT INTO `exam_room` VALUES (32, 3, '8', '208', 0);

-- ----------------------------
-- Table structure for exam_staff
-- ----------------------------
DROP TABLE IF EXISTS `exam_staff`;
CREATE TABLE `exam_staff`  (
  `esId` int(11) NOT NULL AUTO_INCREMENT COMMENT '预备考务人员id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `schoolId` int(11) NULL DEFAULT NULL COMMENT '学校id',
  `telephoneNumber` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话号码',
  `grade` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '年级',
  `subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '科目',
  `qualified` int(11) NULL DEFAULT NULL COMMENT '是否具有主考资格',
  `schoolExamine` int(11) NULL DEFAULT NULL COMMENT '学校审核状态',
  `photograph` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '证件照',
  `eduExamine` int(11) NULL DEFAULT NULL COMMENT '招生部门审核状态',
  `finalRejection` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '驳回意见',
  `isArrange` int(11) NULL DEFAULT NULL COMMENT '是否被排考',
  PRIMARY KEY (`esId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_staff
-- ----------------------------
INSERT INTO `exam_staff` VALUES (1, '小闯', 20, '男', 1, '13912345678', '高三', '英语', 1, 2, NULL, 2, '', 1);
INSERT INTO `exam_staff` VALUES (2, '小蓝', 0, '男', 2, '19512345678', '初二', '生物', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (3, '张振兴', 0, '男', 1, '15715690563', '高一', '数学', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (4, '李闯王', 0, '男', 1, '13855146632', '高三', '科学', 1, 3, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (5, '小绿', NULL, '男', 2, '15260274720', '初二', '化学', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (6, '肖黑', NULL, '男', 1, '19712345678', '初三', '化学', 1, 2, NULL, 2, '', 1);
INSERT INTO `exam_staff` VALUES (7, '小类', NULL, '男', 3, '13513245678', '初二', '物理', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (8, '小校', NULL, '女', 2, '13612345678', '高一', '英语', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (9, '小孔', NULL, '女', 3, '18912345678', '高一', '数学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (10, '晓晓', NULL, '女', 2, '13656468872', '初一', '英语', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (11, '小白', NULL, '男', 1, '13254687862', '初二', '物理', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (12, '小乘', NULL, '男', 3, '19616546878', '高二', '物理', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (13, '大成', NULL, '男', 2, '15261846878', '高一', '化学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (14, '大帝', NULL, '女', 1, '19813465482', '初一', '化学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (15, '小刘', NULL, '男', 2, '13946787982', '初二', '语文', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (16, '小赵', NULL, '女', 3, '15668786913', '高一', '数学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (17, '小钱', NULL, '男', 1, '13948625861', '初二', '数学', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (18, '小孙', NULL, '女', 2, '13465486748', '初一', '英语', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (19, '小李', NULL, '男', 3, '13646872023', '初二', '英语', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (20, '小舟', NULL, '女', 1, '13765465123', '高一', '物理', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (21, '小周', NULL, '男', 2, '13846876821', '高二', '物理', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (22, '小五', NULL, '女', 3, '13845413457', '初一', '化学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (23, '小吴', NULL, '男', 1, '13054648782', '初二', '化学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (24, '小政', NULL, '女', 2, '13656486421', '初一', '生物', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (25, '小郑', NULL, '男', 3, '18935454872', '高二', '生物', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (26, '小汪', NULL, '女', 1, '13646873213', '高一', '数学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (27, '小旺', NULL, '男', 2, '13853453176', '高二', '数学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (28, '肖宵', NULL, '女', 3, '18865316540', '高一', '数学', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (29, '小札', NULL, '男', 1, '15216487893', '高二', '物理', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (30, '刘瑾乘', NULL, '女', 2, '18765465431', '初一', '化学', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (31, 'zzx', 18, '男', 1, '15715699999', '高一', '物理', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (38, '小张', 14, '男', 2, '13566668888', '高一', '生物', 0, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (39, '小留', 23, '女', 3, '13566667777', '高二', '语文', 1, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (41, '西嗷', NULL, '男', 1, '13678523157', '高一', '数学', 1, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (42, '法海', NULL, '女', 2, '19856453535', '初二', '英语', 0, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (43, '白蛇', NULL, '男', 3, '18956522258', '初一', '历史', 0, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (44, '青蛇', NULL, '女', 1, '13946210252', '高一', '政治', 0, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (45, '小青', NULL, '男', 2, '13895110475', '高二', '物理', 0, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (46, '零充', NULL, '女', 3, '13568713254', '高一', '化学', 0, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (47, '小单', NULL, '男', 1, '19822255332', '高二', '物理', 0, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (48, '包子头', NULL, '女', 2, '13984654564', '高一', '生物', 0, 2, NULL, 2, NULL, 0);
INSERT INTO `exam_staff` VALUES (49, '五香', NULL, '女', 3, '13984654562', '高一', '生物', 0, 2, NULL, 2, NULL, 1);
INSERT INTO `exam_staff` VALUES (50, '猴子', NULL, '男', 1, '13687543217', '初一', '化学', 0, 2, NULL, 2, NULL, 0);

-- ----------------------------
-- Table structure for floor
-- ----------------------------
DROP TABLE IF EXISTS `floor`;
CREATE TABLE `floor`  (
  `floorId` int(11) NOT NULL AUTO_INCREMENT COMMENT '楼层id',
  `building` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所在楼',
  `schoolId` int(11) NULL DEFAULT NULL COMMENT '学校id',
  `floorStep` int(11) NULL DEFAULT NULL COMMENT '所在楼层',
  PRIMARY KEY (`floorId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of floor
-- ----------------------------
INSERT INTO `floor` VALUES (1, '双子楼', 1, 3);
INSERT INTO `floor` VALUES (2, '双子楼', 1, 4);
INSERT INTO `floor` VALUES (3, '双子楼', 1, 5);
INSERT INTO `floor` VALUES (4, '明理楼', 3, 1);
INSERT INTO `floor` VALUES (5, '教学楼', 2, 1);
INSERT INTO `floor` VALUES (6, '综合楼', 1, 9);
INSERT INTO `floor` VALUES (7, '博学北楼', 2, 2);
INSERT INTO `floor` VALUES (8, '明理楼', 3, 2);

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `hsId` int(11) NOT NULL AUTO_INCREMENT COMMENT '历史记录id',
  `esId` int(11) NULL DEFAULT NULL COMMENT '考务人员id',
  `year` int(11) NULL DEFAULT NULL COMMENT '年份',
  `hsMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本年监考相关信息',
  PRIMARY KEY (`hsId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 195 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (70, 4, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (71, 18, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (72, 22, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (73, 6, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (74, 21, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (75, 9, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (76, 1, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (77, 8, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (78, 7, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (79, 3, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (80, 19, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (81, 5, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (82, 27, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (83, 12, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (84, 20, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (85, 10, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (86, 16, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (87, 17, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (88, 25, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (89, 15, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (90, 26, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (91, 28, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (92, 14, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (93, 13, 2010, '参与2010高考监考');
INSERT INTO `history` VALUES (94, 2, 2010, '参与2010高考巡考');
INSERT INTO `history` VALUES (95, 11, 2010, '参与2010高考巡考');
INSERT INTO `history` VALUES (96, 24, 2010, '参与2010高考巡考');
INSERT INTO `history` VALUES (97, 23, 2010, '参与2010高考巡考');
INSERT INTO `history` VALUES (98, 31, 2010, '参与2010高考巡考');
INSERT INTO `history` VALUES (99, 29, 2010, '参与2010高考巡考');
INSERT INTO `history` VALUES (160, 4, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (161, 18, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (162, 22, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (163, 6, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (164, 21, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (165, 9, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (166, 1, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (167, 8, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (168, 7, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (169, 3, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (170, 19, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (171, 5, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (172, 27, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (173, 12, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (174, 20, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (175, 10, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (176, 16, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (177, 17, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (178, 25, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (179, 15, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (180, 26, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (181, 28, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (182, 14, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (183, 13, 2011, '参与2011高考监考');
INSERT INTO `history` VALUES (184, 2, 2011, '参与2011高考巡考');
INSERT INTO `history` VALUES (185, 11, 2011, '参与2011高考巡考');
INSERT INTO `history` VALUES (186, 24, 2011, '参与2011高考巡考');
INSERT INTO `history` VALUES (187, 23, 2011, '参与2011高考巡考');
INSERT INTO `history` VALUES (188, 31, 2011, '参与2011高考巡考');
INSERT INTO `history` VALUES (189, 29, 2011, '参与2011高考巡考');
INSERT INTO `history` VALUES (190, 1, 2012, '参与2012高考监考');
INSERT INTO `history` VALUES (191, 1, 2013, '参与2013高考监考');
INSERT INTO `history` VALUES (192, 1, 2014, '参与2014高考监考');
INSERT INTO `history` VALUES (193, 1, 2015, '参与2015高考监考');
INSERT INTO `history` VALUES (194, 1, 2016, '参与2016高考监考');

-- ----------------------------
-- Table structure for inspection_team
-- ----------------------------
DROP TABLE IF EXISTS `inspection_team`;
CREATE TABLE `inspection_team`  (
  `inspectionTeamId` int(11) NOT NULL AUTO_INCREMENT COMMENT '巡考组id',
  `firstInspectionPersonId` int(11) NULL DEFAULT NULL COMMENT '巡考人员一id',
  `secondInspectionPersonId` int(11) NULL DEFAULT NULL COMMENT '巡考人员二id',
  `eduId` int(11) NULL DEFAULT NULL COMMENT '招办id',
  PRIMARY KEY (`inspectionTeamId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 364 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of inspection_team
-- ----------------------------
INSERT INTO `inspection_team` VALUES (356, 18, 17, 1);
INSERT INTO `inspection_team` VALUES (357, 10, 3, 1);
INSERT INTO `inspection_team` VALUES (358, 29, 41, 1);
INSERT INTO `inspection_team` VALUES (359, 19, 48, 1);
INSERT INTO `inspection_team` VALUES (360, 12, 30, 1);
INSERT INTO `inspection_team` VALUES (361, 43, 5, 1);
INSERT INTO `inspection_team` VALUES (362, 11, 31, 2);
INSERT INTO `inspection_team` VALUES (363, 8, 50, 2);

-- ----------------------------
-- Table structure for inspection_team_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `inspection_team_arrangement`;
CREATE TABLE `inspection_team_arrangement`  (
  `itArrangeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '巡考组安排id',
  `inspectionTeamId` int(11) NULL DEFAULT NULL COMMENT '巡考组id',
  `schoolId` int(11) NULL DEFAULT NULL COMMENT '学校id',
  `floorId` int(11) NULL DEFAULT NULL COMMENT '楼层id',
  `sessions` int(11) NULL DEFAULT NULL COMMENT '场次（取值1,2,3,4）',
  PRIMARY KEY (`itArrangeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1042 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of inspection_team_arrangement
-- ----------------------------
INSERT INTO `inspection_team_arrangement` VALUES (1010, 356, 1, 6, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1011, 356, 1, 3, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1012, 356, 1, 1, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1013, 356, 1, 2, 4);
INSERT INTO `inspection_team_arrangement` VALUES (1014, 357, 1, 2, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1015, 357, 1, 6, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1016, 357, 1, 3, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1017, 357, 1, 1, 4);
INSERT INTO `inspection_team_arrangement` VALUES (1018, 358, 1, 1, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1019, 358, 1, 2, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1020, 358, 1, 6, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1021, 358, 1, 3, 4);
INSERT INTO `inspection_team_arrangement` VALUES (1022, 359, 1, 3, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1023, 359, 1, 1, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1024, 359, 1, 2, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1025, 359, 1, 6, 4);
INSERT INTO `inspection_team_arrangement` VALUES (1026, 360, 2, 5, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1027, 360, 2, 7, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1028, 360, 2, 5, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1029, 360, 2, 5, 4);
INSERT INTO `inspection_team_arrangement` VALUES (1030, 361, 2, 7, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1031, 361, 2, 5, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1032, 361, 2, 7, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1033, 361, 2, 7, 4);
INSERT INTO `inspection_team_arrangement` VALUES (1034, 362, 3, 8, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1035, 362, 3, 8, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1036, 362, 3, 8, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1037, 362, 3, 4, 4);
INSERT INTO `inspection_team_arrangement` VALUES (1038, 363, 3, 4, 1);
INSERT INTO `inspection_team_arrangement` VALUES (1039, 363, 3, 4, 2);
INSERT INTO `inspection_team_arrangement` VALUES (1040, 363, 3, 4, 3);
INSERT INTO `inspection_team_arrangement` VALUES (1041, 363, 3, 8, 4);

-- ----------------------------
-- Table structure for invigilator_group
-- ----------------------------
DROP TABLE IF EXISTS `invigilator_group`;
CREATE TABLE `invigilator_group`  (
  `invigilatorGroupId` int(11) NOT NULL AUTO_INCREMENT COMMENT '监考组id',
  `examinerId` int(11) NULL DEFAULT NULL COMMENT '主考人员id',
  `firstInvigilatorId` int(11) NULL DEFAULT NULL COMMENT '监考人员一id',
  `secondInvigilatorId` int(11) NULL DEFAULT NULL COMMENT '监考人员二id',
  `eduId` int(11) NULL DEFAULT NULL COMMENT '所属招办id',
  PRIMARY KEY (`invigilatorGroupId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1019 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of invigilator_group
-- ----------------------------
INSERT INTO `invigilator_group` VALUES (1010, 26, 46, 45, 1);
INSERT INTO `invigilator_group` VALUES (1011, 1, 38, 22, 1);
INSERT INTO `invigilator_group` VALUES (1012, 6, 42, 39, 1);
INSERT INTO `invigilator_group` VALUES (1013, 4, 24, 16, 1);
INSERT INTO `invigilator_group` VALUES (1014, 27, 23, 49, 1);
INSERT INTO `invigilator_group` VALUES (1015, 2, 14, 28, 1);
INSERT INTO `invigilator_group` VALUES (1016, 21, 7, 44, 1);
INSERT INTO `invigilator_group` VALUES (1017, 25, 15, 20, 2);
INSERT INTO `invigilator_group` VALUES (1018, 9, 47, 13, 2);

-- ----------------------------
-- Table structure for invigilator_group_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `invigilator_group_arrangement`;
CREATE TABLE `invigilator_group_arrangement`  (
  `igArrangeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '监考组安排id',
  `invigilatorGroupId` int(11) NULL DEFAULT NULL COMMENT '监考组id',
  `schoolId` int(11) NULL DEFAULT NULL COMMENT '学校id',
  `exRoomId` int(11) NULL DEFAULT NULL COMMENT '考场id',
  `sessions` int(11) NULL DEFAULT NULL COMMENT '场次（取值1,2,3,4）',
  PRIMARY KEY (`igArrangeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1554 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of invigilator_group_arrangement
-- ----------------------------
INSERT INTO `invigilator_group_arrangement` VALUES (1518, 1010, 1, 4, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1519, 1011, 1, 3, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1520, 1012, 1, 2, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1521, 1013, 1, 5, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1522, 1014, 2, 9, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1523, 1015, 2, 10, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1524, 1016, 2, 8, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1525, 1017, 3, 14, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1526, 1018, 3, 13, 1);
INSERT INTO `invigilator_group_arrangement` VALUES (1527, 1010, 1, 2, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1528, 1013, 1, 3, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1529, 1011, 1, 4, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1530, 1012, 1, 5, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1531, 1011, 1, 2, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1532, 1012, 1, 3, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1533, 1013, 1, 4, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1534, 1010, 1, 5, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1535, 1013, 1, 2, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1536, 1010, 1, 3, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1537, 1012, 1, 4, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1538, 1011, 1, 5, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1539, 1016, 2, 8, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1540, 1015, 2, 9, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1541, 1014, 2, 10, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1542, 1015, 2, 8, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1543, 1014, 2, 9, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1544, 1016, 2, 10, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1545, 1015, 2, 8, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1546, 1014, 2, 9, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1547, 1016, 2, 10, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1548, 1018, 3, 14, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1549, 1017, 3, 13, 2);
INSERT INTO `invigilator_group_arrangement` VALUES (1550, 1018, 3, 13, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1551, 1017, 3, 14, 3);
INSERT INTO `invigilator_group_arrangement` VALUES (1552, 1018, 3, 14, 4);
INSERT INTO `invigilator_group_arrangement` VALUES (1553, 1017, 3, 13, 4);

-- ----------------------------
-- Table structure for number_of_candidates
-- ----------------------------
DROP TABLE IF EXISTS `number_of_candidates`;
CREATE TABLE `number_of_candidates`  (
  `numOfCanId` int(11) NOT NULL AUTO_INCREMENT COMMENT '考生数目Id',
  `eduId` int(11) NULL DEFAULT NULL COMMENT '教育局id',
  `numOfScience` int(11) NULL DEFAULT NULL COMMENT '理科生人数',
  `numOfArt` int(11) NULL DEFAULT NULL COMMENT '文科生人数',
  `numOfExcellent` int(11) NULL DEFAULT NULL COMMENT '特长生人数',
  PRIMARY KEY (`numOfCanId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of number_of_candidates
-- ----------------------------
INSERT INTO `number_of_candidates` VALUES (1, 1, 90, 30, 30);
INSERT INTO `number_of_candidates` VALUES (2, 2, 30, 31, 30);

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `schoolId` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校id',
  `schoolName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学校名称',
  `eduId` int(11) NULL DEFAULT NULL COMMENT '招生部门id',
  `exRoomExamine` int(11) NULL DEFAULT NULL COMMENT '考场审核状态',
  `typeOfExaminationSite` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考点类型',
  PRIMARY KEY (`schoolId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '合肥工业大学', 1, 2, '理科考点');
INSERT INTO `school` VALUES (2, '安徽大学', 1, 2, '文科考点');
INSERT INTO `school` VALUES (3, '安徽理工大学', 2, 2, '特科考点');

-- ----------------------------
-- Table structure for school_admin_login
-- ----------------------------
DROP TABLE IF EXISTS `school_admin_login`;
CREATE TABLE `school_admin_login`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `schoolId` int(11) NULL DEFAULT NULL COMMENT '所属学校id',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school_admin_login
-- ----------------------------
INSERT INTO `school_admin_login` VALUES ('AHU-1654', '123900', 2);
INSERT INTO `school_admin_login` VALUES ('AHU-1695', '145682', 2);
INSERT INTO `school_admin_login` VALUES ('AUST-1008', '123456', 3);
INSERT INTO `school_admin_login` VALUES ('HFUT-1001', '123456', 1);
INSERT INTO `school_admin_login` VALUES ('HFUT-1012', '987654', 1);
INSERT INTO `school_admin_login` VALUES ('HFUT-1024', '152632', 1);

-- ----------------------------
-- Table structure for status_notes
-- ----------------------------
DROP TABLE IF EXISTS `status_notes`;
CREATE TABLE `status_notes`  (
  `stateId` int(11) NOT NULL AUTO_INCREMENT COMMENT '状态备注id',
  `esId` int(11) NULL DEFAULT NULL COMMENT '考务人员id',
  `stateMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注内容',
  `auditState` int(10) NULL DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`stateId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of status_notes
-- ----------------------------
INSERT INTO `status_notes` VALUES (1, 4, '想退休了', 2);
INSERT INTO `status_notes` VALUES (2, 4, '跳槽', 0);
INSERT INTO `status_notes` VALUES (3, 4, '欺凌学生', 0);
INSERT INTO `status_notes` VALUES (4, 4, '被人打了', 2);
INSERT INTO `status_notes` VALUES (5, 4, '我的身体出现了点问题', 0);

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
INSERT INTO `test_personnel_login` VALUES ('13054648782', '123456');
INSERT INTO `test_personnel_login` VALUES ('13254687862', '123456');
INSERT INTO `test_personnel_login` VALUES ('13465486748', '123456');
INSERT INTO `test_personnel_login` VALUES ('13513245678', '123456');
INSERT INTO `test_personnel_login` VALUES ('13566667777', '123456');
INSERT INTO `test_personnel_login` VALUES ('13566668888', '123456');
INSERT INTO `test_personnel_login` VALUES ('13568713254', '123456');
INSERT INTO `test_personnel_login` VALUES ('13612345678', '132456');
INSERT INTO `test_personnel_login` VALUES ('13646872023', '134562');
INSERT INTO `test_personnel_login` VALUES ('13646873213', '134568');
INSERT INTO `test_personnel_login` VALUES ('13656468872', '123456');
INSERT INTO `test_personnel_login` VALUES ('13656486421', '121312');
INSERT INTO `test_personnel_login` VALUES ('13678523157', '123465');
INSERT INTO `test_personnel_login` VALUES ('13687543217', '132456');
INSERT INTO `test_personnel_login` VALUES ('13765465123', '123456');
INSERT INTO `test_personnel_login` VALUES ('13845413457', '123456');
INSERT INTO `test_personnel_login` VALUES ('13846876821', '123456');
INSERT INTO `test_personnel_login` VALUES ('13853453176', '123456');
INSERT INTO `test_personnel_login` VALUES ('13855146632', '6666666');
INSERT INTO `test_personnel_login` VALUES ('13895110475', '123456');
INSERT INTO `test_personnel_login` VALUES ('13912345678', '45678');
INSERT INTO `test_personnel_login` VALUES ('13946210252', '123456');
INSERT INTO `test_personnel_login` VALUES ('13946787982', '123456');
INSERT INTO `test_personnel_login` VALUES ('13948625861', '123456');
INSERT INTO `test_personnel_login` VALUES ('13984654562', '123456');
INSERT INTO `test_personnel_login` VALUES ('13984654564', '123456');
INSERT INTO `test_personnel_login` VALUES ('15216487893', '134562');
INSERT INTO `test_personnel_login` VALUES ('15260274720', '123456');
INSERT INTO `test_personnel_login` VALUES ('15261846878', '123456');
INSERT INTO `test_personnel_login` VALUES ('15550872402', 'zzxmima');
INSERT INTO `test_personnel_login` VALUES ('15564582563', '123456');
INSERT INTO `test_personnel_login` VALUES ('15668786913', '132456');
INSERT INTO `test_personnel_login` VALUES ('15715690563', '1234');
INSERT INTO `test_personnel_login` VALUES ('15715699999', '123456');
INSERT INTO `test_personnel_login` VALUES ('18765465431', '123456');
INSERT INTO `test_personnel_login` VALUES ('18865316540', '123456');
INSERT INTO `test_personnel_login` VALUES ('18912345678', '132456');
INSERT INTO `test_personnel_login` VALUES ('18935454872', '123456');
INSERT INTO `test_personnel_login` VALUES ('18956522258', '123456');
INSERT INTO `test_personnel_login` VALUES ('19512345678', '45678');
INSERT INTO `test_personnel_login` VALUES ('19616546878', '123456');
INSERT INTO `test_personnel_login` VALUES ('19712345678', '123456');
INSERT INTO `test_personnel_login` VALUES ('19813465482', '123456');
INSERT INTO `test_personnel_login` VALUES ('19822255332', '123456');
INSERT INTO `test_personnel_login` VALUES ('19856453535', '123456');

-- ----------------------------
-- Table structure for violation_record
-- ----------------------------
DROP TABLE IF EXISTS `violation_record`;
CREATE TABLE `violation_record`  (
  `vrId` int(11) NOT NULL AUTO_INCREMENT COMMENT '违规记录id',
  `esId` int(11) NULL DEFAULT NULL COMMENT '考务人员id',
  `year` int(11) NULL DEFAULT NULL COMMENT '年份',
  `vrMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '违规原因',
  `vrPunish` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '违规处罚',
  PRIMARY KEY (`vrId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of violation_record
-- ----------------------------
INSERT INTO `violation_record` VALUES (1, 4, 2018, '辱骂考生', '5年');
INSERT INTO `violation_record` VALUES (2, 4, 2019, '帮助作弊', '永久');
INSERT INTO `violation_record` VALUES (3, 4, 2018, '殴打其他考务人员', '1年');

SET FOREIGN_KEY_CHECKS = 1;
