/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : homework

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2016-02-27 19:48:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `name` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('admin01', 'admin01', '男', '100', '110', 'xian');
INSERT INTO `users` VALUES ('asda', '111', '男', '12', '111', '111');
INSERT INTO `users` VALUES ('assa', 'asas', '男', '13', 'assa', 'asas');
INSERT INTO `users` VALUES ('bg', '123', '男', '12', '123', 'sss');
INSERT INTO `users` VALUES ('pange', '123456', '男', '31', 'asAS', 'ass');
INSERT INTO `users` VALUES ('pange1', '123456', '男', '15', '1223545', '南极');
INSERT INTO `users` VALUES ('xls', '123456', '男', '35', '18229022670', '南极');
INSERT INTO `users` VALUES ('xls1', '123456', '男', '32', '18229022670', '石家庄');
INSERT INTO `users` VALUES ('xls56i', 'xulinsong', '男', '21', '18229022670', '石家庄');
INSERT INTO `users` VALUES ('xulinsong', '123456', '男', '21', '132566', '石家庄');
INSERT INTO `users` VALUES ('xxc', '123456', '男', '21', 'ksaodkap', 'kasdj');
INSERT INTO `users` VALUES ('哈哈哈', '111111', '男', '32', '18229022670', '南极');
INSERT INTO `users` VALUES ('许林松', '123456', '男', '21', '18229022670', '南极');
INSERT INTO `users` VALUES ('阿萨斯', '123456', '男', '24', '24133321220', '就看见');
