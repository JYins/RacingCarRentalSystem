/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : vehicle_lease_manager

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 21/01/2024 16:10:51
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Username',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Password',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Admin' COMMENT 'Role',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Add Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Admin Table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 'Admin', '2024-01-21 12:20:26');

-- ----------------------------
-- Table structure for car_dispense
-- ----------------------------
DROP TABLE IF EXISTS `car_dispense`;
CREATE TABLE `car_dispense`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Create Time',
  `car_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Name',
  `car_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Type',
  `brand` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Brand',
  `license_plate_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'License Plate Number',
  `body_color` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Body Color',
  `seating_capacity` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Seating Capacity',
  `hourly_price` int(0) NOT NULL COMMENT 'Hourly Price',
  `dispense_region` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Dispense Region',
  `dispense_location` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Dispense Location',
  `dispense_time` datetime NULL DEFAULT NULL COMMENT 'Dispense Time',
  `car_condition` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Status',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1702023819790 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Car Dispense' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_dispense
-- ----------------------------
INSERT INTO `car_dispense` VALUES (41, '2021-05-19 12:20:26', 'BYD Qin', 'Sedan', 'BYD', 'A000000', 'White', '4', 100, 'Miyun District', '4 Jingshan Front St, Dongcheng, Beijing', '2024-01-21 12:20:26', 'In Use');
INSERT INTO `car_dispense` VALUES (42, '2021-05-19 12:20:26', 'BYD Qin', 'Sedan', 'BYD', 'A000001', 'White', '6', 200, 'Miyun District', '4 Jingshan Front St, Dongcheng, Beijing', '2024-01-21 12:20:26', 'In Use');
INSERT INTO `car_dispense` VALUES (43, '2021-05-19 12:20:26', 'BYD Qin', 'Sedan', 'BYD', 'A000002', 'Black', '4', 300, 'Miyun District', '4 Jingshan Front St, Dongcheng, Beijing', '2024-01-21 12:20:26', 'Available');
INSERT INTO `car_dispense` VALUES (44, '2021-05-19 12:20:26', 'XPeng G6', 'SUV', 'XPeng', 'A000003', 'Black', '4', 200, 'Xicheng District', 'East Changan Ave, Dongcheng, Beijing', '2024-01-21 12:20:26', 'Available');
INSERT INTO `car_dispense` VALUES (45, '2021-05-19 12:20:26', 'XPeng G6', 'SUV', 'XPeng', 'A000004', 'Blue', '6', 500, 'Xicheng District', 'East Changan Ave, Dongcheng, Beijing', '2024-01-21 12:20:26', 'Available');
INSERT INTO `car_dispense` VALUES (46, '2021-05-19 12:20:26', 'Wuling Hongguang', 'Sedan', 'Wuling', 'A000005', 'Blue', '4', 600, 'Xicheng District', 'East Changan Ave, Dongcheng, Beijing', '2024-01-21 12:20:26', 'Available');
INSERT INTO `car_dispense` VALUES (1702023819789, '2023-12-08 16:23:39', 'Wuling Hongguang', 'Sedan', 'Wuling', 'A000006', 'Blue', '6', 200, 'Xicheng District', 'East Changan Ave, Dongcheng, Beijing', '2024-01-21 16:23:19', 'Available');

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Create Time',
  `car_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Name',
  `car_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Type',
  `brand` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Brand',
  `body_color` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Body Color',
  `seating_capacity` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Seating Capacity',
  `license_plate_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'License Plate Number',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Additional Info',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Car Info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_info
-- ----------------------------
INSERT INTO `car_info` VALUES (31, '2024-01-21 12:20:26', 'BYD Qin', 'Sedan', 'BYD', 'Blue', '4', 'A000000', '<p><img src=\"http://localhost:9341/upload/1702914616282.jpg\"></p>');
INSERT INTO `car_info` VALUES (32, '2024-01-21 12:20:26', 'BYD Qin', 'Sedan', 'BYD', 'Blue', '4', 'A000001', '<p><img src=\"http://localhost:9341/upload/1702914825785.jpg\"></p>');
INSERT INTO `car_info` VALUES (33, '2024-01-21 12:20:26', 'Wuling Hongguang', 'Sedan', 'Wuling', 'Gray', '4', 'A000002', '<p><img src=\"http://localhost:9341/upload/1702914945583.jpg\"></p>');
INSERT INTO `car_info` VALUES (34, '2024-01-21 12:20:26', 'Wuling Hongguang', 'Sedan', 'Wuling', 'Gray', '4', 'A000003', '<p><img src=\"http://localhost:9341/upload/1702914945583.jpg\"></p>');
INSERT INTO `car_info` VALUES (35, '2024-01-21 12:20:26', 'XPeng G6', 'SUV', 'XPeng', 'White', '5', 'A000004', '<p><img src=\"http://localhost:9341/upload/1702914945583.jpg\"></p>');
INSERT INTO `car_info` VALUES (36, '2024-01-21 12:20:26', 'XPeng G6', 'SUV', 'XPeng', 'White', '5', 'A000005', '<p><img src=\"http://localhost:9341/upload/1702914945583.jpg\"></p>');

-- ----------------------------
-- Table structure for car_return
-- ----------------------------
DROP TABLE IF EXISTS `car_return`;
CREATE TABLE `car_return`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Create Time',
  `car_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Name',
  `car_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Type',
  `brand` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Brand',
  `license_plate_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'License Plate Number',
  `return_time` datetime NULL DEFAULT NULL COMMENT 'Return Time',
  `return_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Return Content',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Username',
  `real_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Real Name',
  `mobile_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Mobile Phone',
  `is_reviewed` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'No' COMMENT 'Is Reviewed',
  `review_response` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Review Response',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1704444206784 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Car Return' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_return
-- ----------------------------
INSERT INTO `car_return` VALUES (1702960112086, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000002', '2024-01-21 16:29:29', '<p><img src=\"http://localhost:9341/upload/1702960110044.jpg\"></p>', 'user', 'user', '13823888886', 'Yes', '');
INSERT INTO `car_return` VALUES (1704444206783, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000002', '2024-01-21 16:29:29', '', 'user', 'user', '13823888886', 'No', '');

-- ----------------------------
-- Table structure for car_storage
-- ----------------------------
DROP TABLE IF EXISTS `car_storage`;
CREATE TABLE `car_storage`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Create Time',
  `car_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Name',
  `car_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Type',
  `brand` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Brand',
  `license_plate_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'License Plate Number',
  `recovery_location` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Recovery Location',
  `recovery_time` datetime NULL DEFAULT NULL COMMENT 'Recovery Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1701966825708 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Car Storage' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_storage
-- ----------------------------
INSERT INTO `car_storage` VALUES (51, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000000', '4 Jingshan Front St, Dongcheng, Beijing', '2024-01-21 16:29:29');
INSERT INTO `car_storage` VALUES (52, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000001', '4 Jingshan Front St, Dongcheng, Beijing', '2024-01-21 16:29:29');
INSERT INTO `car_storage` VALUES (53, '2024-01-21 16:29:29', 'XPeng G6', 'SUV', 'XPeng', 'A000002', '4 Jingshan Front St, Dongcheng, Beijing', '2024-01-21 16:29:29');
INSERT INTO `car_storage` VALUES (54, '2024-01-21 16:29:29', 'XPeng G6', 'SUV', 'XPeng', 'A000003', '4 Jingshan Front St, Dongcheng, Beijing', '2024-01-21 16:29:29');
INSERT INTO `car_storage` VALUES (55, '2024-01-21 16:29:29', 'Wuling Hongguang', 'Sedan', 'Wuling', 'A000004', 'East Changan Ave, Dongcheng, Beijing', '2024-01-21 16:29:29');
INSERT INTO `car_storage` VALUES (1701966825707, '2024-01-21 16:29:29', 'Wuling Hongguang', 'Sedan', 'Wuling', 'A000005', 'East Changan Ave, Dongcheng, Beijing', '2024-01-21 16:29:29');

-- ----------------------------
-- Table structure for dispense_area
-- ----------------------------
DROP TABLE IF EXISTS `dispense_area`;
CREATE TABLE `dispense_area`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Create Time',
  `area` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Area',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Dispense Area' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dispense_area
-- ----------------------------
INSERT INTO `dispense_area` VALUES (21, '2024-01-21 16:29:29', 'Miyun District');
INSERT INTO `dispense_area` VALUES (22, '2024-01-21 16:29:29', 'Yanqing District');
INSERT INTO `dispense_area` VALUES (23, '2024-01-21 16:29:29', 'Chaoyang District');
INSERT INTO `dispense_area` VALUES (24, '2024-01-21 16:29:29', 'Xicheng District');
INSERT INTO `dispense_area` VALUES (25, '2024-01-21 16:29:29', 'Fengtai District');
INSERT INTO `dispense_area` VALUES (26, '2024-01-21 16:29:29', 'Mentougou District');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `user_id` bigint(0) NOT NULL COMMENT 'User ID',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Username',
  `table_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Table Name',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Role',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Token',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Add Time',
  `expiration_time` datetime NOT NULL COMMENT 'Expiration Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Token Table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 1, 'abo', 'users', 'Admin', 'riisuqauxycnyfghacqp2yfqm4kkhyo5', '2024-01-21 16:29:29', '2024-01-21 17:04:51');
INSERT INTO `token` VALUES (2, 11, '1', 'yonghu', 'User', 'ul3cay5k8gtdqoqjkhomvxo8qvjpplak', '2024-01-21 16:29:29', '2024-01-21 16:29:29');
INSERT INTO `token` VALUES (3, 1701967436381, 'aabb', 'user', 'User', 'x3pk8u3frwikps5a6dmq56czsk41qcjh', '2024-01-21 16:29:29', '2024-01-21 16:29:29');
INSERT INTO `token` VALUES (4, 2, 'zzz', 'admin', 'Admin', 'xpe4wicivy31odgpx67ztpwv6membsn6', '2024-01-21 16:29:29', '2024-01-21 16:29:29');
INSERT INTO `token` VALUES (5, 16, 'user', 'user', 'User', '2e0539k5u73nwgwnmtngz1mgwvxfengc', '2024-01-21 16:29:29', '2024-01-21 17:05:21');

-- ----------------------------
-- Table structure for use_order
-- ----------------------------
DROP TABLE IF EXISTS `use_order`;
CREATE TABLE `use_order`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Create Time',
  `car_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Name',
  `car_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Car Type',
  `brand` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Brand',
  `license_plate_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'License Plate Number',
  `hourly_price` int(0) NULL DEFAULT NULL COMMENT 'Hourly Price',
  `use_duration` int(0) NOT NULL COMMENT 'Use Duration',
  `total_price` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Total Price',
  `use_time` datetime NULL DEFAULT NULL COMMENT 'Use Time',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Username',
  `real_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Real Name',
  `mobile_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Mobile Phone',
  `is_reviewed` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'No' COMMENT 'Is Reviewed',
  `review_response` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Review Response',
  `is_paid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Unpaid' COMMENT 'Is Paid',
  `is_return` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'No' COMMENT 'Is Returned',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1704444147632 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Use Order' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of use_order
-- ----------------------------
INSERT INTO `use_order` VALUES (61, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000000', 200, 3, '600', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Unpaid', 'Yes');
INSERT INTO `use_order` VALUES (62, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000001', 300, 2, '600', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Unpaid', 'Yes');
INSERT INTO `use_order` VALUES (63, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000001', 200, 3, '600', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Unpaid', 'Yes');
INSERT INTO `use_order` VALUES (64, '2024-01-21 16:29:29', 'XPeng G6', 'SUV', 'XPeng', 'A000002', 400, 4, '1200', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Unpaid', 'Yes');
INSERT INTO `use_order` VALUES (65, '2024-01-21 16:29:29', 'XPeng G6', 'SUV', 'XPeng', 'A000002', 500, 5, '2500', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Unpaid', 'Yes');
INSERT INTO `use_order` VALUES (1701967537268, '2024-01-21 16:29:29', 'XPeng G6', 'SUV', 'XPeng', 'A000003', 600, 12, '7200', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Paid', 'Yes');
INSERT INTO `use_order` VALUES (1702023863492, '2024-01-21 16:29:29', 'Wuling Hongguang', 'Sedan', 'Wuling', 'A000004', 400, 12, '4800', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '123321', 'Paid', 'Yes');
INSERT INTO `use_order` VALUES (1702489472343, '2024-01-21 16:29:29', 'Wuling Hongguang', 'Sedan', 'Wuling', 'A000004', 200, 3, '1800', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Paid', 'Yes');
INSERT INTO `use_order` VALUES (1702524104758, '2024-01-21 16:29:29', 'Wuling Hongguang', 'Sedan', 'Wuling', 'A000005', 200, 20, '8000', '2024-01-21 16:29:29', 'user', 'user', '13113414411', 'Yes', '', 'Paid', 'Yes');
INSERT INTO `use_order` VALUES (1702959808298, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000002', 300, 5, '1500', '2024-01-21 16:29:29', 'user', 'user', '13823888886', 'No', '', 'Paid', 'Yes');
INSERT INTO `use_order` VALUES (1704444147631, '2024-01-21 16:29:29', 'BYD Qin', 'Sedan', 'BYD', 'A000002', 300, 3, '900', '2024-01-21 16:29:29', 'user', 'user', '13823888886', 'No', '', 'Paid', 'Yes');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `add_time` timestamp NOT NULL DEFAULT NOW() COMMENT 'Create Time',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Username',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Password',
  `real_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Real Name',
  `gender` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Gender',
  `birthday` date NULL DEFAULT NULL COMMENT 'Birthday',
  `identification_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ID Number',
  `mobile_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Mobile Phone',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Photo',
  `is_reviewed` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'No' COMMENT 'Is Reviewed',
  `review_response` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Review Response',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1701967436382 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'User' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (16, '2024-01-21 16:29:29', 'user', '123456', 'John Smith', 'Male', '2004-06-23', '110552200207113333', '13333333333', '<p><img src=\"http://localhost:9341/upload/1702913828994.jpg\"><img src=\"http://localhost:9341/upload/1702913832627.jpg\"></p>', 'Yes', NULL);

SET FOREIGN_KEY_CHECKS = 1;
