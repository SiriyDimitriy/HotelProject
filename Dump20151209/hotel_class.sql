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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `name` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `foto` varchar(100) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('Люкс','Всего несколько тысячелетий назад у нас появились номера типа Люкс. Это вершина к которой мы стремились и достигли ее. Здесь не только балконы, солнечный свет, телевизоры и туалет...В сервис включены даже услуги наших юных куртизанок, которые обучаются в собственной школе при нашем отеле.','Lux'),('Полулюкс','Для гостей которые любят комфорт и тепло (и не жлобятся в финансовм плане) у нас имеются номера типа полулюкс. В таких номерах цена соответствует качеству. Здесь найдется и кресло (оно же диван одноместный), и столик, за которым можно попить чаю, и телевизор, с аналоговой антенной и тремя каналами соответственно: УТ-1, 1+1 и Голос. Отличительной особенностью номеров Полулюкс является наличие балкона, в комнате всегда светло, ведь река стикс и царство Аида с другой стороны (со стороны номеров Стандарт).','HalfLux'),('Стандарт','Согласно статистике, самым востребованным классом является номер типа Стандарт. Посетители не зя отдают предпочтение этим номерам, ведь здесь они находят не только тепло от трехлампочной люстры, а и уют. Ведь стены обклеены высококачественными обоями, пусть и немного пошарпанными, но они создают особую, неповторимую атмосферу. В каждом номере класса стандарт имеется выход на балкон, но самого балкона нет. Также следует отметить, что все окна застеклены, а отсутствие балкона - не беда, ведь там, - внизу, в таинственной темноте течет река Стикс, екскурсия проводится за наш счет, Перевозчик об этом вкурсе. Также номер стандарт отличается количеством стульев (здесь их заметно больше) и есть досточка которой подпирают окно с балкона (которого нет) при сильных сквозняках.','Standart'),('Эконом','Номера класса \"Эконом\" отличаются не только низкими ценами, но и своей уютностью. В комнатах этого класса всегда светло и свежо. Здесь Вы найдете даже стул, на котором можно посидеть.Если, вдруг, станет прохладно, - всегда можно разжечь костер с остатков второго стула, заблаговременно оставленных предыдущими клиентами. В номерах эконом класса нет телевизора или телефона, но наши клиенты всегда могут скрасить этот недостаток общением с тараканом Гошей и паучком Сеней, которые проживают в нашем номере на правах старожилов и сооснователей этого прекрасного комплекса.','Econom');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
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
