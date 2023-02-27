-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bid` (
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `itemid` int DEFAULT NULL,
  `amount` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES ('2023-02-27','2023-02-28',3,90),('2023-02-27','2023-02-28',7,5000),('2023-02-27','2023-02-28',7,7000),('2023-02-27','2023-02-28',2,70000),('2023-02-27','2023-02-28',2,90000),('2023-02-27','2023-02-28',20,1100),('2023-02-27','2023-02-28',3,200);
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyers`
--

DROP TABLE IF EXISTS `buyers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyers` (
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(15) NOT NULL,
  `phne` varchar(10) NOT NULL,
  `Buyer_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Buyer_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `password` (`password`),
  UNIQUE KEY `phne` (`phne`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyers`
--

LOCK TABLES `buyers` WRITE;
/*!40000 ALTER TABLE `buyers` DISABLE KEYS */;
INSERT INTO `buyers` VALUES ('Akku','akku@gmail.com','12','12345',1),('Anubhavi','an@gmail.com','00','1789',2),('anu','anu@gmail.com','67','8909',3),('khushbu','khushbu@gmail.com','54','1920',4),('shalu','shalu@gmail.com','12345','1234567890',5),('Saurav','saurav@gmail.com','22','2466',6),('Hemensan','hemensan@gmail.com','1111','7856',8),('shalu','s@gmail.com','11','11',9),('ranu','ranu@gmail.com','90','563455',11),('uplabdhi','up@gmail.com','44','7898965446',12);
/*!40000 ALTER TABLE `buyers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispute`
--

DROP TABLE IF EXISTS `dispute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dispute` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  `buyer_id` int DEFAULT NULL,
  `item_id` int DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'OPEN',
  PRIMARY KEY (`id`),
  KEY `seller_id` (`seller_id`),
  KEY `buyer_id` (`buyer_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `dispute_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`Seller_id`),
  CONSTRAINT `dispute_ibfk_2` FOREIGN KEY (`buyer_id`) REFERENCES `buyers` (`Buyer_id`),
  CONSTRAINT `dispute_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `items` (`itemid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispute`
--

LOCK TABLES `dispute` WRITE;
/*!40000 ALTER TABLE `dispute` DISABLE KEYS */;
INSERT INTO `dispute` VALUES (1,'2020-02-01','dfdg',1,2,1,'Resolved'),(2,'2023-02-25','dfdg',1,2,1,'Resolved'),(3,'2023-02-26','dsfd',2,3,1,'Resolved'),(4,'2023-02-27','about',3,2,4,'Resolved'),(5,'2023-02-27','about',3,2,4,'Resolved'),(6,'2023-02-27','price_issue',2,3,4,'Resolved');
/*!40000 ALTER TABLE `dispute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemid` int NOT NULL,
  `itemName` varchar(20) DEFAULT NULL,
  `MRP` double(7,2) DEFAULT NULL,
  `MFGDate` date NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'cellPhone',7000.00,'2020-02-03','electronics'),(3,'pen',100.00,'2019-02-03','other'),(4,'habdset',600.00,'2019-05-06','electronics'),(7,'stone',75000.00,'1789-01-02','antiques'),(10,'AC',50000.00,'2020-03-04','electronics'),(20,'jacket',1000.00,'2023-01-01','clothing'),(21,'Top',2000.00,'2019-08-03','clothing'),(22,'bed',3000.00,'2022-04-06','rest');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellers`
--

DROP TABLE IF EXISTS `sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sellers` (
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phne` varchar(10) NOT NULL,
  `Seller_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Seller_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `password` (`password`),
  UNIQUE KEY `phne` (`phne`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellers`
--

LOCK TABLES `sellers` WRITE;
/*!40000 ALTER TABLE `sellers` DISABLE KEYS */;
INSERT INTO `sellers` VALUES ('gaurav','gaurav@gmail.com','98','3244',1),('riyansh','ray@gmail.com','12','54657',2),('sh','g@gmail.com','55','55',3),('Anubhavi','anu@gmail.com','77','32456576',4),('efgh','efgh@gmail.com','5678','543217890',5),('shalu1','shalu1@gmail.com','33','98767890',6);
/*!40000 ALTER TABLE `sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sold`
--

DROP TABLE IF EXISTS `sold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sold` (
  `itemid` int DEFAULT NULL,
  `amount` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sold`
--

LOCK TABLES `sold` WRITE;
/*!40000 ALTER TABLE `sold` DISABLE KEYS */;
INSERT INTO `sold` VALUES (7,3000);
/*!40000 ALTER TABLE `sold` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-27 23:03:19
