/*
Navicat MySQL Data Transfer

Source Server         : mysqlLokal
Source Server Version : 50532
Source Host           : localhost:3306
Source Database       : katedra

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2015-08-10 15:41:36
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
  `datum` datetime DEFAULT NULL,
  `vrednost` float(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aktivnost
-- ----------------------------
INSERT INTO `aktivnost` VALUES ('1', '1', '1', '2015-08-06 13:20:33', '0.50');
INSERT INTO `aktivnost` VALUES ('2', '2', '1', '2015-08-13 13:20:48', '0.50');

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
INSERT INTO `predavac` VALUES ('1', 'John', 'Smit', '1', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

-- ----------------------------
-- Table structure for predmet
-- ----------------------------
DROP TABLE IF EXISTS `predmet`;
CREATE TABLE `predmet` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`)
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
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `broj_indeksa` (`broj_indeksa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'andrija_ilic1987+1@yahoo.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '123/07', 'Student', 'Student', '0', null);
INSERT INTO `student` VALUES ('2', 'andrija_ilic1987@yahoo.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '157/06', 'andrija', 'ilic', '1', null);
INSERT INTO `student` VALUES ('3', 'andrija_ilic1987+2@yahoo.com', '937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244', '133/23', 'Test', 'Testic', '0', null);
INSERT INTO `student` VALUES ('4', 'andrija_ilic1987+3@yahoo.com', '937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244', '122/04', 'test', 'test', '0', 'pdal3ao41mop41t089morkj8f6');
INSERT INTO `student` VALUES ('5', 'andrija_ilic1987+4@yahoo.com', '6fec2a9601d5b3581c94f2150fc07fa3d6e45808079428354b868e412b76e6bb', '144/07', 'andrija', 'test', '1', 's8ot0e7q4jm0cdmv1qfd1ac5ok');
INSERT INTO `student` VALUES ('6', 'andrija.ilic87@gmail.com', 'd5caf17539ee62962606429f421d196349b75e492af00d0f6d4ce0fd878c2841', '155/09', 'Andrija', 'Ilic', '0', 'bh4bdgefqekeunsjrn6suoqctt');

-- ----------------------------
-- Table structure for student_aktivnost_ass
-- ----------------------------
DROP TABLE IF EXISTS `student_aktivnost_ass`;
CREATE TABLE `student_aktivnost_ass` (
  `student` int(11) NOT NULL,
  `aktivnost` int(11) NOT NULL,
  `broj_poena` float(5,2) DEFAULT NULL,
  PRIMARY KEY (`student`,`aktivnost`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student_aktivnost_ass
-- ----------------------------
INSERT INTO `student_aktivnost_ass` VALUES ('1', '1', '100.00');

-- ----------------------------
-- Table structure for student_predmet_ass
-- ----------------------------
DROP TABLE IF EXISTS `student_predmet_ass`;
CREATE TABLE `student_predmet_ass` (
  `student_id` int(11) NOT NULL,
  `program_id` int(11) NOT NULL,
  `konacna_ocena` int(11) DEFAULT NULL,
  `broj_bodova` float(4,2) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student_predmet_ass
-- ----------------------------
INSERT INTO `student_predmet_ass` VALUES ('1', '1', null, null);
INSERT INTO `student_predmet_ass` VALUES ('2', '1', null, null);

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
  `pozicija` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tip_predavaca
-- ----------------------------
INSERT INTO `tip_predavaca` VALUES ('1', 'profesor');
INSERT INTO `tip_predavaca` VALUES ('2', 'asistent');
