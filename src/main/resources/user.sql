/*
 Navicat Premium Data Transfer

 Source Server         : aliyunRDS
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : rm-bp1uyn130er2x0gckho.mysql.rds.aliyuncs.com:3306
 Source Schema         : authenticion

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 18/03/2024 17:15:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `external_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `last_login_time` datetime NULL DEFAULT NULL,
  `creation_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `can_access` tinyint NOT NULL DEFAULT 1,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `external_user_id`(`external_user_id` ASC) USING BTREE,
  UNIQUE INDEX `idx_external_user_id`(`external_user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
