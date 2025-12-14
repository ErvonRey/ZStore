CREATE DATABASE  IF NOT EXISTS `dbm_finalproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbm_finalproject`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbm_finalproject
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(45) NOT NULL,
  `prod_price` double NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (10000,'Skittles Original',62),(10001,'Coolers Purified Drinking Water 1000ml',25),(10002,'Coolers Purified Drinking Water 500ml',15),(10003,'Nature\'s Spring 1000ml',25),(10004,'Lucky Me Pancit Canton Original 55g',28),(10005,'Lucky Me Pancit Canton Spicy 55g',28),(10006,'555 Sardines in Tomato Sauce 155g',27.5),(10007,'555 Sardines in Chili Sauce 155g',27.5),(10008,'Century Tuna Flakes in Oil 180g',72),(10009,'Century Tuna Hot & Spicy 180g',75),(10010,'C2 Green Tea Jasmine 500ml',40),(10011,'C2 Lemon Tea 500ml',40),(10012,'C2 Peach Tea 500ml',40),(10013,'Coca-Cola 1.5L',70),(10014,'Pepsi 1.5L',70),(10015,'Sprite 1.5L',70),(10016,'Royal Tru-Orange 1.5L',70),(10017,'RC Cola 1.5L',55.5),(10018,'Tang Orange 500g',65),(10019,'Tang Lemon 500g',65.5),(10020,'Tang Mango 500g',65.5),(10021,'Selecta Ice Cream 1L',150),(10022,'Bear Brand Powdered Milk 150g',98.5),(10023,'Bear Brand Powdered Milk 300g',195),(10024,'Ovaltine 3-in-1 25g',7.5),(10025,'Ovaltine Powder 400g',140),(10026,'Rebisco Crackers 100g',20),(10027,'Rebisco Cream Sandwich 45g',15),(10028,'Piattos Cheese 55g',22),(10029,'Piattos BBQ 55g',22),(10030,'Oishi Prawn Crackers 70g',25),(10031,'Oishi Choco Mallows 70g',28),(10032,'Oishi Pillows Choco 70g',28),(10033,'Jack n Jill Chiz Curls 55g',22),(10034,'Jack n Jill Piattos Sour Cream 55g',22),(10035,'Magnolia Fresh Milk 1L',105.5),(10036,'Magnolia Butter 227g',95),(10037,'Anchor Butter 227g',140),(10038,'Gardenia Bread White Loaf',60),(10039,'Gardenia Pandesal Pack',50),(10040,'Purefoods Chicken Hotdog 450g',120),(10041,'Purefoods Corned Beef 150g',85),(10042,'Argentina Beef Tapa 150g',100),(10043,'San Miguel Pale Pilsen 330ml',45),(10044,'Safeguard Soap 90g',27),(10045,'Palmolive Soap 90g',22),(10046,'Colgate Toothpaste 100g',65),(10047,'Closeup Toothpaste 100g',60),(10048,'Pepsodent Toothpaste 100g',58),(10049,'Blue Bay Hand Sanitizer 250ml',80),(10050,'Dettol Antiseptic 175ml',120),(10051,'Surf Laundry Powder 1kg',160),(10052,'OMO Laundry Powder 1kg',155),(10053,'Tide Laundry Powder 1kg',165),(10054,'Silver Swan Soy Sauce 1L',48),(10055,'Silver Swan Vinegar 1L',48),(10056,'Mama Sita’s Soy Sauce 1L',120),(10057,'Mama Sita’s Bagoong 200g',85),(10058,'Mama Sita’s Spaghetti Sauce 250g',35.5),(10059,'Jolly Spaghetti Sauce 250g',35.5),(10060,'Lucky Me Instant Mami Chicken 55g',12),(10061,'Lucky Me Instant Mami Beef 55g',12),(10062,'Nissin Instant Mami Chicken 55g',12),(10063,'Nissin Instant Mami Beef 55g',12),(10064,'Nissin Yakisoba 75g',32),(10065,'Nissin Cup Noodles Chicken 65g',18),(10066,'Nissin Cup Noodles Beef 65g',18),(10067,'Oishi Choco Cracklets 70g',28),(10068,'Oishi Pillows Ube 70g',28),(10069,'Oishi Pillows Mango 70g',28),(10070,'Oishi Mini Cracklings 60g',25),(10071,'Piattos Sour Cream & Onion 55g',22),(10072,'Piattos Cheese 55g',22),(10073,'Jack n Jill Chiz Curls BBQ 55g',22),(10074,'Jack n Jill Chiz Curls Cheese 55g',22),(10075,'Rebisco Fudgee Barr Chocolate 35g',15),(10076,'Rebisco Fudgee Barr Ube 35g',15),(10077,'Ovaltine 3-in-1 25g',7.5),(10078,'Ovaltine Powder 400g',140),(10079,'Milo 3-in-1 25g',7.5),(10080,'Milo Powder 400g',150),(10081,'Tang Pineapple 500g',65),(10082,'Tang Lemon 500g',65.5),(10083,'Tang Mango 500g',65.5),(10084,'Lipton Iced Tea 500ml',32),(10085,'Selecta Mini Cups Chocolate 100ml',35),(10086,'Selecta Mini Cups Ube 100ml',35),(10087,'Selecta Choco Classic 1L',150),(10088,'Selecta Ube Classic 1L',150),(10089,'Magnolia Butter 500g',180),(10090,'Magnolia Fresh Milk 1L',105.5),(10091,'Anchor Butter 500g',260),(10092,'Gardenia Ensaymada Pack',55),(10093,'Gardenia Ensaymada Regular',15),(10094,'Gardenia Loaf Bread Wheat',60),(10095,'Gardenia Loaf Bread White',60),(10096,'Purefoods Corned Beef 150g',85),(10097,'Purefoods Corned Beef 200g',115),(10098,'Purefoods Chicken Hotdog 450g',120),(10099,'Purefoods Chicken Hotdog 1kg',240),(10100,'Argentina Beef Tapa 150g',100);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-14 16:32:22
