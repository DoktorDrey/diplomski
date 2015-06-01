/*
Navicat MySQL Data Transfer

Source Server         : LokalMysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : katedra

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-06-02 00:44:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aktivnost
-- ----------------------------
DROP TABLE IF EXISTS `aktivnost`;
CREATE TABLE `aktivnost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tip_aktivnosti` int(11) NOT NULL,
  `program` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aktivnost
-- ----------------------------

-- ----------------------------
-- Table structure for predavac
-- ----------------------------
DROP TABLE IF EXISTS `predavac`;
CREATE TABLE `predavac` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `titula` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predavac
-- ----------------------------
INSERT INTO `predavac` VALUES ('1', 'John', 'Smit', '1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

-- ----------------------------
-- Table structure for predmet
-- ----------------------------
DROP TABLE IF EXISTS `predmet`;
CREATE TABLE `predmet` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predmet
-- ----------------------------
INSERT INTO `predmet` VALUES ('1', 'Projektovanje softvera');
INSERT INTO `predmet` VALUES ('2', 'Softverski paterni');

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `godina` int(11) NOT NULL,
  `id_predmeta` int(11) NOT NULL,
  `max_broj_poena` int(11) DEFAULT NULL,
  `bp_predavanja` int(11) DEFAULT NULL,
  `bp_vezbe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`godina`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of program
-- ----------------------------
INSERT INTO `program` VALUES ('1', '2015', '1', '100', '100', '100');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `broj_indeksa` varchar(30) DEFAULT NULL,
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  `activated` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', null, null, '123/07', 'Student', 'Student', '0');
INSERT INTO `student` VALUES ('2', 'andrija_ilic1987@yahoo.com', '937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244', '157/06', 'andrija', 'ilic', '0');

-- ----------------------------
-- Table structure for tip_aktivnosti
-- ----------------------------
DROP TABLE IF EXISTS `tip_aktivnosti`;
CREATE TABLE `tip_aktivnosti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_aktivnosti` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tip_aktivnosti
-- ----------------------------
INSERT INTO `tip_aktivnosti` VALUES ('1', 'pismeni');
INSERT INTO `tip_aktivnosti` VALUES ('2', 'usmeni');
INSERT INTO `tip_aktivnosti` VALUES ('3', 'kolokvijum');
INSERT INTO `tip_aktivnosti` VALUES ('4', 'projekat');
INSERT INTO `tip_aktivnosti` VALUES ('5', 'aktivnost');

-- ----------------------------
-- Table structure for tip_predavaca
-- ----------------------------
DROP TABLE IF EXISTS `tip_predavaca`;
CREATE TABLE `tip_predavaca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_titule` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tip_predavaca
-- ----------------------------
INSERT INTO `tip_predavaca` VALUES ('1', 'mr');
INSERT INTO `tip_predavaca` VALUES ('2', 'dr');
