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
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `number` smallint(6) NOT NULL,
  `class` varchar(20) NOT NULL COMMENT 'Внешний ключ к таблице class',
  `places` int(1) NOT NULL,
  `price` int(5) NOT NULL,
  `comment` text,
  PRIMARY KEY (`number`),
  KEY `fk_rooms_classes` (`class`),
  CONSTRAINT `fk_rooms_classes` FOREIGN KEY (`class`) REFERENCES `class` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (3,'Эконом',1,30,'Комната без электричества'),(4,'Стандарт',1,50,'Не селить до Нового Года'),(18,'Люкс',1,100,'18.12. 2015 не селить!!'),(23,'Эконом',2,50,'Стул забрали в 37'),(36,'Полулюкс',2,110,''),(45,'Эконом',3,70,''),(46,'Люкс',3,270,'Убить паука в углу'),(48,'Стандарт',3,140,'Телевизор вынесли до 15 декабря'),(49,'Эконом',1,30,'Убрать номер!!!'),(50,'Эконом',1,30,NULL),(52,'Эконом',2,50,'Заменить лампочку'),(55,'Эконом',2,50,NULL),(56,'Эконом',3,70,NULL),(57,'Эконом',3,70,'Течёт крыша, подставить тазик'),(58,'Эконом',3,70,'Поцарапана мебель'),(61,'Стандарт',1,50,NULL),(62,'Стандарт',1,50,'Нет постельного белья'),(63,'Стандарт',2,90,'В туалете не работает слив, починить'),(64,'Стандарт',2,90,'Вывести пятно на коврике.'),(65,'Стандарт',2,90,NULL),(66,'Стандарт',3,140,NULL),(67,'Стандарт',3,140,'В номере наблюдается беспричинная вонь, возможно, кто-то спрятал там труп.'),(71,'Полулюкс',1,70,'Разболталась дверная ручкаю'),(72,'Полулюкс',2,110,'Сильно сквозит'),(74,'Полулюкс',3,190,'Детей не селить'),(76,'Полулюкс',1,70,'Водятся привидения'),(77,'Полулюкс',2,110,NULL),(78,'Полулюкс',3,190,'Перекрыт водный вентиль, сантехник в отпуске'),(79,'Полулюкс',1,70,NULL),(83,'Люкс',1,100,'Новые занавески'),(85,'Люкс',2,180,NULL),(86,'Люкс',3,270,'В номере забыт телефон 15 ноября'),(87,'Люкс',1,100,'Этот номер ограбили'),(88,'Люкс',2,180,NULL),(89,'Люкс',3,270,'Лучший вид на Олимп'),(97,'Люкс',1,100,NULL);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
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
