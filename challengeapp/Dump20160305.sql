CREATE DATABASE  IF NOT EXISTS `rivals` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rivals`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: rivals
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `challenges`
--

DROP TABLE IF EXISTS `challenges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenges` (
  `challengeid` int(11) NOT NULL AUTO_INCREMENT,
  `creatoruid` int(11) NOT NULL,
  `acceptoruid` int(11) DEFAULT NULL,
  `fbchallengeid` varchar(1000) DEFAULT NULL,
  `starttime` timestamp NULL DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `challengetype` varchar(45) NOT NULL,
  `endtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`challengeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenges`
--

LOCK TABLES `challenges` WRITE;
/*!40000 ALTER TABLE `challenges` DISABLE KEYS */;
/*!40000 ALTER TABLE `challenges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_challenge_mapping`
--

DROP TABLE IF EXISTS `player_challenge_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;


CREATE TABLE `player_challenge_mapping` (
  `playerID` int(11) NOT NULL AUTO_INCREMENT,
  `challengeID` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `winstatus` varchar(45) DEFAULT NULL,
  `fblikes` int(11) DEFAULT NULL,
  `player_image` blob,
  `playertype` varchar(45) DEFAULT NULL,
  `player_name` varchar(200) DEFAULT NULL,
  `playerinfo1` varchar(200) DEFAULT NULL,
  `playerinfo2` varchar(200) DEFAULT NULL,
  `playerinfo3` varchar(200) DEFAULT NULL,
  `playerinfo4` varchar(200) DEFAULT NULL,
  `playerinfo5` varchar(200) DEFAULT NULL,
  `playerinfo6` varchar(200) DEFAULT NULL,
  `playerinfo7` varchar(200) DEFAULT NULL,
  `playerinfo8` varchar(200) DEFAULT NULL,
  `playerinfo9` varchar(200) DEFAULT NULL,
  `playerinfo10` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`playerID`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `userid` FOREIGN KEY (`uid`) REFERENCES `user_tokens` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_challenge_mapping`
--

LOCK TABLES `player_challenge_mapping` WRITE;
/*!40000 ALTER TABLE `player_challenge_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_challenge_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduler`
--

DROP TABLE IF EXISTS `scheduler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduler` (
  `scheduleid` int(11) NOT NULL AUTO_INCREMENT,
  `challengeid` int(11) DEFAULT NULL,
  `creatoruid` int(11) DEFAULT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expiryindays` varchar(30) NOT NULL,
  PRIMARY KEY (`scheduleid`),
  KEY `challenge_id_idx` (`challengeid`),
  CONSTRAINT `challenge_id` FOREIGN KEY (`challengeid`) REFERENCES `challenges` (`challengeid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduler`
--

LOCK TABLES `scheduler` WRITE;
/*!40000 ALTER TABLE `scheduler` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `username` varchar(500) NOT NULL,
  `useremail` varchar(1000) NOT NULL,
  `devicetype` varchar(45) NOT NULL,
  `deviceid` varchar(200) NOT NULL,
  `totalchallenges` int(11) DEFAULT NULL,
  `totalwins` int(11) DEFAULT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastupdateddate` timestamp NULL DEFAULT NULL,
  `userimage` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tokens`
--

DROP TABLE IF EXISTS `user_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_tokens` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `fbtoken` varchar(1500) NOT NULL,
  `lastupdteddate` timestamp NULL DEFAULT NULL,
  `createddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `useremail` varchar(500) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tokens`
--

LOCK TABLES `user_tokens` WRITE;
/*!40000 ALTER TABLE `user_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'rivals'
--

--
-- Dumping routines for database 'rivals'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-05 22:14:20
