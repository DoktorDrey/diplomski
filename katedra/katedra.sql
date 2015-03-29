/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : katedra

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2014-08-02 14:26:25
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predavac
-- ----------------------------

-- ----------------------------
-- Table structure for predmet
-- ----------------------------
DROP TABLE IF EXISTS `predmet`;
CREATE TABLE `predmet` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predmet
-- ----------------------------

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `godina` int(11) NOT NULL,
  `id_predmeta` int(11) NOT NULL,
  PRIMARY KEY (`id`,`godina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of program
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `broj_indeksa` varchar(20) NOT NULL,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  PRIMARY KEY (`broj_indeksa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for tip_aktivnosti
-- ----------------------------
DROP TABLE IF EXISTS `tip_aktivnosti`;
CREATE TABLE `tip_aktivnosti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_aktivnosti` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tip_aktivnosti
-- ----------------------------

-- ----------------------------
-- Table structure for tip_predavaca
-- ----------------------------
DROP TABLE IF EXISTS `tip_predavaca`;
CREATE TABLE `tip_predavaca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_titule` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tip_predavaca
-- ----------------------------
