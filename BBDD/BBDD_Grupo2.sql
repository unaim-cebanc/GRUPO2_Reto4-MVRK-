CREATE DATABASE  IF NOT EXISTS `data_centers` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `data_centers`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: data_centers
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `consumo_energia`
--

DROP TABLE IF EXISTS `consumo_energia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumo_energia` (
  `id_consumo` int NOT NULL AUTO_INCREMENT,
  `id_sede` int NOT NULL,
  `kwh_servidores` int DEFAULT NULL,
  `kwh_refrigeracion` int DEFAULT NULL,
  `kwh_totales` int DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_consumo`),
  KEY `fk_consumo_energia1_idx` (`id_sede`),
  CONSTRAINT `fk_consumo_energia1` FOREIGN KEY (`id_sede`) REFERENCES `sedes` (`id_sede`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo_energia`
--

LOCK TABLES `consumo_energia` WRITE;
/*!40000 ALTER TABLE `consumo_energia` DISABLE KEYS */;
INSERT INTO `consumo_energia` VALUES (1,7,120,80,200,'2026-01-01 07:00:00'),(2,3,95,60,155,'2026-01-01 08:00:00'),(3,10,140,90,230,'2026-01-01 09:00:00'),(4,1,110,70,180,'2026-01-01 10:00:00'),(5,8,160,100,260,'2026-01-01 11:00:00'),(6,5,130,85,215,'2026-01-01 12:00:00'),(7,2,90,55,145,'2026-01-01 13:00:00'),(8,9,150,95,245,'2026-01-01 14:00:00'),(9,4,105,65,170,'2026-01-01 15:00:00'),(10,6,125,75,200,'2026-01-01 16:00:00'),(11,2,135,88,223,'2026-01-02 07:00:00'),(12,9,100,63,163,'2026-01-02 08:00:00'),(13,4,145,92,237,'2026-01-02 09:00:00'),(14,7,118,72,190,'2026-01-02 10:00:00'),(15,1,155,98,253,'2026-01-02 11:00:00'),(16,10,128,82,210,'2026-01-02 12:00:00'),(17,6,92,58,150,'2026-01-02 13:00:00'),(18,3,148,97,245,'2026-01-02 14:00:00'),(19,8,112,68,180,'2026-01-02 15:00:00'),(20,5,130,78,208,'2026-01-02 16:00:00'),(21,9,142,90,232,'2026-01-03 07:00:00'),(22,6,98,62,160,'2026-01-03 08:00:00'),(23,1,150,95,245,'2026-01-03 09:00:00'),(24,5,120,75,195,'2026-01-03 10:00:00'),(25,3,165,105,270,'2026-01-03 11:00:00'),(26,8,135,88,223,'2026-01-03 12:00:00'),(27,4,96,60,156,'2026-01-03 13:00:00'),(28,2,152,99,251,'2026-01-03 14:00:00'),(29,10,108,70,178,'2026-01-03 15:00:00'),(30,7,132,80,212,'2026-01-03 16:00:00');
/*!40000 ALTER TABLE `consumo_energia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_residuos`
--

DROP TABLE IF EXISTS `control_residuos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_residuos` (
  `id_residuo` int NOT NULL AUTO_INCREMENT,
  `id_sede` int NOT NULL,
  `tipo_residuo` varchar(30) NOT NULL,
  `cantidad_kg` int DEFAULT NULL,
  `metodo_gestion` varchar(30) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_residuo`),
  KEY `fk_control_residuos1_idx` (`id_sede`),
  CONSTRAINT `fk_control_residuos1` FOREIGN KEY (`id_sede`) REFERENCES `sedes` (`id_sede`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_residuos`
--

LOCK TABLES `control_residuos` WRITE;
/*!40000 ALTER TABLE `control_residuos` DISABLE KEYS */;
INSERT INTO `control_residuos` VALUES (1,7,'Electrónicos',15,'Reciclaje especializado','2026-01-01 09:00:00'),(2,3,'Plásticos',25,'Reciclaje','2026-01-01 10:00:00'),(3,10,'Papel/Cartón',40,'Reciclaje','2026-01-01 11:00:00'),(4,1,'Orgánicos',30,'Compostaje','2026-01-01 12:00:00'),(5,8,'Metales',18,'Reciclaje','2026-01-01 13:00:00'),(6,5,'Electrónicos',22,'Reciclaje especializado','2026-01-01 14:00:00'),(7,2,'Plásticos',28,'Reciclaje','2026-01-01 15:00:00'),(8,9,'Papel/Cartón',35,'Reciclaje','2026-01-01 16:00:00'),(9,4,'Orgánicos',32,'Compostaje','2026-01-01 17:00:00'),(10,6,'Metales',20,'Reciclaje','2026-01-01 18:00:00'),(11,2,'Electrónicos',17,'Reciclaje especializado','2026-01-02 09:00:00'),(12,9,'Plásticos',26,'Reciclaje','2026-01-02 10:00:00'),(13,4,'Papel/Cartón',42,'Reciclaje','2026-01-02 11:00:00'),(14,7,'Orgánicos',29,'Compostaje','2026-01-02 12:00:00'),(15,1,'Metales',19,'Reciclaje','2026-01-02 13:00:00'),(16,10,'Electrónicos',24,'Reciclaje especializado','2026-01-02 14:00:00'),(17,6,'Plásticos',27,'Reciclaje','2026-01-02 15:00:00'),(18,3,'Papel/Cartón',38,'Reciclaje','2026-01-02 16:00:00'),(19,8,'Orgánicos',31,'Compostaje','2026-01-02 17:00:00'),(20,5,'Metales',21,'Reciclaje','2026-01-02 18:00:00'),(21,9,'Electrónicos',16,'Reciclaje especializado','2026-01-03 09:00:00'),(22,6,'Plásticos',24,'Reciclaje','2026-01-03 10:00:00'),(23,1,'Papel/Cartón',39,'Reciclaje','2026-01-03 11:00:00'),(24,5,'Orgánicos',33,'Compostaje','2026-01-03 12:00:00'),(25,3,'Metales',23,'Reciclaje','2026-01-03 13:00:00'),(26,8,'Electrónicos',25,'Reciclaje especializado','2026-01-03 14:00:00'),(27,4,'Plásticos',29,'Reciclaje','2026-01-03 15:00:00'),(28,2,'Papel/Cartón',41,'Reciclaje','2026-01-03 16:00:00'),(29,10,'Orgánicos',28,'Compostaje','2026-01-03 17:00:00'),(30,7,'Metales',20,'Reciclaje','2026-01-03 18:00:00');
/*!40000 ALTER TABLE `control_residuos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emisiones`
--

DROP TABLE IF EXISTS `emisiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emisiones` (
  `id_emisiones` int NOT NULL AUTO_INCREMENT,
  `id_sede` int NOT NULL,
  `tipo_emision` varchar(45) DEFAULT NULL,
  `cantidad_toneladas` int DEFAULT NULL,
  `fuente` varchar(45) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_emisiones`),
  KEY `fk_emisiones1_idx` (`id_sede`),
  CONSTRAINT `fk_emisiones1` FOREIGN KEY (`id_sede`) REFERENCES `sedes` (`id_sede`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emisiones`
--

LOCK TABLES `emisiones` WRITE;
/*!40000 ALTER TABLE `emisiones` DISABLE KEYS */;
INSERT INTO `emisiones` VALUES (1,3,'CO2',12,'Consumo eléctrico','2025-01-15 09:32:00'),(2,7,'CO2',8,'Generador diésel','2025-02-03 17:21:00'),(3,1,'CO2',6,'Sistema de refrigeración','2025-02-20 08:10:00'),(4,9,'NOx',4,'Generador diésel','2025-03-05 13:45:00'),(5,5,'CO2',10,'Consumo eléctrico','2025-03-19 10:12:00'),(6,2,'CO2',5,'UPS baterías','2025-04-02 06:55:00'),(7,8,'CO2',9,'Consumo eléctrico','2025-04-17 14:40:00'),(8,4,'HFC',3,'Sistema de refrigeración','2025-05-01 11:20:00'),(9,6,'CO2',7,'Consumo eléctrico','2025-05-23 17:10:00'),(10,10,'NOx',2,'Generador diésel','2025-06-04 05:30:00'),(11,3,'CO2',11,'Consumo eléctrico','2025-06-18 13:22:00'),(12,7,'CO2',6,'Sistema de refrigeración','2025-07-09 10:44:00'),(13,1,'CO2',4,'UPS baterías','2025-07-26 07:35:00'),(14,9,'NOx',3,'Generador diésel','2025-08-11 18:05:00'),(15,5,'CO2',8,'Consumo eléctrico','2025-08-29 15:18:00'),(16,2,'HFC',2,'Sistema de refrigeración','2025-09-10 08:50:00'),(17,8,'CO2',9,'Consumo eléctrico','2025-09-28 12:07:00'),(18,4,'CO2',6,'Consumo eléctrico','2025-10-12 09:31:00'),(19,6,'NOx',3,'Generador diésel','2025-10-30 15:22:00'),(20,10,'CO2',7,'Sistema de refrigeración','2025-11-14 07:40:00'),(21,3,'CO2',10,'Consumo eléctrico','2025-12-03 12:55:00'),(22,7,'CO2',5,'UPS baterías','2025-12-19 17:09:00'),(23,1,'HFC',2,'Sistema de refrigeración','2026-01-07 08:27:00'),(24,9,'CO2',8,'Consumo eléctrico','2026-02-02 14:12:00'),(25,5,'NOx',3,'Generador diésel','2026-03-01 11:00:00');
/*!40000 ALTER TABLE `emisiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id_empleado` int NOT NULL AUTO_INCREMENT,
  `id_empleado_gerente` int DEFAULT NULL,
  `id_sede` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fecha_contrato` date DEFAULT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `id_empleado_gerente_UNIQUE` (`id_empleado_gerente`),
  KEY `fk_empleados2_idx` (`id_sede`),
  CONSTRAINT `fk_empleados1` FOREIGN KEY (`id_empleado_gerente`) REFERENCES `empleados` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_empleados2` FOREIGN KEY (`id_sede`) REFERENCES `sedes` (`id_sede`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,NULL,3,'Carlos','Gómez','2018-03-12'),(2,2,7,'Lucía','Martínez','2019-07-21'),(3,NULL,5,'Andrés','López','2020-01-15'),(4,NULL,9,'Sofía','Ramírez','2017-11-03'),(5,5,4,'Daniel','Torres','2021-06-18'),(6,NULL,6,'Valentina','Castro','2019-09-25'),(7,NULL,8,'Mateo','Rojas','2018-12-05'),(8,8,2,'Isabella','Navarro','2016-04-14'),(9,NULL,10,'Javier','Ortega','2022-02-01'),(10,NULL,1,'Camila','Silva','2020-08-30'),(11,NULL,7,'Alejandro','Morales','2017-05-19'),(12,1,3,'Paula','Herrera','2021-03-11'),(13,NULL,6,'Diego','Vega','2019-01-09'),(14,NULL,8,'María','Campos','2020-10-22'),(15,NULL,2,'Fernando','Molina','2018-07-16'),(16,NULL,9,'Laura','Delgado','2015-06-01'),(17,NULL,4,'Pablo','Suárez','2019-12-12'),(18,NULL,5,'Claudia','Reyes','2021-02-27'),(19,10,1,'Hugo','Cortés','2018-09-10'),(20,9,10,'Adriana','Paredes','2022-04-03'),(21,NULL,6,'Sergio','Fuentes','2017-01-25'),(22,NULL,7,'Elena','Peña','2020-11-14'),(23,NULL,3,'Iván','Acosta','2019-06-30'),(24,7,8,'Natalia','Escobar','2021-09-08'),(25,NULL,4,'Raúl','Bravo','2016-12-19'),(26,NULL,5,'Gabriela','Santos','2020-02-17'),(27,4,9,'Tomás','León','2018-05-05'),(28,NULL,2,'Patricia','Iglesias','2021-07-29'),(29,6,6,'Ricardo','Cano','2017-10-21'),(30,NULL,1,'Verónica','Serrano','2019-04-13'),(31,NULL,7,'Álvaro','Prieto','2022-01-11'),(32,NULL,8,'Marta','Calvo','2018-06-02'),(33,NULL,3,'Rubén','Pascual','2017-08-26'),(34,NULL,10,'Beatriz','Gallego','2021-12-07'),(35,NULL,4,'Óscar','Lara','2019-03-03'),(36,NULL,2,'Cristina','Nieto','2020-05-28'),(37,NULL,6,'Guillermo','Rico','2018-11-15'),(38,NULL,9,'Alicia','Pastor','2022-03-09'),(39,3,5,'Jorge','Marín','2019-08-01'),(40,NULL,7,'Sara','Rubio','2021-01-20'),(41,11,1,'Unai','Manterola','2026-02-03'),(42,12,1,'Unax','Gahona','2025-04-09'),(43,13,1,'Aimar','Garcia','2026-05-13'),(44,14,1,'Aroa','Hernandez','2025-10-06');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala_servidores`
--

DROP TABLE IF EXISTS `sala_servidores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala_servidores` (
  `id_sala_servidor` int NOT NULL AUTO_INCREMENT,
  `id_sede` int NOT NULL,
  `nombre_sala` varchar(45) DEFAULT NULL,
  `num_racks` smallint DEFAULT NULL,
  `superficie_m2` int DEFAULT NULL,
  PRIMARY KEY (`id_sala_servidor`),
  KEY `fk_sala_servidores1_idx` (`id_sede`),
  CONSTRAINT `fk_sala_servidores1` FOREIGN KEY (`id_sede`) REFERENCES `sedes` (`id_sede`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala_servidores`
--

LOCK TABLES `sala_servidores` WRITE;
/*!40000 ALTER TABLE `sala_servidores` DISABLE KEYS */;
INSERT INTO `sala_servidores` VALUES (1,3,'Sala Alfa',12,120),(2,7,'Sala Beta',8,95),(3,1,'Sala Gamma',15,150),(4,9,'Sala Delta',10,110),(5,4,'Sala Épsilon',6,80),(6,2,'Sala Zeta',14,140),(7,8,'Sala Eta',9,100),(8,5,'Sala Theta',11,130),(9,6,'Sala Iota',7,85),(10,10,'Sala Kappa',16,170),(11,3,'Sala Lambda',13,145),(12,1,'Sala Mu',5,70),(13,7,'Sala Nu',18,200),(14,4,'Sala Xi',10,115),(15,2,'Sala Ómicron',12,135),(16,9,'Sala Pi',8,90),(17,6,'Sala Rho',14,155),(18,5,'Sala Sigma',9,105),(19,8,'Sala Tau',11,125),(20,10,'Sala Upsilon',17,190);
/*!40000 ALTER TABLE `sala_servidores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sedes`
--

DROP TABLE IF EXISTS `sedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sedes` (
  `id_sede` int NOT NULL AUTO_INCREMENT,
  `nombre_sede` varchar(45) NOT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  `superficie_m2` int DEFAULT NULL,
  PRIMARY KEY (`id_sede`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sedes`
--

LOCK TABLES `sedes` WRITE;
/*!40000 ALTER TABLE `sedes` DISABLE KEYS */;
INSERT INTO `sedes` VALUES (1,'Sede Aurora','Madrid',1200),(2,'Centro Boreal','Buenos Aires',950),(3,'Edificio Horizonte','Bogotá',1500),(4,'Sede Atlántica','Lisboa',800),(5,'Torre Innovar','Ciudad de México',2100),(6,'Campus Solaris','Barcelona',1750),(7,'Oficina Nébula','Santiago de Chile',900),(8,'Complejo Prisma','París',1600),(9,'Sede Vanguardia','Lima',1100),(10,'Hub Continental','Berlín',1400);
/*!40000 ALTER TABLE `sedes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sistemas_refrigeracion`
--

DROP TABLE IF EXISTS `sistemas_refrigeracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sistemas_refrigeracion` (
  `id_sistema` int NOT NULL AUTO_INCREMENT,
  `id_sala_servidor` int NOT NULL,
  `tipo_sistema` varchar(10) DEFAULT NULL,
  `eficiencia_energetica` varchar(45) DEFAULT NULL,
  `refrigerante` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_sistema`),
  KEY `fk_sistema_refrigeracion1_idx` (`id_sala_servidor`),
  CONSTRAINT `fk_refrigeracion1` FOREIGN KEY (`id_sala_servidor`) REFERENCES `sala_servidores` (`id_sala_servidor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sistemas_refrigeracion`
--

LOCK TABLES `sistemas_refrigeracion` WRITE;
/*!40000 ALTER TABLE `sistemas_refrigeracion` DISABLE KEYS */;
INSERT INTO `sistemas_refrigeracion` VALUES (1,17,'Chiller','A++','R410A'),(2,8,'VRV','A+','R32'),(3,14,'Precisión','A','R134a'),(4,3,'CRAC','A++','R410A'),(5,19,'InRow','A+','R407C'),(6,12,'DX','A','R410A'),(7,5,'Chiller','A++','R32'),(8,16,'VRV','A+','R134a'),(9,7,'Precisión','A','R410A'),(10,11,'CRAC','A++','R407C'),(11,18,'InRow','A+','R32'),(12,2,'DX','A','R134a'),(13,15,'Chiller','A++','R410A'),(14,9,'VRV','A+','R407C'),(15,20,'Precisión','A','R32'),(16,4,'CRAC','A++','R134a'),(17,13,'InRow','A+','R410A'),(18,6,'DX','A','R407C'),(19,10,'Chiller','A++','R32'),(20,1,'VRV','A+','R134a'),(21,17,'Precisión','A','R410A'),(22,8,'CRAC','A++','R407C'),(23,14,'InRow','A+','R32'),(24,3,'DX','A','R134a'),(25,19,'Chiller','A++','R410A'),(26,12,'VRV','A+','R407C'),(27,5,'Precisión','A','R32'),(28,16,'CRAC','A++','R134a'),(29,7,'InRow','A+','R410A'),(30,11,'DX','A','R407C');
/*!40000 ALTER TABLE `sistemas_refrigeracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `id_usuario_admin` int DEFAULT NULL,
  `id_empleado` int NOT NULL,
  `nombre_usuario` varchar(20) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `id_usuario_admin_UNIQUE` (`id_usuario_admin`) /*!80000 INVISIBLE */,
  KEY `fk_usuarios1_idx` (`id_empleado`),
  KEY `dfzgbergthbfvcfdgv_idx` (`id_empleado`),
  CONSTRAINT `dfzgbergthbfvcfdgv` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,NULL,1,'carlos.gomez','4821'),(2,1,2,'lucia.martinez','1934'),(3,NULL,3,'andres.lopez','7502'),(4,NULL,4,'sofia.ramirez','8641'),(5,7,5,'daniel.torres','3257'),(6,NULL,6,'valentina.castro','9184'),(7,NULL,7,'mateo.rojas','6042'),(8,6,8,'isabella.navarro','2719'),(9,NULL,9,'javier.ortega','5380'),(10,NULL,10,'camila.silva','4927'),(11,NULL,11,'alejandro.morales','8675'),(12,NULL,12,'paula.herrera','1043'),(13,NULL,13,'diego.vega','3568'),(14,4,14,'maria.campos','9021'),(15,5,15,'fernando.molina','6750'),(16,NULL,16,'laura.delgado','4302'),(17,NULL,17,'pablo.suarez','5983'),(18,NULL,18,'claudia.reyes','2147'),(19,NULL,19,'hugo.cortes','7804'),(20,NULL,20,'adriana.paredes','6315'),(21,NULL,21,'sergio.fuentes','2459'),(22,NULL,22,'elena.pena','8976'),(23,NULL,23,'ivan.acosta','3642'),(24,9,24,'natalia.escobar','7501'),(25,NULL,25,'raul.bravo','4893'),(26,8,26,'gabriela.santos','1907'),(27,NULL,27,'tomas.leon','8324'),(28,NULL,28,'patricia.iglesias','5068'),(29,10,29,'ricardo.cano','6731'),(30,2,30,'veronica.serrano','9410'),(31,NULL,31,'alvaro.prieto','1825'),(32,NULL,32,'marta.calvo','3749'),(33,NULL,33,'ruben.pascual','8602'),(34,NULL,34,'beatriz.gallego','4250'),(35,NULL,35,'oscar.lara','7138'),(36,NULL,36,'cristina.nieto','5947'),(37,3,37,'guillermo.rico','2684'),(38,NULL,38,'alicia.pastor','8301'),(39,NULL,39,'jorge.marin','1095'),(40,NULL,40,'sara.rubio','4762'),(41,11,41,'unai.manterola','1234'),(43,13,43,'aimar.garcia','1234'),(44,14,44,'aroa.hernandez','1234');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-16  8:46:33
