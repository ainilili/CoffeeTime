/*
 Navicat Premium Data Transfer

 Source Server         : mall_invite
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.50.221:3306
 Source Schema         : ct

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 12/07/2018 15:14:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ct_account
-- ----------------------------
DROP TABLE IF EXISTS `ct_account`;
CREATE TABLE `ct_account`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ct_temp_config
-- ----------------------------
DROP TABLE IF EXISTS `ct_temp_config`;
CREATE TABLE `ct_temp_config`  (
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `prefix` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `suffix` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `properties_json` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ct_temp_content
-- ----------------------------
DROP TABLE IF EXISTS `ct_temp_content`;
CREATE TABLE `ct_temp_content`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `temp_config_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_format` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ct_temp_datasource
-- ----------------------------
DROP TABLE IF EXISTS `ct_temp_datasource`;
CREATE TABLE `ct_temp_datasource`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `interference` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for live_room
-- ----------------------------
DROP TABLE IF EXISTS `live_room`;
CREATE TABLE `live_room`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `room_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `live_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `live_flash_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
