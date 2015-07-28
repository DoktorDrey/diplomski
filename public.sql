/*
Navicat PGSQL Data Transfer

Source Server         : AGstaging
Source Server Version : 90305
Source Host           : 127.0.0.1:5432
Source Database       : golden_scratch
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90305
File Encoding         : 65001

Date: 2015-05-29 12:38:44
*/


-- ----------------------------
-- Sequence structure for gs_games_id_seq
-- ----------------------------
DROP SEQUENCE "public"."gs_games_id_seq";
CREATE SEQUENCE "public"."gs_games_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;
SELECT setval('"public"."gs_games_id_seq"', 4, true);

-- ----------------------------
-- Sequence structure for gs_montly_leaders_id_seq
-- ----------------------------
DROP SEQUENCE "public"."gs_montly_leaders_id_seq";
CREATE SEQUENCE "public"."gs_montly_leaders_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for gs_users_id_seq
-- ----------------------------
DROP SEQUENCE "public"."gs_users_id_seq";
CREATE SEQUENCE "public"."gs_users_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;

-- ----------------------------
-- Sequence structure for locale_users_id_seq
-- ----------------------------
DROP SEQUENCE "public"."locale_users_id_seq";
CREATE SEQUENCE "public"."locale_users_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."locale_users_id_seq"', 1, true);

-- ----------------------------
-- Table structure for gs_current_jackpot
-- ----------------------------
DROP TABLE IF EXISTS "public"."gs_current_jackpot";
CREATE TABLE "public"."gs_current_jackpot" (
"jackpot" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of gs_current_jackpot
-- ----------------------------

-- ----------------------------
-- Table structure for gs_current_month_points
-- ----------------------------
DROP TABLE IF EXISTS "public"."gs_current_month_points";
CREATE TABLE "public"."gs_current_month_points" (
"player_id" int4 NOT NULL,
"points" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of gs_current_month_points
-- ----------------------------
INSERT INTO "public"."gs_current_month_points" VALUES ('1', '184');

-- ----------------------------
-- Table structure for gs_games
-- ----------------------------
DROP TABLE IF EXISTS "public"."gs_games";
CREATE TABLE "public"."gs_games" (
"id" int4 DEFAULT nextval('gs_games_id_seq'::regclass) NOT NULL,
"name" varchar(30) COLLATE "default",
"image" varchar(30) COLLATE "default",
"game_id" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of gs_games
-- ----------------------------
INSERT INTO "public"."gs_games" VALUES ('1', 'Golden Scratchie', 'scratchie.jpg', 'scratchie');
INSERT INTO "public"."gs_games" VALUES ('2', 'Golden Wealth', 'goldenwealth.jpg', 'golden');
INSERT INTO "public"."gs_games" VALUES ('3', 'Egyptian Treasures', 'egyptiantreasures.jpg', 'egyptian');
INSERT INTO "public"."gs_games" VALUES ('4', 'Summer Holiday', 'summerholiday.jpg', 'summer');

-- ----------------------------
-- Table structure for gs_montly_leaders
-- ----------------------------
DROP TABLE IF EXISTS "public"."gs_montly_leaders";
CREATE TABLE "public"."gs_montly_leaders" (
"id" int4 DEFAULT nextval('gs_montly_leaders_id_seq'::regclass) NOT NULL,
"player_id" int4,
"place" int4,
"points" int4,
"for_month" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of gs_montly_leaders
-- ----------------------------

-- ----------------------------
-- Table structure for gs_users
-- ----------------------------
DROP TABLE IF EXISTS "public"."gs_users";
CREATE TABLE "public"."gs_users" (
"id" int4 DEFAULT nextval('gs_users_id_seq'::regclass) NOT NULL,
"username" varchar(50) COLLATE "default",
"password" varchar(150) COLLATE "default",
"firstname" varchar(20) COLLATE "default",
"lastname" varchar(20) COLLATE "default",
"email" varchar(50) COLLATE "default",
"phone" varchar(20) COLLATE "default",
"doj" timestamp(6),
"ipaddress" varchar(20) COLLATE "default",
"zipcode" varchar(15) COLLATE "default",
"address" varchar(30) COLLATE "default",
"address2" varchar(30) COLLATE "default",
"city" varchar(20) COLLATE "default",
"state" varchar(20) COLLATE "default",
"country" varchar(20) COLLATE "default",
"balance" int4 DEFAULT 0
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of gs_users
-- ----------------------------
INSERT INTO "public"."gs_users" VALUES ('1', 'andrija', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'andrija', 'ilic', 'johnson@iamcorp.eu', '1231231312', '2015-05-18 11:28:58', '101.165.83.160', '4000', 'street 1', 'street 2', 'mycit', 'qld', '13', '209');

-- ----------------------------
-- Table structure for locale_users
-- ----------------------------
DROP TABLE IF EXISTS "public"."locale_users";
CREATE TABLE "public"."locale_users" (
"id" int4 DEFAULT nextval('locale_users_id_seq'::regclass) NOT NULL,
"user_name" varchar(30) COLLATE "default",
"user_password" varchar(150) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of locale_users
-- ----------------------------
INSERT INTO "public"."locale_users" VALUES ('1', 'admin', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."gs_games_id_seq" OWNED BY "gs_games"."id";
ALTER SEQUENCE "public"."gs_montly_leaders_id_seq" OWNED BY "gs_montly_leaders"."id";
ALTER SEQUENCE "public"."gs_users_id_seq" OWNED BY "gs_users"."id";
ALTER SEQUENCE "public"."locale_users_id_seq" OWNED BY "locale_users"."id";

-- ----------------------------
-- Primary Key structure for table gs_current_jackpot
-- ----------------------------
ALTER TABLE "public"."gs_current_jackpot" ADD PRIMARY KEY ("jackpot");

-- ----------------------------
-- Primary Key structure for table gs_current_month_points
-- ----------------------------
ALTER TABLE "public"."gs_current_month_points" ADD PRIMARY KEY ("player_id");

-- ----------------------------
-- Primary Key structure for table gs_games
-- ----------------------------
ALTER TABLE "public"."gs_games" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table gs_montly_leaders
-- ----------------------------
ALTER TABLE "public"."gs_montly_leaders" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table gs_users
-- ----------------------------
ALTER TABLE "public"."gs_users" ADD UNIQUE ("email");
ALTER TABLE "public"."gs_users" ADD UNIQUE ("username");

-- ----------------------------
-- Primary Key structure for table gs_users
-- ----------------------------
ALTER TABLE "public"."gs_users" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table locale_users
-- ----------------------------
ALTER TABLE "public"."locale_users" ADD PRIMARY KEY ("id");
