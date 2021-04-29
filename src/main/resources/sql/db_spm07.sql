/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.10_3306
 Source Server Type    : MySQL
 Source Server Version : 50543
 Source Host           : 192.168.1.10:3306
 Source Schema         : db_spm07

 Target Server Type    : MySQL
 Target Server Version : 50543
 File Encoding         : 65001

 Date: 24/07/2020 17:01:02
*/
CREATE TABLE if not exists `db_spm07`.`s_realTimeSituation`  (
                                      `id` bigint(20) NOT NULL,
                                      `lineNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `DataTime` datetime NULL DEFAULT NULL,
                                      `JobName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `PadGroup` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `PCBIDs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `PCBBarcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `Rules` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `FieldType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `Resources` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `remark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `remark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `remark3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `index_lineno`(`lineNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for s_component
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_component`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'component数据 ',
  `pcbIdLine` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `componentName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `componentInspectResult` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_defaultSetting
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_defaultSetting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `settingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `settingValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
/*INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 1, 'boardMachineTimeLimit', '5', '2020-07-24 17:09:50', '看板实时刷新(小时单位)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=1);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 2, 'FPY', '1', '2020-07-17 18:19:31', '看板是否需要FPY图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=2);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 3, 'CPK', '1', '2020-07-16 17:40:35', '看板是否需要CPK图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=3);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 4, 'Product', '1', '2020-07-17 18:19:25', '看板是否需要Product图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=4);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 5, 'DefaultTop5', '1', '2020-07-16 17:42:31', '看板是否需要top5图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=5);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 6, 'defaultType', '1;2;3;4;5', '2020-07-24 17:10:15', 'top5设置'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=6);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 7, 'boardMachineRefreshTime', '4', '2020-07-17 18:20:45', '看板实时刷新间隔(秒单位)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=7);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 8, 'autoDelete', '0', '2020-07-17 10:20:53', '自动删除'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=8);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 9, 'autoDeleteDays', '35', '2020-07-24 17:10:04', '保存数据天数'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=9);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 10, 'standCPK', '1', '2020-07-24 17:10:04', 'CPK设置标准值'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=10);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 11, 'Frequency-start', '8', '2020-07-24 17:10:04', '班次设置值(例如早上八点:8   24小时制)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=11);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 12, 'autoDelete-MaxDays', '365', '2020-07-24 17:10:04', '自动删除最大上限设置天数(一般不设置,如果需要删除天数大于365天请修改天数 例如500天 500)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=12);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 13, 'hChartColor', '0', '2020-07-24 17:10:04', '选择chart柱形图图表主题皮肤'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=13);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 14, 'backgroundColor', '2', '2020-07-24 17:10:04', '选择spc系统背景皮肤'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=14);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 15, 'passPcbYeild', '85', '2020-07-24 17:10:04', '看板直通率标准设定值 例如85% 85'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=15);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 16, 'boardView-chartMove', '0', '2020-07-24 17:10:04', '看板动画渲染开关'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=16);
INSERT INTO `db_spm07`.`s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 17, 'showPad2DImageMode', '0', '2020-07-24 17:10:04', '选择查看缺陷图片方式'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM `db_spm07`.s_defaultSetting where id=17);
*/
-- ----------------------------
-- Table structure for s_errorcode
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_errorcode`  (
  `Code` int(11) NOT NULL,
  `Description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


/*INSERT INTO `s_errorcode` (Code,Description) SELECT 10000,'基准点出错'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10000);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10001,'条码错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10001);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10002,'CPK 错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10002);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10003,'直通率错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10003);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10004,'realtime 错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10004);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10005,'NGBuffer 错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10005);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10006,'平整度错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10006);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10007,'连续不良错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10007);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10008,'普通错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10008);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10009,'跳出手动输入条码对话框'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10009);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10010,'进板扫描错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10010);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10011,'出板扫描错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10011);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10012,'条码连读错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10012);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10013,'MES 错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10013);
INSERT INTO `s_errorcode` (Code,Description) SELECT 10014,'OCV 错误'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10014);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20000,'紧急开关被按下'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20000);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20001,'安全门被打开'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20001);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20002,'气压不足'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20002);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20003,'X 轴负极限感应器不亮'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20003);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20004,'X 轴负极限感应器不亮'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20004);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20005,'X 轴正极限感应器不亮'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20005);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20006,'X 轴负极限感应器不亮'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20006);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20007,'X 轴正极限感应器不亮'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20007);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20008,'X 轴负极限感应器不亮'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20008);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20009,'1 轨“通过”状态下，进板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20009);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20010,'1 轨进板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20010);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20011,'1  轨“通过”状态下，进板口到轨道中间卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20011);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20012,'1 轨进板口到轨道中间卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20012);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20013,'1 轨减速感应超时'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20013);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20014,'1 轨调宽方向走反'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20014);

INSERT INTO `s_errorcode` (Code,Description) SELECT 20015,'1 轨调宽方向走反'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20015);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20016,'1 轨“通过”状态下，出板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20016);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20017,'1 轨出板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20017);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20018,'1 轨轨道中间到出板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20018);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20019,'1 轨阻挡器上升异常'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20019);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20020,'1 轨轨道上同时存在两块板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20020);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20021,'1 轨升降台上升异常'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20021);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20022,'2 轨“通过”状态下，进板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20022);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20023,'2 轨进板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20023);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20024,'2 轨“通过”状态下，进板口到轨道中间卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20024);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20025,'2 轨进板口到轨道中间卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20025);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20026,'2 轨减速感应超时'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20026);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20027,'2 轨调宽方向走反'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20027);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20028,'2 轨调宽方向走反'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20028);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20029,'2 轨“通过”状态下，出板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20029);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20030,'2 轨出板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20030);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20031,'2 轨轨道中间到出板口卡板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20031);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20032,'2 轨阻挡器上升异常'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20032);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20033,'2 轨轨道上同时存在两块板'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20033);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20034,'2 轨升降台上升异常'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20034);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20035,'2 轨轨道调宽方向走反'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20035);
INSERT INTO `s_errorcode` (Code,Description) SELECT 20036,'2 轨轨道调宽方向走反'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=20036);
*/

-- ----------------------------
-- Table structure for s_job
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_job`  (
  `jobId` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jobVersion` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creatDate` datetime NULL DEFAULT NULL,
  `updateDate` datetime NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`jobId`, `jobName`) USING BTREE,
  UNIQUE INDEX `jobName`(`jobName`) USING BTREE
-- INDEX `fore_job_line_lineNo`(`lineNo`) USING BTREE
--  #CONSTRAINT `fore_job_line_lineNo` FOREIGN KEY (`lineNo`) REFERENCES `s_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'job总表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_line
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_line`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `lineContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDate` datetime NULL DEFAULT NULL,
  `updateDate` datetime NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `LineNo`) USING BTREE,
  UNIQUE INDEX `LineNo`(`LineNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 459 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '线体总表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_pad
-- ----------------------------
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_1`  (
  `id` bigint(255)  NOT NULL AUTO_INCREMENT,
  `padId` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pcbidLine` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `padIndex` bigint(20) NULL DEFAULT NULL,
  `componentId` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `padInspectResult` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `defectTypeCode` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `defectTypeName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `padImagePath` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pad2dImage` longtext null default  NULL,
  `pad3dImage` longtext null default  NULL,
  `height` double NULL DEFAULT NULL,
  `area` double NULL DEFAULT NULL,
  `volume` double NULL DEFAULT NULL,
  `offsetx` double NULL DEFAULT NULL,
  `offsety` double NULL DEFAULT NULL,
  `perHeight` double NULL DEFAULT NULL,
  `perArea` double NULL DEFAULT NULL,
  `perVolume` double NULL DEFAULT NULL,
  `perOffsetx` double NULL DEFAULT NULL,
  `perOffsety` double NULL DEFAULT NULL,
  `shape` double NULL DEFAULT NULL,
  `bridgeType` int(11) NULL DEFAULT NULL,
  `uHeight` double NULL DEFAULT NULL,
  `lHeight` double NULL DEFAULT NULL,
  `uArea` double NULL DEFAULT NULL,
  `lArea` double NULL DEFAULT NULL,
  `uVolume` double NULL DEFAULT NULL,
  `lVolume` double NULL DEFAULT NULL,
  `uOffsetx` double NULL DEFAULT NULL,
  `uOffsety` double NULL DEFAULT NULL,
  `uPerHeight` double NULL DEFAULT NULL,
  `lPerHeight` double NULL DEFAULT NULL,
  `uPerArea` double NULL DEFAULT NULL,
  `lPerArea` double NULL DEFAULT NULL,
  `uPerVolume` double NULL DEFAULT NULL,
  `lPerVolume` double NULL DEFAULT NULL,
  `uPerOffsetx` double NULL DEFAULT NULL,
  `uPerOffsety` double NULL DEFAULT NULL,
  `padTableID` bigint(20) NULL DEFAULT NULL,
  `componentTableID` bigint(20) NULL DEFAULT NULL,
  `pad2dImageBase64` longtext Null DEFAULT NULL,
  `pad3dImageBase64` longtext Null DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fore_pad_1_pcb_pcbidLine`(`pcbidLine`) USING BTREE
  ##, #CONSTRAINT `fore_pad_1_pcb_pcbidLine` FOREIGN KEY (`pcbidLine`) REFERENCES `s_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '焊盘' ROW_FORMAT = Compact;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_2` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_3` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_4` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_5` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_6` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_7` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_8` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_9` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_10` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_11` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_12` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_13` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_14` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_15` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_16` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_17` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_18` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_19` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_20` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_21` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_22` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_23` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_24` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_25` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_26` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_27` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_28` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_29` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_30` LIKE `db_spm07`.`s_pad_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pad_31` LIKE `db_spm07`.`s_pad_1`;

-- ----------------------------
-- Table structure for s_pcb
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_pcb`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pcbIdLine` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jobName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `laneNo` int(11) NULL DEFAULT NULL,
  `inspectResult` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inspectStarttime` datetime NULL DEFAULT NULL,
  `inspectEndtime` datetime NULL DEFAULT NULL,
  `boardWidth` double NULL DEFAULT NULL,
  `boardLength` double NULL DEFAULT NULL,
  `boardBarcode` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `componentTableName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `padTableName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayBarcode` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayWidth` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayLength` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayinspectResult` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `totalpadCount` int(11) NULL DEFAULT NULL,
  `passpadCount` int(11) NULL DEFAULT NULL,
  `ngpadCount` int(11) NULL DEFAULT NULL,
  `goodpadCount` int(11) NULL DEFAULT NULL,
  `shiftyCount` int(11) NULL DEFAULT NULL,
  `bridgeCount` int(11) NULL DEFAULT NULL,
  `shapeerrorCount` int(11) NULL DEFAULT NULL,
  `smearedCount` int(11) NULL DEFAULT NULL,
  `coplanarityCount` int(11) NULL DEFAULT NULL,
  `prebridgeCount` int(11) NULL DEFAULT NULL,
  `padareapercentCount` int(11) NULL DEFAULT NULL,
  `shiftxCount` int(11) NULL DEFAULT NULL,
  `otherCount` int(11) NULL DEFAULT NULL,
  `lowareaCount` int(11) NULL DEFAULT NULL,
  `overareaCount` int(11) NULL DEFAULT NULL,
  `lowheightCount` int(11) NULL DEFAULT NULL,
  `overheightCount` int(11) NULL DEFAULT NULL,
  `excessCount` int(11) NULL DEFAULT NULL,
  `insufficientCount` int(11) NULL DEFAULT NULL,
  `missingCount` int(11) NULL DEFAULT NULL,
  `hCpk` double NULL DEFAULT NULL,
  `aCpk` double NULL DEFAULT NULL,
  `VCPK` double NULL DEFAULT NULL,
  `shithxCpk` double NULL DEFAULT NULL,
  `shithyCpk` double NULL DEFAULT NULL,
  `ucl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lcl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `pcbIdLine`) USING BTREE,
  INDEX `fore_pcb0_line_lineNo`(`lineNo`) USING BTREE,
  INDEX `fore_pcb0_job_jobNo`(`jobName`) USING BTREE,
  INDEX `pcbIdLine`(`pcbIdLine`) USING BTREE
  # #CONSTRAINT `fore_pcb_job_jobNo` FOREIGN KEY (`jobName`) REFERENCES `s_job` (`jobName`) ON DELETE CASCADE ON UPDATE CASCADE,
  # #CONSTRAINT `fore_pcb_line_lineNo` FOREIGN KEY (`lineNo`) REFERENCES `s_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB AUTO_INCREMENT = 263 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spi-pcb表' ROW_FORMAT = Compact;
-- ----------------------------
-- Table structure for s_status
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_status`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '机器状态数据',
  `lineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `barcode` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ipAddress` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `equipmentNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loft` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `factory` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lane` int(11) NULL DEFAULT NULL,
  `customer` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start` smallint(1) NULL DEFAULT NULL,
  `run` smallint(1) NULL DEFAULT NULL,
  `stop` smallint(1) NULL DEFAULT NULL,
  `idle` smallint(1) NULL DEFAULT NULL,
  `init` smallint(1) NULL DEFAULT NULL,
  `error` smallint(1) NULL DEFAULT NULL,
  `towerR` smallint(1) NULL DEFAULT NULL,
  `towerG` smallint(1) NULL DEFAULT NULL,
  `towerY` smallint(1) NULL DEFAULT NULL,
  `status` smallint(1) NULL DEFAULT NULL,
  `errContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `status_line_lineNo`(`lineNo`) USING BTREE
   #CONSTRAINT `status_line_lineNo` FOREIGN KEY (`lineNo`) REFERENCES `s_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 358 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spi-设备状态' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `psw` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lineNoStr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `lineNoStr`) USING BTREE,
  INDEX `lineWithUser`(`lineNoStr`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_realTimeAutoRefresh`  (
                                    `id` bigint(20) NOT NULL,
                                    `LineNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `PCBIDs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `X1` double NULL DEFAULT NULL,
                                    `X2` double NULL DEFAULT NULL,
                                    `X3` double NULL DEFAULT NULL,
                                    `X4` double NULL DEFAULT NULL,
                                    `X5` double NULL DEFAULT NULL,
                                    `Average` double NULL DEFAULT NULL,
                                    `alpha` double NULL DEFAULT NULL,
                                    `R` double NULL DEFAULT NULL,
                                    `Ca` double NULL DEFAULT NULL,
                                    `Cp` double NULL DEFAULT NULL,
                                    `Cpk` double NULL DEFAULT NULL,
                                    `PCBBarcodes` longtext NULL DEFAULT NULL,
                                    `XUCL` double NULL DEFAULT NULL,
                                    `XCL` double NULL DEFAULT NULL,
                                    `XLCL` double NULL DEFAULT NULL,
                                    `USL` double NULL DEFAULT NULL,
                                    `LSL` double NULL DEFAULT NULL,
                                    `RUCL` double NULL DEFAULT NULL,
                                    `RCL` double NULL DEFAULT NULL,
                                    `RLCL` double NULL DEFAULT NULL,
                                    `create_time` double NULL DEFAULT NULL,
                                    `remark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `spi_autoRefresh_lineno`(`LineNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`s_realTimeConfig`  (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                    `JobName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `LineNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `LaneNo` int(11) NULL DEFAULT NULL,
                                    `PadGroup` longtext NULL DEFAULT NULL,
                                    `Pad` longtext NULL DEFAULT NULL,
                                    `RealTimeType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `StandCPK` double NULL DEFAULT NULL,
                                    `PCBSubGroup` int(20) NULL DEFAULT NULL,
                                    `Rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `GetPCBByTime` tinyint(4) NULL DEFAULT NULL,
                                    `USL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `MSL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `LSL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `GetPCBTimeInterver` int(20) NULL DEFAULT NULL,
                                    `LastUpdateTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `create_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `remark5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `spi_realTime_config_lineno`(`LineNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
-- ----------------------------
-- Table structure for a_line
-- ----------------------------
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_line`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '线体ID',
    `aoiMode` int(1) NULL DEFAULT NULL,
    `LineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '线体编号',
    `lineContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线体说明',
    `createDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updateDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'remark',
    PRIMARY KEY (`id`, `LineNo`) USING BTREE,
    UNIQUE INDEX `LineNo`(`LineNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 459 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '线体总表' ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jobVersion` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creatDate` datetime NULL DEFAULT NULL,
  `updateDate` datetime NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aoiMode` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `jobName`) USING BTREE,
  UNIQUE INDEX `ajobName`(`jobName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_status`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lineNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `barcode` varbinary(55) NULL DEFAULT NULL,
  `customer` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `equipmentNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` smallint(1) NULL DEFAULT NULL,
  `errContent` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `error` int(1) NULL DEFAULT NULL,
  `factory` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idle` int(1) NULL DEFAULT NULL,
  `init` int(1) NULL DEFAULT NULL,
  `ipAddress` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lane` int(1) NULL DEFAULT NULL,
  `loft` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `run` int(1) NULL DEFAULT NULL,
  `start` int(1) NULL DEFAULT NULL,
  `stop` int(1) NULL DEFAULT NULL,
  `towerG` int(1) NULL DEFAULT NULL,
  `towerR` int(1) NULL DEFAULT NULL,
  `towerY` int(1) NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `aoiMode` int(1) NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_astatus_lineNo_aline`(`lineNo`) USING BTREE
   #CONSTRAINT `FK_Reference_astatus_lineNo_aline` FOREIGN KEY (`lineNo`) REFERENCES `db_spm07`.`a_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_pcb`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lineNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jobName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aoiMode` int(1) NULL DEFAULT NULL,
  `jobversion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jobmodifyDate` datetime NULL DEFAULT NULL,
  `inspectStarttime` datetime NULL DEFAULT NULL,
  `inspectEndtime` datetime NULL DEFAULT NULL,
  `inspectResult` int(1) NULL DEFAULT NULL,
  `laneNo` int(1) NULL DEFAULT NULL,
  `pcbIdLine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `carrierbarcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `boardBarcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayInfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `componentTableName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fovTableName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `windowTableName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `totalArrayCount` int(11) NULL DEFAULT NULL,
  `goodArrayCount` int(11) NULL DEFAULT NULL,
  `ngArrayCount` int(11) NULL DEFAULT NULL,
  `passArrayCount` int(11) NULL DEFAULT NULL,
  `otherArrayCount` int(11) NULL DEFAULT NULL,
  `totalComponentCount` int(11) NULL DEFAULT NULL,
  `goodComponentCount` int(11) NULL DEFAULT NULL,
  `passComponentCount` int(11) NULL DEFAULT NULL,
  `ngComponentCount` int(11) NULL DEFAULT NULL,
  `otherComponentCount` int(11) NULL DEFAULT NULL,
  `customCount` int(11) NULL DEFAULT NULL,
  `defaultCount` int(11) NULL DEFAULT NULL,
  `missingCount` int(11) NULL DEFAULT NULL,
  `shiftXCount` int(11) NULL DEFAULT NULL,
  `shiftYCount` int(11) NULL DEFAULT NULL,
  `rotationCount` int(11) NULL DEFAULT NULL,
  `bridgeCount` int(11) NULL DEFAULT NULL,
  `voidCount` int(11) NULL DEFAULT NULL,
  `tombStoneCount` int(11) NULL DEFAULT NULL,
  `pinLiftCount` int(11) NULL DEFAULT NULL,
  `solderBeadCount` int(11) NULL DEFAULT NULL,
  `smearCount` int(11) NULL DEFAULT NULL,
  `polarityCount` int(11) NULL DEFAULT NULL,
  `reverseCount` int(11) NULL DEFAULT NULL,
  `wrongPartCount` int(11) NULL DEFAULT NULL,
  `noSolderCount` int(11) NULL DEFAULT NULL,
  `copperExposureCount` int(11) NULL DEFAULT NULL,
  `excessSolderCount` int(11) NULL DEFAULT NULL,
  `solderingCount` int(11) NULL DEFAULT NULL,
  `excessPartsCount` int(11) NULL DEFAULT NULL,
  `barcodeCount` int(11) NULL DEFAULT NULL,
  `eNM_Defect_Type_ENMMaxLengthCount` int(11) NULL DEFAULT NULL,
  `hCpk` double NULL DEFAULT NULL,
  `aCpk` double NULL DEFAULT NULL,
  `vcpk` double NULL DEFAULT NULL,
  `shithxCpk` double NULL DEFAULT NULL,
  `shithyCpk` double NULL DEFAULT NULL,
  `lcl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ucl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `pcbIdLine`) USING BTREE,
  INDEX `pcbIdLine`(`pcbIdLine`) USING BTREE,
  INDEX `FK_Reference_apcb_jobName_ajob`(`jobName`) USING BTREE,
  INDEX `FK_Reference__apcb_lineNo_aline`(`lineNo`) USING BTREE,
  INDEX `aoiMode`(`aoiMode`) USING BTREE
   #CONSTRAINT `FK_Reference_apcb_jobName_ajob` FOREIGN KEY (`jobName`) REFERENCES `db_spm07`.`a_job` (`jobName`) ON DELETE CASCADE ON UPDATE CASCADE,
   #CONSTRAINT `FK_Reference__apcb_lineNo_aline` FOREIGN KEY (`lineNo`) REFERENCES `db_spm07`.`a_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_fov`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pcbIdLine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aoiMode` int(1) NULL DEFAULT NULL,
  `pcbImagePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `boardposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `pcbImageBase64` longtext NULL DEFAULT NULL,
  `fovIndex` int(11) NULL DEFAULT NULL,
  `fovposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `fovimageInfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `fovimageBase64`  longtext Null DEFAULT NULL,
  `fov3dImageBase64`  longtext Null DEFAULT NULL,
  `inspectStarttime` datetime NULL DEFAULT NULL,
  `inspectEndtime` datetime NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference1_afov_pcbIdLine_apcb`(`pcbIdLine`) USING BTREE
   #CONSTRAINT `FK_Reference_afov_pcbIdLine_apcb` FOREIGN KEY (`pcbIdLine`) REFERENCES `db_spm07`.`a_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_1`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pcbIdLine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aoiMode` int(1) NULL DEFAULT NULL,
  `arrayIndex` int(5) NULL DEFAULT NULL,
  `fovIndex` int(5) NULL DEFAULT NULL,
  `partdesignate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `partno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packagetype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `componentposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `componentType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `result` int(1) NULL DEFAULT NULL,
  `defectType` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imageInfo` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `valueInfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `height` double NULL DEFAULT NULL,
  `perheight` double NULL DEFAULT NULL,
  `xshift` double NULL DEFAULT NULL,
  `perxshift` double NULL DEFAULT NULL,
  `yshift` double NULL DEFAULT NULL,
  `peryshift` double NULL DEFAULT NULL,
  `angle` double NULL DEFAULT NULL,
  `perangle` double NULL DEFAULT NULL,
  `volume` double NULL DEFAULT NULL,
  `bigvolume` double NULL DEFAULT NULL,
  `planeness` double NULL DEFAULT NULL,
  `uplanenesswindowid` double NULL DEFAULT NULL,
  `lplanenesswindowid` double NULL DEFAULT NULL,
  `linearity` double NULL DEFAULT NULL,
  `ulinearitywindowid` double NULL DEFAULT NULL,
  `llinearitywindowid` double NULL DEFAULT NULL,
  `similarity` double NULL DEFAULT NULL,
  `polarity` double NULL DEFAULT NULL,
  `area` double NULL DEFAULT NULL,
  `bigarea` double NULL DEFAULT NULL,
  `perarea` double NULL DEFAULT NULL,
  `inspectStarttime` datetime NULL DEFAULT NULL,
  `inspectEndtime` datetime NULL DEFAULT NULL,
  `comImageBase64` longtext NULL DEFAULT NULL,
  `com3dImageBase64` longtext NULL DEFAULT NULL,
  `historyDefectRecord` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_acomponent1_pcbIdLine_apcb`(`pcbIdLine`) USING BTREE
   #CONSTRAINT `FK_Reference_acomponent1_pcbIdLine_apcb` FOREIGN KEY (`pcbIdLine`) REFERENCES `db_spm07`.`a_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_2` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_3` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_4` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_5` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_6` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_7` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_8` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_9` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_10` LIKE `db_spm07`.`a_component_1`;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_11` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_12` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_13` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_14` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_15` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_16` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_17` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_18` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_19` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_20` LIKE `db_spm07`.`a_component_1`;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_21` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_22` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_23` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_24` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_25` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_26` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_27` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_28` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_29` LIKE `db_spm07`.`a_component_1`;
CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_30` LIKE `db_spm07`.`a_component_1`;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_component_31` LIKE `db_spm07`.`a_component_1`;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_window`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comid` bigint(20) NULL DEFAULT NULL,
  `pcbIdLine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aoiMode` int(1) NULL DEFAULT NULL,
  `arrayIndex` int(5) NULL DEFAULT NULL,
  `fovIndex` int(5) NULL DEFAULT NULL,
  `partdesignate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `windowIndex` int(20) NULL DEFAULT NULL,
  `result` int(1) NULL DEFAULT NULL,
  `windowposition` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `valueInfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `height` double NULL DEFAULT NULL,
  `perheight` double NULL DEFAULT NULL,
  `xshift` double NULL DEFAULT NULL,
  `perxshift` double NULL DEFAULT NULL,
  `yshift` double NULL DEFAULT NULL,
  `peryshift` double NULL DEFAULT NULL,
  `angle` double NULL DEFAULT NULL,
  `perangle` double NULL DEFAULT NULL,
  `volume` double NULL DEFAULT NULL,
  `bigvolume` double NULL DEFAULT NULL,
  `pervolume` double NULL DEFAULT NULL,
  `planeness` double NULL DEFAULT NULL,
  `uplanenesswindowid` double NULL DEFAULT NULL,
  `lplanenesswindowid` double NULL DEFAULT NULL,
  `linearity` double NULL DEFAULT NULL,
  `ulinearitywindowid` double NULL DEFAULT NULL,
  `llinearitywindowid` double NULL DEFAULT NULL,
  `similarity` double NULL DEFAULT NULL,
  `polarity` double NULL DEFAULT NULL,
  `area` double NULL DEFAULT NULL,
  `bigarea` double NULL DEFAULT NULL,
  `perarea` double NULL DEFAULT NULL,
  `algorithmparam` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `winImageBase64` longtext NULL DEFAULT NULL,
  `win3dImageBase64` longtext NULL DEFAULT NULL,
  `historyDefectRecord` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
 INDEX `FK_Reference_awindow_pcbIdLine_apcb`(`pcbIdLine`) USING BTREE
   #CONSTRAINT `FK_Reference_awindow_pcbIdLine_apcb` FOREIGN KEY (`pcbIdLine`) REFERENCES `db_spm07`.`a_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

CREATE TABLE IF NOT EXISTS `db_spm07`.`a_defaultType`  (
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `errorcode` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;


