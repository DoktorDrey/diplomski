/*
Navicat MySQL Data Transfer

Source Server         : LokalMysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : katedra

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-08-19 23:00:02
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
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aktivnost
-- ----------------------------
INSERT INTO `aktivnost` VALUES ('1', '1', '1', '2015-08-06 13:20:33', '0.50', '1');
INSERT INTO `aktivnost` VALUES ('2', '2', '1', '2015-08-13 13:20:48', '0.50', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predavac
-- ----------------------------
INSERT INTO `predavac` VALUES ('1', 'Marko', 'Smiljkovic', '1', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO `predavac` VALUES ('2', 'Miloje', 'Milojevic', '1', 'miloje', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO `predavac` VALUES ('3', 'Milenko', 'Petrovic', '2', 'milenko', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO `predavac` VALUES ('4', 'Stefan', 'Djordjevic', '2', 'stefan', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

-- ----------------------------
-- Table structure for predmet
-- ----------------------------
DROP TABLE IF EXISTS `predmet`;
CREATE TABLE `predmet` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predmet
-- ----------------------------
INSERT INTO `predmet` VALUES ('1', 'Projektovanje softvera');
INSERT INTO `predmet` VALUES ('2', 'Softverski paterni');
INSERT INTO `predmet` VALUES ('3', 'Matematika 1');
INSERT INTO `predmet` VALUES ('4', 'Matematika 2');
INSERT INTO `predmet` VALUES ('5', 'Matematika 3');

-- ----------------------------
-- Table structure for predmet_predavac
-- ----------------------------
DROP TABLE IF EXISTS `predmet_predavac`;
CREATE TABLE `predmet_predavac` (
  `predmet` int(255) NOT NULL,
  `predavac` int(255) NOT NULL,
  PRIMARY KEY (`predmet`,`predavac`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of predmet_predavac
-- ----------------------------
INSERT INTO `predmet_predavac` VALUES ('1', '1');
INSERT INTO `predmet_predavac` VALUES ('1', '3');
INSERT INTO `predmet_predavac` VALUES ('2', '1');
INSERT INTO `predmet_predavac` VALUES ('2', '3');
INSERT INTO `predmet_predavac` VALUES ('3', '2');
INSERT INTO `predmet_predavac` VALUES ('3', '4');
INSERT INTO `predmet_predavac` VALUES ('4', '2');
INSERT INTO `predmet_predavac` VALUES ('4', '4');
INSERT INTO `predmet_predavac` VALUES ('5', '2');
INSERT INTO `predmet_predavac` VALUES ('5', '4');

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `godina` int(11) NOT NULL,
  `id_predmeta` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of program
-- ----------------------------
INSERT INTO `program` VALUES ('1', '2015', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'andrija_ilic1987@yahoo.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '123/07', 'Andrija', 'Ilic', '1', null);
INSERT INTO `student` VALUES ('2', 'andrija_ilic1987+5@yahoo.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '157/06', 'Petar', 'Petrovic', '1', null);
INSERT INTO `student` VALUES ('3', 'andrija_ilic1987+2@yahoo.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '133/23', 'Marko', 'Markovic', '1', null);
INSERT INTO `student` VALUES ('4', 'andrija_ilic1987+3@yahoo.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '122/04', 'Pavle', 'Pavlovic', '1', 'pdal3ao41mop41t089morkj8f6');
INSERT INTO `student` VALUES ('5', 'andrija_ilic1987+4@yahoo.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '144/07', 'Kosta', 'Kostic', '1', 's8ot0e7q4jm0cdmv1qfd1ac5ok');
INSERT INTO `student` VALUES ('6', 'andrija.ilic87@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '155/09', 'Laza', 'Lazic', '1', 'bh4bdgefqekeunsjrn6suoqctt');
INSERT INTO `student` VALUES ('7', 'andrija.ilic87+1@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '117/08', 'Ilija', 'Ilic', '1', 'j5qqsb9v96l9bl1nmqqrfr8h0c');
INSERT INTO `student` VALUES ('8', 'andrija.ilic87+2@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1/08', 'Srdjan', 'Popovic', '1', null);
INSERT INTO `student` VALUES ('9', 'andrija.ilic87+3@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2/08', 'Milos', 'Milosevic', '1', null);
INSERT INTO `student` VALUES ('10', 'andrija.ilic87+4@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '3/08', 'Marija', 'Mitrovic', '1', null);
INSERT INTO `student` VALUES ('11', 'andrija.ilic87+5@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '4/08', 'Jelena', 'Kostic', '1', null);
INSERT INTO `student` VALUES ('12', 'andrija.ilic87+6@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '5/08', 'Slobodan', 'Mitic', '1', null);
INSERT INTO `student` VALUES ('13', 'andrija.ilic87+7@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '6/08', 'Paja', 'Kovac', '1', null);
INSERT INTO `student` VALUES ('14', 'andrija.ilic87+8@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '7/08', 'Marko', 'Mitrovic', '1', null);
INSERT INTO `student` VALUES ('15', 'andrija.ilic87+9@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '8/08', 'Miki', 'Lazic', '1', null);
INSERT INTO `student` VALUES ('16', 'andrija.ilic87+10@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '12/08', 'Sasa', 'Stanic', '1', null);

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
INSERT INTO `student_aktivnost_ass` VALUES ('1', '2', null);

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
