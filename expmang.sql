-- MySQL dump 10.13  Distrib 8.3.0, for Win64 (x86_64)
--
-- Host: localhost    Database: expmang
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (4,'Introduction to Computer Science','A foundational course in computer science.'),(5,'java','A foundational course in computer science.'),(6,'C++','A foundational course in computer science.'),(23,'springboot','springboot的应用开发');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_experiment`
--

DROP TABLE IF EXISTS `custom_experiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_experiment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customDetails` text,
  `experiment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_CUSTOM_EXPERIMENT_EXPERIMENT` (`experiment_id`),
  CONSTRAINT `fk_CUSTOM_EXPERIMENT_EXPERIMENT` FOREIGN KEY (`experiment_id`) REFERENCES `experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_experiment`
--

LOCK TABLES `custom_experiment` WRITE;
/*!40000 ALTER TABLE `custom_experiment` DISABLE KEYS */;
/*!40000 ALTER TABLE `custom_experiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data`
--

DROP TABLE IF EXISTS `data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data`
--

LOCK TABLES `data` WRITE;
/*!40000 ALTER TABLE `data` DISABLE KEYS */;
/*!40000 ALTER TABLE `data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_analysis`
--

DROP TABLE IF EXISTS `data_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_analysis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(255) DEFAULT NULL,
  `focus` varchar(255) DEFAULT NULL,
  `data_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_DATA_ANALYSIS_DATA` (`data_id`),
  CONSTRAINT `fk_DATA_ANALYSIS_DATA` FOREIGN KEY (`data_id`) REFERENCES `data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_analysis`
--

LOCK TABLES `data_analysis` WRITE;
/*!40000 ALTER TABLE `data_analysis` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_export`
--

DROP TABLE IF EXISTS `data_export`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_export` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(255) DEFAULT NULL,
  `format_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_DATA_EXPORT_FORMAT` (`format_id`),
  CONSTRAINT `fk_DATA_EXPORT_FORMAT` FOREIGN KEY (`format_id`) REFERENCES `format` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_export`
--

LOCK TABLES `data_export` WRITE;
/*!40000 ALTER TABLE `data_export` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_export` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment`
--

DROP TABLE IF EXISTS `experiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experiment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_EXPERIMENT_COURSE` (`course_id`),
  CONSTRAINT `fk_EXPERIMENT_COURSE` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment`
--

LOCK TABLES `experiment` WRITE;
/*!40000 ALTER TABLE `experiment` DISABLE KEYS */;
INSERT INTO `experiment` VALUES (4,'math1 Experiment','math1',4),(6,'C++ expriment','math2',6),(9,'JAVA1','math2',5),(10,'JAVA2','math2',5),(11,'JAVA3','math2',5),(12,'学习控制器','编程',23);
/*!40000 ALTER TABLE `experiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiment_content`
--

DROP TABLE IF EXISTS `experiment_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experiment_content` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `details` text,
  `experiment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_EXPERIMENT_CONTENT_EXPERIMENT` (`experiment_id`),
  CONSTRAINT `fk_EXPERIMENT_CONTENT_EXPERIMENT` FOREIGN KEY (`experiment_id`) REFERENCES `experiment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiment_content`
--

LOCK TABLES `experiment_content` WRITE;
/*!40000 ALTER TABLE `experiment_content` DISABLE KEYS */;
INSERT INTO `experiment_content` VALUES (2,'哈哈hi i哈纳斯按下时必须',4),(5,'nnkxnnnxnksxnknwknc123',11),(6,'nnkxnnnxnksxnknwknc123754675',11),(8,'nnkxNJNKNKNKNKNKJscsdcknc123754675',10),(10,'编写控制器组件，使用post、get、delete方法的api',12);
/*!40000 ALTER TABLE `experiment_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `format`
--

DROP TABLE IF EXISTS `format`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `format` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `format`
--

LOCK TABLES `format` WRITE;
/*!40000 ALTER TABLE `format` DISABLE KEYS */;
/*!40000 ALTER TABLE `format` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 17:59:21
