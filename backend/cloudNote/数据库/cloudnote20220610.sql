/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.26 : Database - cloud_note
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloud_note` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cloud_note`;

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `value` varchar(100) DEFAULT NULL COMMENT '值',
  `head` bigint DEFAULT '0' COMMENT '所属',
  `deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  `userId` bigint DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb3;

/*Data for the table `categories` */

insert  into `categories`(`id`,`description`,`value`,`head`,`deleted`,`userId`) values (51,NULL,'大学笔记',0,0,10),(52,NULL,'语言笔记',0,1,10),(54,NULL,'大二',51,0,10),(55,NULL,'大三',51,0,10),(56,NULL,'雅思',52,1,10),(57,NULL,'写作',56,1,10),(58,NULL,'小作文',57,1,10),(59,NULL,'md',0,1,10),(66,NULL,'测试',51,0,10),(67,NULL,'技术学习',0,0,10),(68,NULL,'前端开发',67,0,10),(69,NULL,'后端开发',67,0,10),(70,NULL,'大二上学期',54,1,10),(71,NULL,'算法课',70,1,10),(72,NULL,'数据库',70,1,10);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shareId` bigint DEFAULT NULL COMMENT '分享id',
  `userId` bigint DEFAULT NULL COMMENT '用户id',
  `description` varchar(200) DEFAULT NULL COMMENT '评论',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

/*Data for the table `comment` */

insert  into `comment`(`id`,`shareId`,`userId`,`description`,`create_time`) values (1,58,10,'1111','2022-05-25 05:29:37'),(2,58,10,'111333331','2022-05-25 05:29:40'),(3,58,10,'111333331','2022-05-25 05:29:41'),(4,56,10,'111333331','2022-05-25 05:32:56');

/*Table structure for table `content` */

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `mode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '1.富文本2.markdown',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `userId` bigint DEFAULT NULL COMMENT '用户ID',
  `deleted` bigint DEFAULT '0' COMMENT '逻辑删除',
  `version` bigint DEFAULT '1' COMMENT '版本',
  `star` bigint DEFAULT '0' COMMENT '是否收藏：0否1是',
  `categoriesId` bigint DEFAULT '1' COMMENT '分类ID',
  `type` varchar(50) DEFAULT NULL COMMENT '类型：template模板，note笔记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb3;

/*Data for the table `content` */

insert  into `content`(`id`,`title`,`mode`,`content`,`create_time`,`update_time`,`userId`,`deleted`,`version`,`star`,`categoriesId`,`type`) values (54,'进程管理','1','QmYfwVLs96J8iacKoXZbZhYHHPgybQrmLyGqDSbne6cL7c','2022-05-24 05:15:34','2022-06-09 13:37:30',10,0,7,0,54,'note'),(55,'RPC','1','QmexucrphREzuJNcoLjt3jab2bosJp81wgaHLqpXXN2MW4','2022-05-24 05:18:14','2022-05-25 02:09:38',10,1,2,1,54,'note'),(56,'计算理论课后习题','1','Qmda9rE47zpKUpzKqrfiu95HGXLSkUoN6q8J9MkCPKZNex','2022-05-24 05:19:14','2022-06-08 05:46:44',10,0,5,1,55,'note'),(57,'计算机结构','1','QmaYuXMaSLnGMs2T3qMbruRSRtiDnwX5mF4EY2PhN2dbay','2022-05-24 05:21:29','2022-06-09 13:34:30',10,0,3,0,54,'note'),(58,'线图','1','QmWzBmCbj9dY6kwTCyq3C61dd32xqh7XiyNDCnVuM32rJS','2022-05-24 05:23:57','2022-05-24 05:23:57',10,1,1,0,58,'note'),(59,'饼图','1','QmVxpdQ7LQNWiPSh7gcLWHsKNc9EQgayWNDn19kdDMm6kq','2022-05-24 05:24:54','2022-05-24 05:24:54',10,1,1,0,58,'note'),(60,'多组图','1','QmRwrPnrPpndhip1sNwEKb3W4CmQF1r2cPKtyY68gQssBr','2022-05-24 05:25:17','2022-05-24 05:25:17',10,1,1,0,58,'note'),(61,'学习计划模板','1','QmY9PGRuPiJXpqQ1RVQDmiUgqsB3AKXcPEtPXmD9c3MvTV','2022-05-24 13:30:09','2022-05-24 13:30:13',0,0,1,0,0,'template'),(62,'文章模板','1','QmStcMG1rVdrUa1VXb78smsSfRYjffCHgjtG52H1SSww2P','2022-05-24 13:32:00','2022-06-07 16:23:33',0,0,1,0,0,'template'),(63,'问题记录模板','1','QmWG4i1ktdPVYGSWveA7wzQQ5a8hJG1rLsbaMVyFhYpy4T','2022-05-24 13:34:06','2022-06-07 16:23:36',0,0,1,0,0,'template'),(67,'5.33','1','QmZz1uVGfwBP6CrBUPxhvp6ECkB9psEXkRbpD1M655mCQU','2022-05-25 02:36:28','2022-06-07 16:12:21',10,0,5,0,60,'note'),(69,'test','2','QmX59huWZa2PYBE3ffa7L39sSeQRxDktSLYJRUaRLNZ1oF','2022-05-25 02:43:10','2022-06-03 08:47:54',10,0,2,1,0,'note'),(70,'11111111111.docx','1','QmWMAuwF9T9LZWCBK5J961UW5RroRRuRFnCAKPPs7CPJHV','2022-05-26 01:30:55','2022-05-26 01:30:55',10,0,1,0,45,'note'),(71,'我的markdown文件','2','QmP6bdMCKPGXdYQ9L6tMP9Tj3e53czAnX6QBwmFuKWdTmw','2022-05-26 02:41:49','2022-06-04 14:07:27',10,1,5,0,59,'note'),(72,'测试2221','1','QmR7sDnkJERiJLSVdKPVTVqjwc53mVF6XpmGa8zpaipCj7','2022-05-26 03:01:20','2022-05-28 12:59:36',10,1,7,1,54,'note'),(81,'第六届校园提案.docx','1','QmYrkkNjsduJJ9D2GQR9UkkiHBoLZN8wcWTGDP6gwYdLXd','2022-05-28 08:48:22','2022-05-28 08:48:22',10,1,1,0,59,'note'),(82,'第六届校园提案.docx','1','QmYrkkNjsduJJ9D2GQR9UkkiHBoLZN8wcWTGDP6gwYdLXd','2022-05-28 08:48:24','2022-05-28 08:48:24',10,1,1,0,59,'note'),(83,'关于举办第十一届“中国软件杯”大学生软件设计大赛的通知.pdf','1','QmNbkvro5kiFkqxfdqKqPuvHpBUt1FJHbNSYNgrwhG7csZ','2022-05-28 08:53:15','2022-06-03 08:46:55',10,0,1,0,0,'note'),(84,'关于举办第十一届“中国软件杯”大学生软件设计大赛的通知.pdf','1','QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-28 08:53:15','2022-05-28 08:53:15',10,0,1,0,1,'note'),(86,'Poorly_Written_Report_Sample.docx','1','QmVP4KfLwXhU75F7vuZTEKRAQkEhCi5HupkpDNYQhyYq6N','2022-05-28 08:57:05','2022-05-28 08:57:05',10,1,1,0,59,'note'),(88,'README.md','1','QmbFMke1KXqnYyBBWxB74N4c5SBnJMVAiMNRcGu6x1AwQH','2022-05-28 08:59:42','2022-05-28 08:59:42',10,0,1,0,1,'note'),(89,'Poorly_Written_Report_Sample.docx','1','QmVP4KfLwXhU75F7vuZTEKRAQkEhCi5HupkpDNYQhyYq6N','2022-05-28 09:00:21','2022-05-28 09:00:21',10,0,1,0,1,'note'),(91,'README.md','1','QmbFMke1KXqnYyBBWxB74N4c5SBnJMVAiMNRcGu6x1AwQH','2022-05-28 09:00:59','2022-05-28 09:00:59',10,1,1,0,59,'note'),(93,'IP Work Report_CoverPage_2021S2.docx','1','QmTZHJKyWxxLPVMopDpuR1RSxaYWtYMTFCr3JovxVhysrp','2022-05-28 09:01:09','2022-05-28 09:01:09',10,1,1,0,59,'note'),(94,'工业实习报告.docx','1','QmT4eBzvsmDH21pzYjmsrVKSFrdSYfLWSTsxTyiKZg4qR5','2022-05-28 09:04:01','2022-05-28 09:04:01',10,1,1,0,59,'note'),(97,'mdmdmdmdmdm','1','QmXLYJRW2UfASFfmv6BTw5PuQfAc9gicthSCbDBXergXn9','2022-05-28 14:09:29','2022-05-29 03:32:48',10,1,5,0,54,'note'),(98,'README.md','1','QmbFMke1KXqnYyBBWxB74N4c5SBnJMVAiMNRcGu6x1AwQH','2022-05-29 02:57:35','2022-05-29 02:57:35',10,1,1,0,59,'note'),(99,'README.md','1','QmbFMke1KXqnYyBBWxB74N4c5SBnJMVAiMNRcGu6x1AwQH','2022-05-29 02:58:13','2022-05-29 02:58:13',10,1,1,0,59,'note'),(100,'README.md','1','QmbFMke1KXqnYyBBWxB74N4c5SBnJMVAiMNRcGu6x1AwQH','2022-05-29 03:14:03','2022-05-29 03:14:03',10,1,1,0,59,'note'),(101,'README.md','1','QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 03:19:37','2022-05-29 03:19:37',10,1,1,0,59,'note'),(105,'34届韩奖汉译外.pdf','1','QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-29 03:43:34','2022-05-29 03:43:34',10,1,1,0,54,'note'),(106,'34届韩奖汉译外.pdf','1','QmTkpSkH3ExgogbVRqDfPafhLeUJJuzwWayE3H8om7JJ5s','2022-05-29 05:09:35','2022-06-04 14:55:04',10,1,4,0,54,'note'),(109,'README.md','2','QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 13:57:17','2022-05-29 13:57:17',10,1,1,0,51,'note'),(110,'Poorly_Written_Report_Sample.docx','1','QmVP4KfLwXhU75F7vuZTEKRAQkEhCi5HupkpDNYQhyYq6N','2022-05-29 13:57:52','2022-05-29 13:57:52',10,1,1,0,51,'note'),(111,'README.md','2','QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 14:02:20','2022-05-29 14:02:20',10,1,1,0,51,'note'),(112,'README.md','2','QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 14:04:18','2022-05-29 14:04:18',10,1,1,0,51,'note'),(113,'IP Work Report - Template_20220520.pdf','1','QmfXnEkevSE4K9oPTdVUN4tM1zXmZMJQ1yKWrWAFntC8a2','2022-05-29 14:19:14','2022-05-29 14:19:14',10,1,1,0,51,'note'),(122,'212121','1','QmS4KiPRGbMP7AVsXpju2jSw7Z94b2kFq71EEpij9KiBo1','2022-06-04 05:49:13','2022-06-04 05:49:13',10,0,1,0,1,'template'),(123,'嘻嘻','1','QmSiWvhewMGqz4pvVAdkoYiEHCGMP59atVXzccYygAyx7p','2022-06-04 05:54:52','2022-06-04 05:54:52',10,0,1,0,1,'template'),(124,'112121','1','QmUqFBjNr9eSTxRY5UoFpkoMsEqYVzy1fEPgLyjdZihz6a','2022-06-04 06:21:38','2022-06-04 06:21:38',10,1,1,0,56,'note'),(128,'老婆合集','2','QmYLWmJizJKzQgTvhHqFZRdH1MubhgNVXQQtuYPajFQCsS','2022-06-04 14:52:47','2022-06-04 14:59:10',10,1,5,1,54,'note'),(132,'工业实习报告.docx','1','QmT4eBzvsmDH21pzYjmsrVKSFrdSYfLWSTsxTyiKZg4qR5','2022-06-07 15:42:33','2022-06-07 15:42:33',10,1,1,0,51,'note'),(133,'README.md','2','QmNYU62jfpnLW2MBGAE62E6Zqg2ZxUuyA19TN9rgdpT6Nr','2022-06-07 15:49:07','2022-06-07 15:49:07',10,1,1,0,59,'note'),(134,'我的.md','2','QmUz4iTKfYb1DFGSCuTVdsPrgsYVGJAwr73neNa8Qr4v9n','2022-06-07 15:51:10','2022-06-07 15:51:10',10,1,1,0,59,'note'),(135,'我的word文档.docx','1','QmfZVEyZr96svQjGAdPRnm62wH8hL1cd6Twx2zhZDbyuWf','2022-06-07 15:51:33','2022-06-07 15:51:33',10,1,1,0,59,'note'),(136,'我的word文档.pdf','1','QmWntxF8nv9aGwCDSPv6aeRdQwrXft5ZicNJT1YorYBp6h','2022-06-07 15:51:37','2022-06-07 15:51:37',10,1,1,0,59,'note'),(137,'我的.md','2','QmZRNegr2t1vUYRkfCifuHbMCXUZj9NMq3GUmMWgXzB3XX','2022-06-07 15:54:33','2022-06-07 15:54:33',10,1,1,0,59,'note'),(138,'测试1','2','QmdYGuwcmbH3RUsjgiHGNughsKQmkjHppDnA4SsejX1Rb3','2022-06-07 16:16:53','2022-06-07 16:16:53',10,0,1,1,0,'note'),(139,'测试2','2','QmRo7Sroq2v8c3zU1K1PzEHsRch8djtrN6H28d9C4JEuZ8','2022-06-07 16:17:01','2022-06-09 13:58:44',10,0,2,1,66,'note'),(140,'测试3','1','QmYs2gi9Bos7DCtkE7UDKwyybLKYrEpJQjtRnLVwzD6nM5','2022-06-07 16:17:17','2022-06-09 13:53:57',10,0,5,0,66,'note'),(142,'我的.md','2','QmZRNegr2t1vUYRkfCifuHbMCXUZj9NMq3GUmMWgXzB3XX','2022-06-07 16:27:14','2022-06-07 16:27:14',10,0,1,0,66,'note'),(143,'我的word文档.docx','2','Qmc66PkrJ18UVruS8LrL1TdhUv53hPMVYpW74ywA4w5sKm','2022-06-07 16:27:20','2022-06-09 13:37:53',10,0,5,0,66,'note'),(144,'数据结构','2','QmewtY21Ufyqa166AWXrSC3Y3FMqKMhpRRPH2tqTNMUALE','2022-06-08 05:28:38','2022-06-08 05:28:38',10,1,1,0,71,'note'),(145,'mysql入门','2','QmewtY21Ufyqa166AWXrSC3Y3FMqKMhpRRPH2tqTNMUALE','2022-06-08 05:28:55','2022-06-08 05:28:55',10,1,1,0,72,'note'),(146,'测试','2','QmeMPWbCGPZascKZPXWAhxpq7kGfrcM4UnBFeeqbwGU24h','2022-06-08 05:49:49','2022-06-08 05:49:49',10,0,1,0,67,'note'),(147,'hk8VaAFd.docx','1','QmQ7GaspPnmMLkd9Q4S3m5zSZjo7qawQUnrkwT6YhfCJmY','2022-06-09 13:31:51','2022-06-09 13:31:51',10,1,1,0,70,'note'),(148,'hk8VaAFd.docx','1','Qmf54oafxaQw8Lvwyqrz6C3UMDqEX65rgsDuGnTxSVsCyC','2022-06-09 13:35:17','2022-06-09 13:35:17',10,0,1,0,70,'note'),(149,'hk8VaAFd.docx','1','Qmf54oafxaQw8Lvwyqrz6C3UMDqEX65rgsDuGnTxSVsCyC','2022-06-09 13:36:30','2022-06-09 13:36:30',10,0,1,0,70,'note'),(150,'下载.docx','1','QmQ7GaspPnmMLkd9Q4S3m5zSZjo7qawQUnrkwT6YhfCJmY','2022-06-09 13:37:04','2022-06-09 13:37:04',10,0,1,0,69,'note'),(151,'hk8VaAFd.docx','1','Qmf54oafxaQw8Lvwyqrz6C3UMDqEX65rgsDuGnTxSVsCyC','2022-06-09 13:38:12','2022-06-09 13:38:12',10,0,1,0,68,'note'),(152,'hk8VaAFd.docx','1','QmQGZmi1vfSKj6HVD4b8eT9iyPUh9UTVhRXiT9fQC9zajA','2022-06-09 13:38:26','2022-06-09 13:38:43',10,0,2,0,68,'note');

/*Table structure for table `history` */

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `contentId` bigint DEFAULT NULL COMMENT '文章id',
  `versionId` bigint DEFAULT '1' COMMENT '版本号码',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `mode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb3;

/*Data for the table `history` */

insert  into `history`(`id`,`title`,`contentId`,`versionId`,`content`,`create_time`,`mode`) values (70,NULL,55,2,'QmexucrphREzuJNcoLjt3jab2bosJp81wgaHLqpXXN2MW4','2022-05-25 02:09:38','1'),(71,'5.25',64,1,'QmWYddCPs7uR9EvHNCZzpguVFVNfHc6aM3hPVzPdAEESMc','2022-05-25 02:27:49','1'),(72,NULL,64,2,'QmRA2u9QsJqGz6pPPwwHyu4gRrzVUnPCqGLBMR9vm9G8pF','2022-05-25 02:28:28','1'),(73,'111111111111111111',65,1,'QmfBAqoXvVYf6Weehuz8gv3M3g4NyEJiCabQ6jNkmuhfhR','2022-05-25 02:32:08','1'),(74,'测试新增笔记',66,1,'QmdqFcgbPGEwMHwvtAy1S9jkiDtqFypdN5n8iN9pabMBYQ','2022-05-25 02:33:09','1'),(75,'测试新增笔记1',66,2,'QmZMBxod4qU5qXcfPhaHfY3GyZLq813tpYC9b57Kh7HvvZ','2022-05-25 02:34:33','1'),(76,'测试新增笔记12',66,3,'QmZMBxod4qU5qXcfPhaHfY3GyZLq813tpYC9b57Kh7HvvZ','2022-05-25 02:34:37','1'),(77,'5.33',67,1,'Qmbr2rDCV45phcm2vgSZmPLG6LjH6LUJYYMGauR7akP4TB','2022-05-25 02:36:28','1'),(78,NULL,67,2,'QmSZeFkRBLks6QV6WUmB7Nz7mx3ZWQmjtq2o7Ks5QPSfTm','2022-05-25 02:38:53','1'),(79,'689',68,1,'QmbzGnE8TpVr5BLPuk2osUZNp9xgiPGKZh6MScy8LCwvUb','2022-05-25 02:39:32','1'),(80,'test',69,1,'QmWYddCPs7uR9EvHNCZzpguVFVNfHc6aM3hPVzPdAEESMc','2022-05-25 02:43:10','2'),(81,NULL,69,2,'QmQyEcpXsxGr3sj5KXrsZZ3kPoivLxdr8dbJxbtP9gXDzR','2022-05-25 02:43:42','2'),(82,'11111111111.docx',70,1,'QmWMAuwF9T9LZWCBK5J961UW5RroRRuRFnCAKPPs7CPJHV','2022-05-26 01:30:55','3'),(83,'我的markdown文件',71,1,'QmZEwQzavs3PYvZdBcMD7oQ1EdHprd9W2LpUmcL7hokZUF','2022-05-26 02:41:49','2'),(84,'测试',72,1,'QmbshMti4cqcXnhdmUw5GMyMF9k7QvVy62BZ9XQHzjY1ni','2022-05-26 03:01:20','1'),(85,'测试1',72,2,'QmTsubVYaxMhNnQ5aa7zjLknph4ZBfhnz5qSgi4hrnQGFN','2022-05-26 03:02:01','1'),(86,'测试2221',72,3,'QmR7sDnkJERiJLSVdKPVTVqjwc53mVF6XpmGa8zpaipCj7','2022-05-26 03:02:40','1'),(87,'第六届校园提案.docx',73,1,'QmYrkkNjsduJJ9D2GQR9UkkiHBoLZN8wcWTGDP6gwYdLXd','2022-05-28 08:05:51','3'),(88,'第六届校园提案.docx',81,1,'QmYrkkNjsduJJ9D2GQR9UkkiHBoLZN8wcWTGDP6gwYdLXd','2022-05-28 08:48:22','3'),(89,'关于举办第十一届“中国软件杯”大学生软件设计大赛的通知.pdf',83,1,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-28 08:53:15','4'),(90,'Poorly_Written_Report_Sample.docx',86,1,'QmVP4KfLwXhU75F7vuZTEKRAQkEhCi5HupkpDNYQhyYq6N','2022-05-28 08:57:06','3'),(91,'README.md',90,1,'QmbFMke1KXqnYyBBWxB74N4c5SBnJMVAiMNRcGu6x1AwQH','2022-05-28 09:00:59','1'),(92,'IP Work Report_CoverPage_2021S2.docx',92,1,'QmTZHJKyWxxLPVMopDpuR1RSxaYWtYMTFCr3JovxVhysrp','2022-05-28 09:01:09','3'),(93,'工业实习报告.docx',94,1,'QmT4eBzvsmDH21pzYjmsrVKSFrdSYfLWSTsxTyiKZg4qR5','2022-05-28 09:04:01','3'),(94,'831904103陈子茜_工业实习计划书.pdf',95,1,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-28 09:05:16','4'),(95,'IP Work Report - Template_20220520.pdf',96,1,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-28 09:07:20','4'),(96,NULL,72,5,'QmR7sDnkJERiJLSVdKPVTVqjwc53mVF6XpmGa8zpaipCj7','2022-05-28 12:59:01','1'),(97,NULL,72,6,'QmR7sDnkJERiJLSVdKPVTVqjwc53mVF6XpmGa8zpaipCj7','2022-05-28 12:59:07','1'),(98,NULL,72,7,'QmR7sDnkJERiJLSVdKPVTVqjwc53mVF6XpmGa8zpaipCj7','2022-05-28 12:59:36','1'),(99,'MybatisPlus.md',97,1,'QmbTz7p6cGtgwgiz85BJqYzP48ZQBGEPjfkZGiN4iS3kpo','2022-05-28 14:09:30','1'),(100,'mdmdmdmdmdm',97,2,'QmT12zNHMn85oBADYAQB86R1bGjc1MWpZMtjByiGvW1uvn','2022-05-28 14:14:03','1'),(101,NULL,95,2,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-29 02:53:25','1'),(102,NULL,95,3,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-29 02:54:01','1'),(103,NULL,95,4,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-29 02:54:09','1'),(104,NULL,95,5,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-29 02:56:43','1'),(105,NULL,97,3,'QmT12zNHMn85oBADYAQB86R1bGjc1MWpZMtjByiGvW1uvn','2022-05-29 03:09:43','1'),(106,NULL,97,4,'QmT12zNHMn85oBADYAQB86R1bGjc1MWpZMtjByiGvW1uvn','2022-05-29 03:10:04','1'),(107,NULL,68,3,'QmSndLs33BFh8rWp4jEgnr3KgjQKStLhAESC8Bv8Fh6CuU','2022-05-29 03:13:20','1'),(108,'README.md',101,1,'QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 03:19:37','1'),(109,'README.md',102,1,'QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 03:21:27','1'),(110,'MybatisPlus.md',103,1,'QmbTz7p6cGtgwgiz85BJqYzP48ZQBGEPjfkZGiN4iS3kpo','2022-05-29 03:22:23','1'),(111,'IP Work Report - Template_20220520.pdf',104,1,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-29 03:31:18','1'),(112,NULL,97,5,'QmXLYJRW2UfASFfmv6BTw5PuQfAc9gicthSCbDBXergXn9','2022-05-29 03:32:48','1'),(113,'34届韩奖汉译外.pdf',105,1,'QmV9GDsHbpzCBmU2AaPz4EGHKdE6EXfTDBUZAvVvtxsrNc','2022-05-29 03:43:34','1'),(114,'34届韩奖汉译外.pdf',106,1,'QmeVep9M8BwzW3WFY5GGWaPtapmAR31426LVwCpFTYEgQH','2022-05-29 05:09:35','1'),(115,'IP Work Report - Template_20220520.pdf',107,1,'QmfXnEkevSE4K9oPTdVUN4tM1zXmZMJQ1yKWrWAFntC8a2','2022-05-29 05:43:46','1'),(116,'IP Work Report - Template_20220520.pdf',108,1,'QmfXnEkevSE4K9oPTdVUN4tM1zXmZMJQ1yKWrWAFntC8a2','2022-05-29 05:46:01','1'),(117,NULL,71,2,'QmZEwQzavs3PYvZdBcMD7oQ1EdHprd9W2LpUmcL7hokZUF','2022-05-29 05:50:01','2'),(118,'README.md',109,1,'QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 13:57:17','2'),(119,'Poorly_Written_Report_Sample.docx',110,1,'QmVP4KfLwXhU75F7vuZTEKRAQkEhCi5HupkpDNYQhyYq6N','2022-05-29 13:57:52','1'),(120,'README.md',112,1,'QmRctcMfWCLwnsuxuruyUigGyw26x74av1N1u5ihTtMskQ','2022-05-29 14:04:18','2'),(121,'IP Work Report - Template_20220520.pdf',113,1,'QmfXnEkevSE4K9oPTdVUN4tM1zXmZMJQ1yKWrWAFntC8a2','2022-05-29 14:19:14','1'),(122,NULL,106,2,'QmeVep9M8BwzW3WFY5GGWaPtapmAR31426LVwCpFTYEgQH','2022-05-29 14:26:25','1'),(123,NULL,65,2,'QmfBAqoXvVYf6Weehuz8gv3M3g4NyEJiCabQ6jNkmuhfhR','2022-06-03 05:34:20','1'),(124,NULL,106,3,'QmTcAyNXSgNw1vdjnqWgfBZmbYDAJCirv9N9no5eH1aTsd','2022-06-03 06:12:44','1'),(125,NULL,57,2,'QmaYuXMaSLnGMs2T3qMbruRSRtiDnwX5mF4EY2PhN2dbay','2022-06-03 11:20:59','1'),(126,NULL,67,3,'QmTvxEtKmV6TMA3tXqWJQ6C3x8SA4G8dobCxJhGv2Hbfzm','2022-06-04 06:12:49','1'),(127,'112121',124,1,'QmUqFBjNr9eSTxRY5UoFpkoMsEqYVzy1fEPgLyjdZihz6a','2022-06-04 06:21:38','1'),(128,'md',125,1,'QmWYddCPs7uR9EvHNCZzpguVFVNfHc6aM3hPVzPdAEESMc','2022-06-04 06:22:22','2'),(129,'1',126,1,'QmWYddCPs7uR9EvHNCZzpguVFVNfHc6aM3hPVzPdAEESMc','2022-06-04 06:29:29','2'),(130,'1',127,1,'QmWYddCPs7uR9EvHNCZzpguVFVNfHc6aM3hPVzPdAEESMc','2022-06-04 06:33:23','2'),(131,NULL,71,3,'Qmf3qV4pJDj5rAvnTjRcLcqeFatNYrGU32BGvMJTeQeUce','2022-06-04 06:43:15','2'),(132,NULL,71,4,'QmP6bdMCKPGXdYQ9L6tMP9Tj3e53czAnX6QBwmFuKWdTmw','2022-06-04 06:43:31','2'),(133,'老婆合集',128,1,'QmWZiJyZ3rCctkqeg2ETWebmCgomj2m4uYPdEJr3ouMtBJ','2022-06-04 14:52:47','2'),(134,NULL,128,3,'QmetAmdrAzFJ7Xi78vss4jQXLyPjcGpBqFUGEEauZLScpq','2022-06-04 14:53:36','2'),(135,NULL,106,4,'QmTkpSkH3ExgogbVRqDfPafhLeUJJuzwWayE3H8om7JJ5s','2022-06-04 14:55:04','1'),(136,NULL,54,2,'Qmc4SpzcmtxNFJAfCftnRtfUdfVwb7FcSD2PWHuFVVnjZ5','2022-06-04 14:56:59','1'),(137,NULL,128,4,'QmeXytsYZjjQkEMgycLSdifaesWQdZFMj3gYXcq5iqCh4r','2022-06-04 14:58:41','2'),(138,NULL,128,5,'QmYLWmJizJKzQgTvhHqFZRdH1MubhgNVXQQtuYPajFQCsS','2022-06-04 14:59:10','2'),(139,'测试',129,1,'Qmbc2XnQMmBjCPaXqWvPii1taM6VpvaWaDCGPMEm6y57nm','2022-06-04 14:59:48','1'),(140,NULL,129,2,'QmUQ1SvL92sLkfaeQaL6KGUmaA8kN8eDEKJD1bE1SiLPFk','2022-06-04 15:00:20','1'),(141,'外敷No.1',130,1,'QmeHSpyg39JPJkqCLoAJPAVGJ9gGZ8Bjo4W8Msaa47iPKR','2022-06-04 15:07:07','1'),(142,NULL,130,2,'QmWDbQZrXuicQSvmPm1g9DFECry99cUTUA3mdVeJCBcVo1','2022-06-04 15:11:31','1'),(143,'No2',131,1,'QmWYddCPs7uR9EvHNCZzpguVFVNfHc6aM3hPVzPdAEESMc','2022-06-04 15:12:09','2'),(144,NULL,131,2,'QmQbRywpgqCYLX6DD58R4LXpyLzJSpMQ7dVbaee7F1bf8d','2022-06-04 15:13:47','2'),(145,NULL,54,3,'QmNwmuTQwZoEEMaVe2M8tDvQ4ccnZbL34E6Y3rTSaVabwj','2022-06-07 15:37:20','1'),(146,'工业实习报告.docx',132,1,'QmT4eBzvsmDH21pzYjmsrVKSFrdSYfLWSTsxTyiKZg4qR5','2022-06-07 15:42:33','1'),(147,'README.md',133,1,'QmNYU62jfpnLW2MBGAE62E6Zqg2ZxUuyA19TN9rgdpT6Nr','2022-06-07 15:49:07','2'),(148,'我的.md',134,1,'QmUz4iTKfYb1DFGSCuTVdsPrgsYVGJAwr73neNa8Qr4v9n','2022-06-07 15:51:10','2'),(149,'我的word文档.docx',135,1,'QmfZVEyZr96svQjGAdPRnm62wH8hL1cd6Twx2zhZDbyuWf','2022-06-07 15:51:33','1'),(150,'我的word文档.pdf',136,1,'QmWntxF8nv9aGwCDSPv6aeRdQwrXft5ZicNJT1YorYBp6h','2022-06-07 15:51:37','1'),(151,'我的.md',137,1,'QmZRNegr2t1vUYRkfCifuHbMCXUZj9NMq3GUmMWgXzB3XX','2022-06-07 15:54:33','2'),(152,NULL,67,4,'QmZz1uVGfwBP6CrBUPxhvp6ECkB9psEXkRbpD1M655mCQU','2022-06-07 16:11:22','1'),(153,NULL,67,5,'QmZz1uVGfwBP6CrBUPxhvp6ECkB9psEXkRbpD1M655mCQU','2022-06-07 16:12:21','1'),(154,'测试1',138,1,'QmdYGuwcmbH3RUsjgiHGNughsKQmkjHppDnA4SsejX1Rb3','2022-06-07 16:16:53','2'),(155,'测试2',139,1,'QmdYGuwcmbH3RUsjgiHGNughsKQmkjHppDnA4SsejX1Rb3','2022-06-07 16:17:01','2'),(156,'测试3',140,1,'QmWYddCPs7uR9EvHNCZzpguVFVNfHc6aM3hPVzPdAEESMc','2022-06-07 16:17:17','1'),(157,NULL,54,4,'QmVG5PK9nDdPdpdczTGY1NvzbZt4j8XQjRgbWMiCpUHjWP','2022-06-07 16:20:37','1'),(158,NULL,140,2,'QmbgVaGpFyXxJCJe2Cf4N6NKzS9bRtnybbnMDtS8roznYg','2022-06-07 16:23:16','1'),(159,NULL,140,4,'Qmbshy28DU56HgqPoAvFatYiWMpmMnQSUJ1ugrAmk7MbAq','2022-06-07 16:25:08','1'),(160,'我的.md',142,1,'QmZRNegr2t1vUYRkfCifuHbMCXUZj9NMq3GUmMWgXzB3XX','2022-06-07 16:27:14','2'),(161,'我的word文档.docx',143,1,'QmNggXK53Ly9A5SG31qEWVTaXaXpwoZBpWjWbSGkSbTRPA','2022-06-07 16:27:20','1'),(162,NULL,143,2,'QmWrFgxm1WATHvX9WRJt6WFoH2njUAxa9Gq4UhnCkCPmZV','2022-06-07 16:28:25','2'),(163,'数据结构',144,1,'QmewtY21Ufyqa166AWXrSC3Y3FMqKMhpRRPH2tqTNMUALE','2022-06-08 05:28:39','2'),(164,'mysql入门',145,1,'QmewtY21Ufyqa166AWXrSC3Y3FMqKMhpRRPH2tqTNMUALE','2022-06-08 05:28:55','2'),(165,NULL,56,2,'QmTBQfYHGgAufEnW2x9CZ62vcBSQ7LSGTpfzbMhayTSNDr','2022-06-08 05:35:28','1'),(166,NULL,56,4,'QmcTDU7ARWn8ch9W5aJ6qfd788zcaQLRGZ3oLDrRm6LKTv','2022-06-08 05:46:38','1'),(167,NULL,56,5,'Qmda9rE47zpKUpzKqrfiu95HGXLSkUoN6q8J9MkCPKZNex','2022-06-08 05:46:44','1'),(168,'测试',146,1,'QmeMPWbCGPZascKZPXWAhxpq7kGfrcM4UnBFeeqbwGU24h','2022-06-08 05:49:49','2'),(169,'hk8VaAFd.docx',147,1,'QmQ7GaspPnmMLkd9Q4S3m5zSZjo7qawQUnrkwT6YhfCJmY','2022-06-09 13:31:51','1'),(170,NULL,57,3,'QmaYuXMaSLnGMs2T3qMbruRSRtiDnwX5mF4EY2PhN2dbay','2022-06-09 13:34:30','1'),(171,NULL,143,3,'QmU7LRRridvVY3WK14BS8qS3UvbBjZ9wc8GepG9etr7LBC','2022-06-09 13:35:03','2'),(172,'hk8VaAFd.docx',148,1,'Qmf54oafxaQw8Lvwyqrz6C3UMDqEX65rgsDuGnTxSVsCyC','2022-06-09 13:35:17','1'),(173,NULL,143,4,'QmS6vZJmNV234NHYWoe8eLHBzgtJatvmcU16WfH2GPbWQM','2022-06-09 13:35:53','2'),(174,NULL,54,6,'QmSHzdKAsxfxbV2ajCTzKFYSrzDGw84xZRzf7U2vN8pVgs','2022-06-09 13:36:15','1'),(175,'下载.docx',150,1,'QmQ7GaspPnmMLkd9Q4S3m5zSZjo7qawQUnrkwT6YhfCJmY','2022-06-09 13:37:04','1'),(176,NULL,54,7,'QmYfwVLs96J8iacKoXZbZhYHHPgybQrmLyGqDSbne6cL7c','2022-06-09 13:37:30','1'),(177,NULL,143,5,'Qmc66PkrJ18UVruS8LrL1TdhUv53hPMVYpW74ywA4w5sKm','2022-06-09 13:37:53','2'),(178,'hk8VaAFd.docx',151,1,'Qmf54oafxaQw8Lvwyqrz6C3UMDqEX65rgsDuGnTxSVsCyC','2022-06-09 13:38:12','1'),(179,NULL,152,2,'QmQGZmi1vfSKj6HVD4b8eT9iyPUh9UTVhRXiT9fQC9zajA','2022-06-09 13:38:43','1'),(180,NULL,140,5,'QmYs2gi9Bos7DCtkE7UDKwyybLKYrEpJQjtRnLVwzD6nM5','2022-06-09 13:53:57','1'),(181,NULL,139,2,'QmRo7Sroq2v8c3zU1K1PzEHsRch8djtrN6H28d9C4JEuZ8','2022-06-09 13:58:44','2');

/*Table structure for table `logs` */

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` text COMMENT '行为',
  `userId` bigint DEFAULT NULL COMMENT '用户id',
  `ip` text COMMENT 'ip地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8mb3;

/*Data for the table `logs` */

insert  into `logs`(`id`,`action`,`userId`,`ip`,`create_time`) values (135,'登录系统',10,'192.168.13.1','2022-05-24 05:04:22'),(136,'登录系统',10,'127.0.0.1','2022-05-25 01:12:24'),(137,'登录系统',10,'192.168.13.1','2022-05-25 01:12:39'),(138,'登录系统',10,'192.168.13.1','2022-05-25 01:36:09'),(139,'登录系统',10,'192.168.13.1','2022-05-25 05:27:30'),(140,'登录系统',10,'127.0.0.1','2022-05-26 01:21:17'),(141,'登录系统',10,'192.168.13.1','2022-05-26 01:30:00'),(142,'登录系统',10,'127.0.0.1','2022-05-26 03:01:04'),(143,'登录系统',10,'127.0.0.1','2022-05-28 08:01:03'),(144,'登录系统',10,'192.168.13.1','2022-05-28 13:30:51'),(145,'登录系统',10,'192.168.13.1','2022-05-29 02:47:13'),(146,'登录系统',10,'192.168.13.1','2022-05-29 03:42:22'),(147,'登录系统',10,'192.168.13.1','2022-05-29 13:05:07'),(148,'登录系统',10,'192.168.13.1','2022-05-29 13:28:55'),(149,'登录系统',10,'192.168.13.1','2022-05-29 13:34:20'),(150,'登录系统',10,'127.0.0.1','2022-05-29 14:03:12'),(151,'登录系统',10,'127.0.0.1','2022-06-03 05:33:48'),(152,'登录系统',10,'127.0.0.1','2022-06-03 05:36:35'),(153,'登录系统',10,'192.168.13.1','2022-06-03 06:20:22'),(154,'登录系统',10,'127.0.0.1','2022-06-03 11:15:39'),(155,'登录系统',10,'192.168.13.1','2022-06-04 03:53:32'),(156,'登录系统',10,'127.0.0.1','2022-06-04 05:51:30'),(157,'登录系统',10,'192.168.13.1','2022-06-04 06:20:52'),(158,'登录系统',10,'127.0.0.1','2022-06-04 06:56:47'),(159,'登录系统',10,'192.168.13.1','2022-06-04 07:23:26'),(160,'登录系统',10,'127.0.0.1','2022-06-04 14:07:08'),(161,'登录系统',10,'127.0.0.1','2022-06-04 14:16:38'),(162,'登录系统',10,'127.0.0.1','2022-06-04 14:29:00'),(163,'登录系统',10,'127.0.0.1','2022-06-04 14:29:14'),(164,'登录系统',10,'127.0.0.1','2022-06-04 14:33:58'),(165,'登录系统',10,'127.0.0.1','2022-06-04 14:34:01'),(166,'登录系统',10,'127.0.0.1','2022-06-04 14:46:48'),(167,'登录系统',10,'127.0.0.1','2022-06-04 15:00:46'),(168,'登录系统',10,'127.0.0.1','2022-06-04 15:02:05'),(169,'登录系统',10,'127.0.0.1','2022-06-04 15:15:11'),(170,'登录系统',10,'192.168.13.1','2022-06-04 15:20:29'),(171,'登录系统',10,'127.0.0.1','2022-06-04 15:23:11'),(172,'登录系统',10,'127.0.0.1','2022-06-04 15:55:53'),(173,'登录系统',10,'127.0.0.1','2022-06-04 16:16:03'),(174,'登录系统',10,'127.0.0.1','2022-06-07 15:29:04'),(175,'登录系统',10,'127.0.0.1','2022-06-07 15:33:26'),(176,'登录系统',10,'127.0.0.1','2022-06-07 16:15:10'),(177,'登录系统',10,'127.0.0.1','2022-06-08 05:16:29'),(178,'登录系统',15,'127.0.0.1','2022-06-08 05:58:58'),(179,'登录系统',15,'127.0.0.1','2022-06-08 08:24:09'),(180,'登录系统',10,'127.0.0.1','2022-06-08 08:24:38'),(181,'登录系统',10,'127.0.0.1','2022-06-09 13:31:25'),(182,'登录系统',10,'127.0.0.1','2022-06-09 13:51:13');

/*Table structure for table `qz_date` */

DROP TABLE IF EXISTS `qz_date`;

CREATE TABLE `qz_date` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(255) DEFAULT NULL COMMENT '二维码标号',
  `url` varchar(255) DEFAULT NULL COMMENT '二维码地址',
  `end_time` datetime DEFAULT NULL COMMENT '失效时间',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `qz_date` */

/*Table structure for table `share` */

DROP TABLE IF EXISTS `share`;

CREATE TABLE `share` (
  `id` bigint NOT NULL,
  `contentId` bigint DEFAULT NULL COMMENT '文章id',
  `userId` bigint DEFAULT NULL COMMENT '用户id',
  `linkId` bigint DEFAULT NULL COMMENT '链接id',
  `allowComment` bigint DEFAULT '0' COMMENT '是否评论：0评论，1不评论',
  `locked` bigint DEFAULT '1' COMMENT '是否加锁：0锁住，1不锁柱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `qrcode` text COMMENT '二维码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `share` */

insert  into `share`(`id`,`contentId`,`userId`,`linkId`,`allowComment`,`locked`,`password`,`create_time`,`qrcode`) values (54,54,10,35,0,1,NULL,'2022-06-04 15:17:40','QmRzg21jYrEUjSXWuu3ciUTEK63rBDXuhpXUR6Mh6XEjgd'),(56,56,10,36,0,1,NULL,'2022-06-04 15:20:49','QmbNSZyanJj1cHNmYo7VoHYbaH6oFnxhzHjXY7f4BTDQCF'),(57,57,10,34,0,1,NULL,'2022-06-04 15:16:37','QmeGiuTfAHv8b6nWcpCD3itGntxHKL8yNWoEpeDcvyiXSf'),(64,64,10,44,0,1,NULL,'2022-06-04 15:50:35','QmXYPkZ9pVUuf2ur7WaCbnuvQDnsyJaxNbEAPTJdPNWWAN'),(65,65,10,38,0,1,NULL,'2022-06-04 15:36:07','QmY5c77i1hYqdMbn5RtKtF1VCQFqL8D1s5NnGeFyJCKaZF'),(67,67,10,33,0,1,NULL,'2022-06-04 14:19:31','QmNpjm2CeoyqaA1zbmnjVBgSNoyhg3ZEdrU9cGmhm9mj7D'),(71,71,10,29,0,1,NULL,'2022-06-04 14:16:59','QmZCAayckXWpn393Brj6jyU5b7bKGp4wpGFckknnLigN58'),(131,131,10,46,0,1,NULL,'2022-06-04 15:53:07','QmWRhnmSQCpibATVqUCBQEnzS9TLnAqUhwmRqf7pZf57Cv');

/*Table structure for table `short_link` */

DROP TABLE IF EXISTS `short_link`;

CREATE TABLE `short_link` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shorts` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `short_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `long_link` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `flag` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

/*Data for the table `short_link` */

insert  into `short_link`(`id`,`shorts`,`short_link`,`long_link`,`create_time`,`flag`) values (29,'0xU087h0','http://mycloudnote.gz2vip.91tunnel.com/short/0xU087h0','http://re.vipgz1.91tunnel.com/share/71','2022-06-04 14:16:57',0),(30,'HWdsfMoW','http://mycloudnote.gz2vip.91tunnel.com/short/HWdsfMoW','http://re.vipgz1.91tunnel.com/share/71','2022-06-04 14:17:02',0),(31,'JYi68rre','http://mycloudnote.gz2vip.91tunnel.com/short/JYi68rre','http://re.vipgz1.91tunnel.com/share/71','2022-06-04 14:18:11',0),(32,'0mMiDxBT','http://mycloudnote.gz2vip.91tunnel.com/short/0mMiDxBT','http://re.vipgz1.91tunnel.com/share/71','2022-06-04 14:18:25',0),(33,'vY084QJ4','http://mycloudnote.gz2vip.91tunnel.com/short/vY084QJ4','http://re.vipgz1.91tunnel.com/share/67','2022-06-04 14:19:31',0),(34,'xqVpl1TR','http://mycloudnote.gz2vip.91tunnel.com/short/xqVpl1TR','http://re.vipgz1.91tunnel.com/share/57','2022-06-04 15:16:37',0),(35,'shSzumr3','http://mycloudnote.gz2vip.91tunnel.com/short/shSzumr3','http://re.vipgz1.91tunnel.com/share/54','2022-06-04 15:17:40',0),(36,'l060ebu8','http://mycloudnote.gz2vip.91tunnel.com/short/l060ebu8','http://re.vipgz1.91tunnel.com/share/56','2022-06-04 15:20:49',0),(37,'vipD1OaY','http://mycloudnote.gz2vip.91tunnel.com/short/vipD1OaY','http://mycloudnote.gz2vip.91tunnel.com/short/l060ebu8','2022-06-04 15:25:31',0),(38,'Fd17J3tP','http://mycloudnote.gz2vip.91tunnel.com/short/Fd17J3tP','http://re.vipgz1.91tunnel.com/share/65','2022-06-04 15:36:07',0),(39,'XiiUsNsj','http://mycloudnote.gz2vip.91tunnel.com/short/XiiUsNsj','http://re.vipgz1.91tunnel.com/share/65','2022-06-04 15:36:07',0),(40,'hEHoqKD1','http://mycloudnote.gz2vip.91tunnel.com/short/hEHoqKD1','http://re.vipgz1.91tunnel.com/share/57','2022-06-04 15:40:03',0),(41,'tWzlYk82','http://mycloudnote.gz2vip.91tunnel.com/short/tWzlYk82','http://re.vipgz1.91tunnel.com/share/57','2022-06-04 15:40:04',0),(42,'0IudcNlw','http://mycloudnote.gz2vip.91tunnel.com/short/0IudcNlw','http://re.vipgz1.91tunnel.com/share/71','2022-06-04 15:40:49',0),(43,'LxtjIcIh','http://mycloudnote.gz2vip.91tunnel.com/short/LxtjIcIh','http://re.vipgz1.91tunnel.com/share/71','2022-06-04 15:40:49',0),(44,'jS4xS9xn','http://mycloudnote.gz2vip.91tunnel.com/short/jS4xS9xn','http://re.vipgz1.91tunnel.com/share/64','2022-06-04 15:50:35',0),(45,'fApadNAC','http://mycloudnote.gz2vip.91tunnel.com/short/fApadNAC','http://re.vipgz1.91tunnel.com/share/64','2022-06-04 15:50:36',0),(46,'Uyfxloos','http://mycloudnote.gz2vip.91tunnel.com/short/Uyfxloos','http://re.vipgz1.91tunnel.com/share/131','2022-06-04 15:53:07',0),(47,'mbPk2B1V','http://mycloudnote.gz2vip.91tunnel.com/short/mbPk2B1V','http://re.vipgz1.91tunnel.com/share/131','2022-06-04 15:53:07',0),(48,'kPhUzflf','http://mycloudnote.gz2vip.91tunnel.com/short/kPhUzflf','http://re.vipgz1.91tunnel.com/share/131','2022-06-04 15:54:36',0),(49,'XgX9rANa','http://mycloudnote.gz2vip.91tunnel.com/short/XgX9rANa','http://re.vipgz1.91tunnel.com/share/131','2022-06-04 15:54:36',0),(50,'1hp8wmxt','http://mycloudnote.gz2vip.91tunnel.com/short/1hp8wmxt','http://re.vipgz1.91tunnel.com/share/131','2022-06-04 15:54:44',0),(51,'Fflfupf2','http://mycloudnote.gz2vip.91tunnel.com/short/Fflfupf2','http://re.vipgz1.91tunnel.com/share/131','2022-06-04 15:54:44',0),(52,'6wJl9pun','http://mycloudnote.gz2vip.91tunnel.com/short/6wJl9pun','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 15:56:49',0),(53,'Fv8R7kMr','http://mycloudnote.gz2vip.91tunnel.com/short/Fv8R7kMr','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 15:56:49',0),(54,'tDelsbxJ','http://mycloudnote.gz2vip.91tunnel.com/short/tDelsbxJ','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 15:56:53',0),(55,'wd4Rjv45','http://mycloudnote.gz2vip.91tunnel.com/short/wd4Rjv45','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 15:56:54',0),(56,'P4VlFEtQ','http://mycloudnote.gz2vip.91tunnel.com/short/P4VlFEtQ','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 15:57:11',0),(57,'MtT3bX7g','http://mycloudnote.gz2vip.91tunnel.com/short/MtT3bX7g','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 15:57:11',0),(58,'EIxCpiMv','http://mycloudnote.gz2vip.91tunnel.com/short/EIxCpiMv','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 16:05:45',0),(59,'ljKk35ii','http://mycloudnote.gz2vip.91tunnel.com/short/ljKk35ii','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 16:05:45',0),(60,'q9PInr5H','http://mycloudnote.gz2vip.91tunnel.com/short/q9PInr5H','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 16:05:50',0),(61,'ThwdVwbT','http://mycloudnote.gz2vip.91tunnel.com/short/ThwdVwbT','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 16:05:51',0),(62,'r4updE6c','http://mycloudnote.gz2vip.91tunnel.com/short/r4updE6c','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 16:05:58',0),(63,'nEclIIvA','http://mycloudnote.gz2vip.91tunnel.com/short/nEclIIvA','http://re.vipgz1.91tunnel.com/share/67','2022-06-07 16:05:58',0),(64,'0MVtxvET','http://mycloudnote.gz2vip.91tunnel.com/short/0MVtxvET','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:13',0),(65,'x36NIsfD','http://mycloudnote.gz2vip.91tunnel.com/short/x36NIsfD','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:13',0),(66,'aWyALzbJ','http://mycloudnote.gz2vip.91tunnel.com/short/aWyALzbJ','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:19',0),(67,'e8lsldx7','http://mycloudnote.gz2vip.91tunnel.com/short/e8lsldx7','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:19',0),(68,'56wUHeyJ','http://mycloudnote.gz2vip.91tunnel.com/short/56wUHeyJ','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:23',0),(69,'vWo01Dwz','http://mycloudnote.gz2vip.91tunnel.com/short/vWo01Dwz','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:23',0),(70,'pBx7FoWE','http://mycloudnote.gz2vip.91tunnel.com/short/pBx7FoWE','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:56',0),(71,'oMmCyVI3','http://mycloudnote.gz2vip.91tunnel.com/short/oMmCyVI3','http://re.vipgz1.91tunnel.com/share/54','2022-06-07 16:30:57',0),(72,'u2LkqHxj','http://mycloudnote.gz2vip.91tunnel.com/short/u2LkqHxj','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:40:07',0),(73,'aGe1f4JP','http://mycloudnote.gz2vip.91tunnel.com/short/aGe1f4JP','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:40:07',0),(74,'Fh53a6K0','http://mycloudnote.gz2vip.91tunnel.com/short/Fh53a6K0','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:40:45',0),(75,'2ibgLZdF','http://mycloudnote.gz2vip.91tunnel.com/short/2ibgLZdF','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:40:46',0),(76,'VW2JYVT1','http://mycloudnote.gz2vip.91tunnel.com/short/VW2JYVT1','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:26',0),(77,'Je7ZWbmV','http://mycloudnote.gz2vip.91tunnel.com/short/Je7ZWbmV','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:27',0),(78,'SQjHTk1P','http://mycloudnote.gz2vip.91tunnel.com/short/SQjHTk1P','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:27',0),(79,'FlFQKDFl','http://mycloudnote.gz2vip.91tunnel.com/short/FlFQKDFl','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:27',0),(80,'ulI6ysBu','http://mycloudnote.gz2vip.91tunnel.com/short/ulI6ysBu','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:28',0),(81,'voF3e3ec','http://mycloudnote.gz2vip.91tunnel.com/short/voF3e3ec','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:28',0),(82,'sJHT3oVf','http://mycloudnote.gz2vip.91tunnel.com/short/sJHT3oVf','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:29',0),(83,'dkD5fMpE','http://mycloudnote.gz2vip.91tunnel.com/short/dkD5fMpE','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:29',0),(84,'Gx6RfhlY','http://mycloudnote.gz2vip.91tunnel.com/short/Gx6RfhlY','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:30',0),(85,'NvUNfbXr','http://mycloudnote.gz2vip.91tunnel.com/short/NvUNfbXr','http://re.vipgz1.91tunnel.com/share/56','2022-06-08 05:41:31',0),(86,'Vlug345a','http://mycloudnote.gz2vip.91tunnel.com/short/Vlug345a','http://re.vipgz1.91tunnel.com/share/54','2022-06-08 08:49:50',0),(87,'aXTMoqAs','http://mycloudnote.gz2vip.91tunnel.com/short/aXTMoqAs','http://re.vipgz1.91tunnel.com/share/54','2022-06-08 08:49:50',0);

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tagsName` varchar(50) DEFAULT NULL COMMENT 'tag名字',
  `contentId` bigint DEFAULT NULL COMMENT '文章id',
  `userId` bigint DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;

/*Data for the table `tag` */

insert  into `tag`(`id`,`tagsName`,`contentId`,`userId`) values (1,'测试1',54,10),(2,'测试2',54,10),(4,'测试1',55,10),(7,'测试',57,10),(10,'ces',68,10),(11,'123',68,10),(16,'翻译',106,10),(17,'！',106,10),(18,'li',65,10),(19,'11',57,10),(20,'12',57,10),(25,'测试',67,10),(26,'1',67,10),(30,'新增',140,10),(31,'测试',140,10),(32,'测试',56,10),(33,'新增',56,10),(34,'专业课',56,10);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户名',
  `password` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '密码',
  `tel` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `introduce` varchar(255) DEFAULT NULL COMMENT '签名',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`tel`,`email`,`create_time`,`update_time`,`introduce`,`avatar`) values (10,'云笔记小c','0b3dad2f0452d8f52917d63cf6e825ec33f21ae2','13527492912','21775638207@qq.com','2022-05-17 22:23:07','2022-06-07 16:15:29','iiiiiiiiii','http://cyjspace.5gzvip.91tunnel.com/ipfs/Qmaj2suU9iJYbZXgkSm7LUPSrggfu1HWHreKjzSBcrDykx'),(12,'983876112083980288','0b3dad2f0452d8f52917d63cf6e825ec33f21ae2','13706988002',NULL,'2022-06-07 15:32:25','2022-06-07 15:32:25',NULL,'https://img1.baidu.com/it/u=4117401193,3509866561&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500'),(13,'983876328136773632','0b3dad2f0452d8f52917d63cf6e825ec33f21ae2','13706988022',NULL,'2022-06-07 15:33:16','2022-06-07 15:33:16',NULL,'https://img1.baidu.com/it/u=4117401193,3509866561&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500'),(14,'983886835602296832','0b3dad2f0452d8f52917d63cf6e825ec33f21ae2','13706988111',NULL,'2022-06-07 16:15:01','2022-06-07 16:15:01',NULL,'https://img1.baidu.com/it/u=4117401193,3509866561&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500'),(15,'984093641251360768','0b3dad2f0452d8f52917d63cf6e825ec33f21ae2','13706988003',NULL,'2022-06-08 05:56:48','2022-06-08 05:56:48',NULL,'https://img1.baidu.com/it/u=4117401193,3509866561&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
