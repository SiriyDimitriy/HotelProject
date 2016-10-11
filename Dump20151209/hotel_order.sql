CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.5.46-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Номер заказа',
  `orderdate` datetime NOT NULL COMMENT 'Дата заказа',
  `useremail` varchar(255) NOT NULL COMMENT 'Вторичный ключ от таблицы user',
  `class` varchar(20) NOT NULL,
  `places` int(2) NOT NULL,
  `startdate` date NOT NULL COMMENT 'Дата начала диапазона',
  `enddate` date NOT NULL COMMENT 'Дата конца диапазона',
  `usercomment` text,
  `roomnumber` smallint(6) DEFAULT NULL COMMENT 'Вторичный ключ от табицы room',
  `cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_users` (`useremail`),
  KEY `fk_orders_rooms` (`roomnumber`),
  CONSTRAINT `fk_orders_users` FOREIGN KEY (`useremail`) REFERENCES `user` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2015-12-03 00:00:00','Vasilisa@olimp.ku','Люкс',2,'2015-12-04','2015-12-06','',85,360),(2,'2015-12-03 00:00:00','Vasilisa@olimp.ku','Люкс',1,'2015-12-12','2015-12-15','',83,300),(4,'2015-12-03 00:00:00','Vasilisa@olimp.ku','Люкс',2,'2015-12-27','2015-12-28','',88,180),(6,'2015-12-06 00:00:00','Meduza@aid.set','Стандарт',1,'2015-12-07','2015-12-10','Мне, пожалуйста, тёмную сырую комнату, да без зеркал!!!',4,150),(7,'2015-12-06 00:00:00','petrosyan@ya.pe','Стандарт',1,'2016-04-02','2016-04-22','Всем ржать.',4,800),(8,'2015-12-07 00:00:00','kashchey@smerti.net','Люкс',3,'2015-12-10','2015-12-12','',46,540),(10,'2015-12-07 00:00:00','kashchey@smerti.net','Люкс',3,'2015-12-10','2015-12-12','',86,540),(11,'2015-12-07 00:00:00','Batman@gmail.com','Люкс',3,'2015-12-10','2015-12-20','',89,2160);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-09 11:25:31
