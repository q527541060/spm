/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.10_3306
 Source Server Type    : MySQL
 Source Server Version : 50543
 Source Host           : 192.168.1.10:3306
 Source Schema         : db_spm

 Target Server Type    : MySQL
 Target Server Version : 50543
 File Encoding         : 65001

 Date: 24/07/2020 17:01:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for a_line
-- ----------------------------

CREATE TABLE IF NOT EXISTS `a_line`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '线体ID',
  `LineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '线体编号',
  `lineContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线体说明',
  `createDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'remark',
  PRIMARY KEY (`id`, `LineNo`) USING BTREE,
  UNIQUE INDEX `LineNo`(`LineNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 459 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '线体总表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_status
-- ----------------------------

CREATE TABLE IF NOT EXISTS `a_status`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'aoi机器状态数据ID',
  `lineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'aoi机器线体',
  `barcode` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前条码或上一片条码',
  `ipAddress` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前设备局域网IP',
  `equipmentNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `loft` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备楼层',
  `factory` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备厂区',
  `lane` int(11) NULL DEFAULT NULL COMMENT '设备轨道号',
  `customer` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名',
  `start` smallint(1) NULL DEFAULT NULL COMMENT '设备开启',
  `run` smallint(1) NULL DEFAULT NULL COMMENT '设备运行',
  `stop` smallint(1) NULL DEFAULT NULL COMMENT '设备停止',
  `idle` smallint(1) NULL DEFAULT NULL COMMENT '设备闲置',
  `init` smallint(1) NULL DEFAULT NULL COMMENT '设备初始化',
  `error` smallint(1) NULL DEFAULT NULL COMMENT '设备报警',
  `towerR` smallint(1) NULL DEFAULT NULL COMMENT '红灯',
  `towerG` smallint(1) NULL DEFAULT NULL COMMENT '绿灯',
  `towerY` smallint(1) NULL DEFAULT NULL COMMENT '黄灯',
  `status` smallint(1) NULL DEFAULT NULL COMMENT '预留',
  `errContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报警详细信息',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'remark',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `status_line_lineNo_aoi`(`lineNo`) USING BTREE,
  CONSTRAINT `status_line_lineNo_aoi` FOREIGN KEY (`lineNo`) REFERENCES `a_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 358 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'aoi-设备状态' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_component
-- ----------------------------

CREATE TABLE IF NOT EXISTS `s_component`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'component数据 ',
  `pcbIdLine` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `componentName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `componentInspectResult` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_defaultSetting
-- ----------------------------

CREATE TABLE IF NOT EXISTS `s_defaultSetting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `settingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `settingValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 1, 'boardMachineTimeLimit', '5', '2020-07-24 17:09:50', '看板实时刷新(小时单位)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=1);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 2, 'FPY', '1', '2020-07-17 18:19:31', '看板是否需要FPY图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=2);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 3, 'CPK', '1', '2020-07-16 17:40:35', '看板是否需要CPK图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=3);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 4, 'Product', '1', '2020-07-17 18:19:25', '看板是否需要Product图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=4);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 5, 'DefaultTop5', '1', '2020-07-16 17:42:31', '看板是否需要top5图'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=5);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 6, 'defaultType', '1;2;3;4;5', '2020-07-24 17:10:15', 'top5设置'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=6);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 7, 'boardMachineRefreshTime', '4', '2020-07-17 18:20:45', '看板实时刷新间隔(秒单位)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=7);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 8, 'autoDelete', '0', '2020-07-17 10:20:53', '自动删除'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=8);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 9, 'autoDeleteDays', '35', '2020-07-24 17:10:04', '保存数据天数'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=9);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 10, 'standCPK', '1', '2020-07-24 17:10:04', 'CPK设置标准值'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=10);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 11, 'Frequency-start', '8', '2020-07-24 17:10:04', '班次设置值(例如早上八点:8   24小时制)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=11);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 12, 'autoDelete-MaxDays', '365', '2020-07-24 17:10:04', '自动删除最大上限设置天数(一般不设置,如果需要删除天数大于365天请修改天数 例如500天 500)'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=12);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 13, 'hChartColor', '0', '2020-07-24 17:10:04', '选择chart柱形图图表主题皮肤'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=13);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 14, 'backgroundColor', '0', '2020-07-24 17:10:04', '选择spc系统背景皮肤'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=14);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 15, 'passPcbYeild', '85', '2020-07-24 17:10:04', '看板直通率标准设定值 例如85% 85'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=15);
INSERT INTO `s_defaultSetting`(id,settingName,settingValue,updateTime,remark) SELECT 16, 'boardView-chartMove', '1', '2020-07-24 17:10:04', '看板动画渲染开关'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_defaultSetting where id=16);

-- ----------------------------
-- Table structure for s_errorcode
-- ----------------------------

CREATE TABLE IF NOT EXISTS `s_errorcode`  (
  `Code` int(11) NOT NULL,
  `Description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


INSERT INTO `s_errorcode` (Code,Description) SELECT 10000,'基准点出错'FROM DUAL WHERE NOT EXISTS ( SELECT * FROM s_errorcode where Code=10000);
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


-- ----------------------------
-- Table structure for s_job
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm`.`s_job`  (
  `jobId` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jobVersion` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creatDate` datetime NULL DEFAULT NULL,
  `updateDate` datetime NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`jobId`, `jobName`) USING BTREE,
  UNIQUE INDEX `jobName`(`jobName`) USING BTREE,
  INDEX `fore_job_line_lineNo`(`lineNo`) USING BTREE,
  CONSTRAINT `fore_job_line_lineNo` FOREIGN KEY (`lineNo`) REFERENCES `s_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'job总表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_line
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm`.`s_line`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LineNo` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `lineContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDate` datetime NULL DEFAULT NULL,
  `updateDate` datetime NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `LineNo`) USING BTREE,
  UNIQUE INDEX `LineNo`(`LineNo`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 459 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '线体总表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_pad
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm`.`s_pad`  (
  `id` bigint(255) NOT NULL,
  `padId` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pcbidLine` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `padIndex` bigint(20) NULL DEFAULT NULL,
  `componentId` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `padInspectResult` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `defectTypeCode` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `defectTypeName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `padImagePath` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pad2dImage` blob NULL,
  `pad3dImage` blob NULL,
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
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fore_pad_pcb_pcbidLine`(`pcbidLine`) USING BTREE,
  CONSTRAINT `fore_pad_pcb_pcbidLine` FOREIGN KEY (`pcbidLine`) REFERENCES `s_pcb` (`pcbIdLine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '焊盘' ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for s_pcb
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm`.`s_pcb`  (
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
  `boardBarcode` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `componentTableName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `padTableName` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayBarcode` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayWidth` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayLength` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrayinspectResult` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `pcbIdLine`) USING BTREE,
  INDEX `fore_pcb_line_lineNo`(`lineNo`) USING BTREE,
  INDEX `fore_pcb_job_jobNo`(`jobName`) USING BTREE,
  INDEX `pcbIdLine`(`pcbIdLine`) USING BTREE,
  CONSTRAINT `fore_pcb_job_jobNo` FOREIGN KEY (`jobName`) REFERENCES `s_job` (`jobName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fore_pcb_line_lineNo` FOREIGN KEY (`lineNo`) REFERENCES `s_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 263 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spi-pcb表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_status
-- ----------------------------

CREATE TABLE IF NOT EXISTS `db_spm`.`s_status`  (
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
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `status_line_lineNo`(`lineNo`) USING BTREE,
  CONSTRAINT `status_line_lineNo` FOREIGN KEY (`lineNo`) REFERENCES `s_line` (`LineNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 358 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spi-设备状态' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------

CREATE TABLE IF NOT EXISTS `s_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `psw` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lineNoStr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `lineNoStr`) USING BTREE,
  INDEX `lineWithUser`(`lineNoStr`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
