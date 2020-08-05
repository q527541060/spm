/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : dbutils

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-11-02 22:48:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `springboot2`.`user`;
CREATE TABLE `springboot2`.`user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `springboot2`.`user` VALUES ('1', 'name_525', '48');
INSERT INTO `springboot2`.`user` VALUES ('2', 'name_367', '33');
INSERT INTO `springboot2`.`user` VALUES ('3', 'name_630', '23');
INSERT INTO `springboot2`.`user` VALUES ('4', 'name_230', '34');
INSERT INTO `springboot2`.`user` VALUES ('5', 'name_750', '50');
INSERT INTO `springboot2`.`user` VALUES ('6', 'name_762', '26');
INSERT INTO `springboot2`.`user` VALUES ('7', 'name_433', '38');
INSERT INTO `springboot2`.`user` VALUES ('8', 'name_742', '44');
INSERT INTO `springboot2`.`user` VALUES ('9', 'name_960', '37');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `springboot2`.`student`;
CREATE TABLE `springboot2`.`student` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `springboot2`.`student` VALUES ('1', 'Jack');
INSERT INTO `springboot2`.`student` VALUES ('2', 'Phil');
INSERT INTO `springboot2`.`student` VALUES ('3', 'Jenny');

