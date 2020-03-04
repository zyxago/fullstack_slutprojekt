CREATE DATABASE  IF NOT EXISTS `erikh-recept` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `erikh-recept`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: its.teknikum.it    Database: erikh-recept
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Food category';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('Fisk'),('Kött'),('Lactos');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_recipe`
--

DROP TABLE IF EXISTS `category_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_recipe` (
  `recipe_id` int(10) unsigned NOT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`recipe_id`,`category`),
  KEY `category_idx` (`category`),
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `recipe_category_id` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Recipe categories';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_recipe`
--

LOCK TABLES `category_recipe` WRITE;
/*!40000 ALTER TABLE `category_recipe` DISABLE KEYS */;
INSERT INTO `category_recipe` VALUES (7,'Fisk'),(7,'Kött');
/*!40000 ALTER TABLE `category_recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of comment',
  `text` varchar(2048) NOT NULL COMMENT 'Comment Text',
  `parent_id` int(10) unsigned NOT NULL COMMENT 'id of parent: comment or recipe',
  PRIMARY KEY (`id`),
  KEY `parent_comment_id_idx` (`parent_id`),
  CONSTRAINT `parent_comment_id` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `parent_recipe_id` FOREIGN KEY (`parent_id`) REFERENCES `recipe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Comment on an recipe or another comment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `ingredient` varchar(128) NOT NULL COMMENT 'Name of ingredient',
  PRIMARY KEY (`ingredient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Recipe Ingredient';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES ('ägg'),('fläsk'),('mjölk'),('Salad');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient_recipe`
--

DROP TABLE IF EXISTS `ingredient_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient_recipe` (
  `recipe_id` int(10) unsigned NOT NULL,
  `ingredient` varchar(45) NOT NULL,
  `amount` int(10) unsigned NOT NULL,
  `measurement` varchar(45) NOT NULL,
  PRIMARY KEY (`recipe_id`,`ingredient`),
  KEY `messurment_idx` (`measurement`),
  CONSTRAINT `messurment` FOREIGN KEY (`measurement`) REFERENCES `measurement` (`measurement`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `recipe_ingredient_id` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Recipe Ingredients';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_recipe`
--

LOCK TABLES `ingredient_recipe` WRITE;
/*!40000 ALTER TABLE `ingredient_recipe` DISABLE KEYS */;
INSERT INTO `ingredient_recipe` VALUES (7,'fläsk',300,'g'),(7,'Salad',100,'g');
/*!40000 ALTER TABLE `ingredient_recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instruction_recipe`
--

DROP TABLE IF EXISTS `instruction_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instruction_recipe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Instruction ID',
  `recipe_id` int(10) unsigned NOT NULL COMMENT 'Recipe ID',
  `instruction_order` int(10) unsigned NOT NULL COMMENT 'Instruction order',
  `instruction_text` varchar(512) NOT NULL,
  PRIMARY KEY (`id`,`recipe_id`,`instruction_order`),
  KEY `recipe_instruction_id_idx` (`recipe_id`),
  CONSTRAINT `recipe_instruction_id` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='Recipe instructions';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruction_recipe`
--

LOCK TABLES `instruction_recipe` WRITE;
/*!40000 ALTER TABLE `instruction_recipe` DISABLE KEYS */;
INSERT INTO `instruction_recipe` VALUES (21,7,0,'instruction1'),(22,7,1,'instruction2'),(27,7,4,'asdadas');
/*!40000 ALTER TABLE `instruction_recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurement`
--

DROP TABLE IF EXISTS `measurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `measurement` (
  `measurement` varchar(45) NOT NULL COMMENT 'Food messurment (dl, l, ml, kg, g, tsk)',
  PRIMARY KEY (`measurement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Messurments';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurement`
--

LOCK TABLES `measurement` WRITE;
/*!40000 ALTER TABLE `measurement` DISABLE KEYS */;
INSERT INTO `measurement` VALUES ('cl'),('dl'),('g'),('hg'),('kg'),('l'),('ml'),('msk'),('tsk');
/*!40000 ALTER TABLE `measurement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `writer_id` int(10) unsigned NOT NULL,
  `img` blob,
  `info` varchar(1024) DEFAULT NULL,
  `stats_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `writer_id_idx` (`writer_id`),
  KEY `stats_id_idx` (`stats_id`),
  CONSTRAINT `stats_id` FOREIGN KEY (`stats_id`) REFERENCES `stats` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `writer_id` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Recipe';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (7,'test',1,_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0@\0\0\0@\0\0\0�iq\�\0\0�IDATx�\�y�\�U�\�?\���;֭\�֐T�\�@&BL�$�H;�J\�F\\�>_�ԥO��z\�\�\����\�\�\�m�k\�a	\r\�N���\0\rQ�I$	d�RUI\�u\�\�p��\��Ul�W�>k�\�\�[�i\�\�\�g\�}\�?\���PC�\�\��늿��O�0��ᕼn�l!7\�X�,����L\�����\�KD\�\�\�A��>/�rEO�9[(\�\�쫆ϴ|O\�8�\��\�E��Y��o��,M� �\�[̇�J��w�/��?X��\�%\�\�\�9����\�]fW�X)�]]\�k���\�3-\�\�2.�r\�\�\�\�\�z\�_.Kk�ۭ=v���x��x[\�\n���|`\�\"\n��\\�\���W�~�ܷw\�p���\�>�j\�uyHhF\� �kr��\���m\�\�Q�T��.�\�ύ�6\�s\�\��-_�����\�\�Q��70�&�3�3��\�oX\��\�\'�?Xx�+�\�G�_x�ݗ�\�~顱\�f��iD�j\�W$�x`\�\�\�n�{\�<���A:��\\Z-\�Ϋ�~\�\�_��C���6f������F+x���\��#\�\�\�\�z�\�z\�-`\�ڢ�=s��z\"\��!mAx\�y\�Hk�ge�;��=0\�7\�d�=7�^��/��g*¶ۘ\�&z>�F�\��?�\�)�\�\��{�{\�i��5y��$ֵ-\�\�\��\�\�U_�\�\�ܦ\r!a�\��5��29��\�7\��*�o_�{^I�HbL#F\�*L\�`�\��}��~�q[gٻ�ƻZ�\���m��i<o��)��C�o[ww�\�m\�)o��w֮��)@�co\r\�T\�}\�)�(P6I1s\Z5o��\��q͟�\��\�\�^��\�\�o\�꿋L˟U�6K<D�MykG���*ޢa�	K ��f�\nmWog)_��C_\�D.]zb��\\\�\��J(�\nk��I{�\�Re͎�1�i\�\��\\�\'C�z\�s����3R\0xҁ\0\'\0 ݧ\�`-�)$\n4�lK�28���\�}\�,\�\�ߘ��\�/\�\�+ӹd�_��v�WË�u\�m\�Uv^Z�K�#\�[�|V�%!\�\��F\�7*�6sb�Lm\�_6�\����\�\�\�K�bw�|ک�\�6\�\�Z]Q�k\�5\��G#5~�\��\�_,\�ڸ\� rS`J �\�aE����\�\�߾\�ORhI\�\�H�v\�\�\����\�ܵa�\�ʏ~{��U|}i�\�a3��s\��\�{\�\�J\�\�_� 8nx6N\�Q\�\�L��FZ\�d�\'�\�{��vl0���s�5\����Q�`,`A+\�&�.7��A�k�O�8\�\�\�~�@DD$\�Z�@�|\�3\�\�\�\�-.�\�\'n��e�Ϲd��i��Bs\�p/o{�KK\�]\\\�\�!\���Ad\�n4րj\�\���\�\�\�nk\���g�\�r��!Ԋ�\�%\�8Eh\rF�1`4\�(�[\� Z�	aA\n	~\�t[\0\�\�\�\�hXP	4\"h%$�1?��~ޣc �(<�<*͏�\�o�\�õ}\'+��\�\�~ǐ^�̫���F^��ww��殺\�\�Z\�\�2\�/���\�8\�\�f\����\�\�T�Nz\�l!\�&)�J>=R�e�J�\�b倄Xa�\�jC���\Z$sM\�s�eK��U�c)���\�:U�u\�\�\�4����Q\�2V]�w\��\�\�\�y\�KtwI���\�o\�\�\�j~غL\��\�:;n%���-��\�{�\�~\�\�\rW�\��\��/���\0�!U\�]q�m&LH�\�w\Z#Op��\\\\���B	\�l�\�O���u\�\�׽��߶.�\�%�)���h\�\�H\nQ�z�\�羞��^<�MJ]\�,�POAe 휡6\'P\�$\�\��w�y�{�έ��oZ\�\�SV.ȱ�\�_;;\Z�=#��\�r�/A�\�uWvy+����\�V�\�\Z圩R�S\�\��O���D \�2\Z�FV����w<�?׫c%\�\�\�!W�V���bp\�\�ࡔ�\��)P�\�}���>P\�_oR���Λ�\� \�&�y�Ă\�2nV����\�<\�|䫄9ȕ=�z*\�\�m\�~Yu\�\�G\�~x�����\�Ҽ\�n�en�i\r*u\�T��.,Y㮱\�_:kk�5<x�\�>`\�嗄���3K��\�\�\�/\�\�|�RbU�����\�@_B\�\�\�\�\�\�����v/�$��\�*aAZ\�}�+\�oX\�t+ⱉ6;wv�aKٚGT�l=�\�\�\������.�xY\�`b7\�*v�Di��\�=\�\�e$X�@%i�<�D(�劸�Gw\�X\�\�\�\0w\�\�\���9m�\�tv�O~j=]�ș!�d\�p\�\�癛L9g{��[�N0/���$U\�z\�qsEǐx�7f�hʫ\�TEJ\��Qm2G�:��\�\�,��/,h\�\0=r\��o���לV-�$�|E\�\\!��ؐ��\��\�3��l��\�\n\�\\\�\� \r(/�~H�]-^~\�\"(	g�2�l)�i�\�K�i��d�b�q\�(##\�\�|���=a\��E�4�\�\��/�\Z	Zm�\�<vZ\0�m (#��Ia\�\�\�7d\�[\�C���P�́�z4W�e/o�:����\'��Y:�\�X�h=�০X\��A\�y��r\�K\�q\�?=\�5ﯺ�\�ʙ��9@�\�R�g-cA�gؔ��1_\�?-\0t\�\��ZiW\'0��!\��BhAH=7+�\�\�uk�:{���\�\�	k�5fZ�\��sljj\n�A0K\�:\�\�\�\�5҆G�\�b�\�r\rD\�qᏅd\�dK\�9@�`\�h\�h\�\�i1BF�|\�\�(��n��)\Z\�\0B��<��\\\�mc��5@�Z\�9(��\0\�b�$h)\n\�J@A��ؼ��ɱ�V0�D\�)��\�ܽɜ��*�M��i��\�%�e�\�J��s�\��׈\�j\�\�*�^\�G�\�\0\�r3o-�ƶ-w\�\\\�\�9��.H�|>[��\�-�����\�kA���%Ks\�\����+\�\��TNd\�d6�\�\�*�U�f\�\�!\�\�̙z�\'\Z�@Q�\�\�5�|6\�\�~\�gr�d����?\�A�\�\�\Z\�\�RY�Ê���\�4���|��Hd&\�)\�^X\�\�C\�\�\�+Fn$�4��h)�l\�H\��u�\�l�\r\�lb1-��,�mH\�%\�[\Zu530sZ\0ĩ�\�ߌ���*�&r\��\n\�\�\�d�P�_�U�\�K\�\�ޔ����v��\�cz\�\�¤ę����\�o�Y�|��\�3T,�\�u>�\�_�����Ii��.\�i\ZLd1�\�\�Ю&g,p\����oM\�\�|�a\'��77��N\0���~^�\�\����\�A����\r\�<�u $sc1C��0\�\�#z6\�!bN\"L|A��ҧ��\�l�ǣ�\�\��\�ƣ��\�%X\�`\"A2\rӣ�cS��\�\�ҷ�<i6\�D��wtx��)ϯ���_���~��\�	&���M�\�m�m�ߦ�c��\�1�X\��!\��8��\Z0R\"�@�Y�\�b����<�\r&5\���\�\�\�\�\�s@]��7U7�\n\�*z�z$c����t�\�]�>k\�#���}N��\�S��؂\�\�\�\�pZ\0ԏGy��\�z�o\�5=ũ�g]R\���圚\�\�]��u�UE���(\n/��\��\"Kj�v\�\�fSE��\�\�\�/�b�\�|���,�m��\�9�}\�\�������\�o�	\�#~�\�7~}ꍷ\�K\�\�/\�S�\��4�$\��8���\�\�VX�=�|�O�\�&�\�+�uV$m���\r\�Uˆ\��\�\������J}\�9A/\0%\�����S@{\�Q7|�S�o\��p��%6�\\\�[wDYB��P#\�E)�!\r(H�kwʹa&�\�j\�f,��y\�s�KC\�\��G۷8��!�M��;5��)i�\�ۯu>��DCl�\�u딨)�\�P�NQ\�@�!J��@3�F&�0��\�HY�x\'�\Z��M�=Q�zﴞ��Wnh��hL[F�G(r����\0\�~\�X[(\�gB9�&�S&�P\�0�´�Y\r\r\r\r3�*hf\�\�x\�JN@xB�S��\�[�\����ll¢k�t>\�Ob�\�H�,�\��T�\n^��$�[\r��\�{��fLM\"\�	���{-\�o`F\�n���\�%A�`�q�DP�l>#X\�\�\�\r$-��~r��\�XJz$�HS-x�S�\���ptJ,��ŢbQ�p�u)j�]8\�ga-T\r,6 R0)�\ZX�J����2\�a!�2iV �\�\��n�\�\���\�f�\�S`b��<6�\n��t*�?%����Uǅ\�Η���9V@(�%x\�\�\�H�;W2p�\�Jh\\�\�KW�ٵF8�\�\Z�-~޲vmHKz�\\}{�\�U;�bsh\�9�eQ���i������\�C����\"�\��\0K�i\��ϔV\�i&A^@��5S$h:�\nY���˷��Wl+Pnj\�1\�D��ٻ\�ү\�Ӹ\� �T�~\0�\�_�~U\� r�Ե\�\�m�\�\�i6l\�34�ͨę�\':�c@XT\�p\�b.{EWFd,T�\n|\�`74G�\�㓻\��\��c\�\�=yq�/���\�\�oO�h7ŕ+���P@�\0\�\�\��/B���\�|��-\�<\�`q\�   +ec|�\�$�\\	\ne\�!ȃ\�a��nI:\'�\�O�凢?],\�{g��\�\�))��\0�s\'\�\�\�\�Β\�z�!\�\�PꄰA�E뺹\�aEj\�\��,B���GWY\�\���\0: ,��\�=\�B \���چ�<\��7Դx\�z�6\0=2\�(EgO%���q��Y?N��]e.��\�Ȉr׈�_\'�\�\�\�)iJ��K3��\���U�ґ�\�˂�\�1�\�b.\���W=c\0�`QOE.\����C�9�N$4A����|\�K͌�\�Red��kn���<g{\'a.�\r�\0\�\��	ē�B\\\��1CR\�\\\�%�Fj�朂|F\0x�q�[\�\�\�\���6w\�VC\�Ȋ\�R\�3XfjN�R\�ͬv\�\��\��O�<�\�ޓ\0�vN�����b���Li�-׎\�v�\�\�s8��7\���\'F�J\���7��ıG#�\�7��kBg\��=�zY7�\�k�tK��e\�D�у�j5�\�U�����7�swĕ�\�d���w6wO)yᚒ|����\�\0\�\��K�=)�\\����7αg\�fr\�8kP*V\r<�\'\�\����\�ϣU7xB\�Qv;B\�\n�Mj#5���9\���N\�9I0P\"W�{�;o�U?�XV-\�h��\�r�6\0���{%� \nP,x�\�27|�Α\�\�S07�9:È\�c\n�ٌ�\�\��	�}f�\�\Z�\�4�\��O\��\�WT(�|\�mb�iX�b\�`~Ǒ�\�\���d�ZΟ2��\�\�Ȏ�\�+��^tn�\�\���ł�>�bUM�K�?�p\�\�=\�\�N5��&�����i\n\'zv�8��F`f�ةY�\�R?<\�?|�\�k/\�r�\�Lʏ~R\��ۢ�uKw�\�ޚ\�f\���\�[.Z=xJu\0�Ī����\�\�\�m��I�]ԟ{Ǖ/)�Oh\�k�37J��z���U�>_�-��\�q׮�[��\'/�п$\�9\"�\�O+�*�\�?hr�)�y\�?|�ɫ/(�f�� x<�S20�y䐒\�//��X��h���\�\�̫�S\�\�Mf�f�hfJܸcS\�ܳW\�\�g�\\��2�Q\�,63gӂ�����G��ظ��\\\�g�t8tKA)\�)��\�\�Oq��\�\�9*\�@1[2snÔ\�\�#\�{\\��/\�[{\��p㧇\�ԖS\�\�k�\�Y�Y\����16�\��a�\�`\���\��\����P0X\�\�Zp]\�/\�\�a\���u�\�{�Av\�o��_\�;.�\�B,`T��f$�m�[D\\����5�&*�\��U�\�S���;���}w{\�}/g,\��/M\�3�X\�-t�P̚��֕�m%�f\\A`OP\�RX\�\�u���p\�¬r\�p\�\�o\�&\'�&bvnS�?e\�y>`~ΘJ\�\�B��\rWV\�a��������A5p�lG\�B�Gf�\'�\�;\�.qZ\�C�hy0\n�0\'\\\�x %k����\�>ñ�N\�\�\�M3�}\�\0<\Z�3\�h^�ܴ�HO�`�!6\�\�\�\"J¼�i\r�(���W�q�\��R@\�����\n����\�2E�=\�er\"\�w\�P.H\�?\Z\�{��?�P�\���\���\��~;&��x�Ȩz\��x�X��T\�vbX�F �p\���50iaV@[B]@#�\�\��1-\�Y@��åV�$�`b<\�?���e\�h27\�2\�k*��^\\s�8%\�o	��\0��)=�\0�$5����\\\�yg���m�h ��\�G$�zl\�3��\�\�&�����-\�\0\�g�\�6�G@�%)\�M+n���\rŏ\'�\Z�\�o\�&\�\�\�C��7/XCF8�\Z\0(��r`�	x6���\�\�tMu���G�F�\�\�W�=q\�c1gV\�&hq�1��[%�F\�\�jY\�-K;��#h\'�!�\�\�n\0�\�=8�oᶑ�\�\�or\�J\��X=�e`5�\nXT2E�d�7;ԯ\�\�>Е)�\"Cw�f/�A�\�gp�\�_\�]��l(��\�gמ�����G\�=�ڑzjf\�ZEʚ(�&јD[�O4��\�R\���\�\�;�ܪ\�@��=��x\�DS�o6MFfۓ\�\�I\��\��p�9�00\�8�g\�\��8���$�K8�;̾w\0yOP%�+yyR�\�T�F��X\�ci�Y�\�X<��\'�0�\�X,\�\"��d�\rH@KQO�Yp�Yo\r2��$0Ld\�\'�\Z��]\0\�\�r8ǲ\�֖��O�^\�>-\'\�oaT\� ?��\�\��\"]�\�DL��$;�\� ꗔӿt^�:\�\���b\�\�\�5_\�\0\0\0\0IEND�B`�','test text',14);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `recipe_full`
--

DROP TABLE IF EXISTS `recipe_full`;
/*!50001 DROP VIEW IF EXISTS `recipe_full`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `recipe_full` AS SELECT 
 1 AS `id`,
 1 AS `writer_id`,
 1 AS `img`,
 1 AS `info`,
 1 AS `title`,
 1 AS `likes`,
 1 AS `repports`,
 1 AS `writer`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stats` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of stats',
  `likes` int(11) NOT NULL COMMENT 'Likes on a post',
  `repports` int(10) unsigned NOT NULL COMMENT 'Repports on a post',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='Stats of a post';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
INSERT INTO `stats` VALUES (14,1,1),(15,0,0),(16,0,0),(17,0,0);
/*!40000 ALTER TABLE `stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of user',
  `name` varchar(45) NOT NULL COMMENT 'Users name',
  `hash` varchar(256) DEFAULT NULL COMMENT 'Hashed version of password',
  `oauth_id` int(11) DEFAULT NULL COMMENT 'OAuth ID from github',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `oauth_id_UNIQUE` (`oauth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='User Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_likes`
--

DROP TABLE IF EXISTS `user_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_likes` (
  `user_id` int(10) unsigned NOT NULL,
  `stats_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`stats_id`),
  KEY `stats_id_idx` (`stats_id`),
  CONSTRAINT `user_likes_stats_id` FOREIGN KEY (`stats_id`) REFERENCES `stats` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_likes_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_likes`
--

LOCK TABLES `user_likes` WRITE;
/*!40000 ALTER TABLE `user_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_repports`
--

DROP TABLE IF EXISTS `user_repports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_repports` (
  `user_id` int(10) unsigned NOT NULL,
  `stats_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`stats_id`),
  KEY `user_repports_stats_id_idx` (`stats_id`),
  CONSTRAINT `user_repports_stats_id` FOREIGN KEY (`stats_id`) REFERENCES `stats` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_repports_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_repports`
--

LOCK TABLES `user_repports` WRITE;
/*!40000 ALTER TABLE `user_repports` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_repports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'erikh-recept'
--

--
-- Dumping routines for database 'erikh-recept'
--
/*!50003 DROP FUNCTION IF EXISTS `addCategory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`erikh`@`%` FUNCTION `addCategory`(category_name VARCHAR(64), recipe_id INT(11)) RETURNS int(11)
BEGIN
	IF NOT EXISTS (SELECT * FROM category WHERE category.name = category_name) 
    THEN INSERT INTO category VALUES(category_name); 
    END IF;
    INSERT INTO category_recipe VALUES(recipe_id, category_name);
RETURN last_insert_id();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `addIngredient` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`erikh`@`%` FUNCTION `addIngredient`(recipe_id INT(11), ingredient_name varchar(64), amount INT(11), measurement VARCHAR(64)) RETURNS int(11)
BEGIN
	IF NOT EXISTS (SELECT * FROM ingredient WHERE ingredient.ingredient = ingredient_name) 
    THEN INSERT INTO ingredient VALUES(ingredient_name); 
    END IF;
    INSERT INTO ingredient_recipe VALUES(recipe_id, ingredient_name, amount, measurement);
	RETURN LAST_INSERT_ID();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `addInstruction` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`erikh`@`%` FUNCTION `addInstruction`(recipe_id INT(11), inst_order INT(11), instruction_text VARCHAR(512)) RETURNS int(11)
BEGIN
    INSERT INTO instruction_recipe VALUES(null, recipe_id, inst_order, instruction_text); 
	RETURN last_insert_id();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recipe_attached_data` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`erikh`@`%` PROCEDURE `recipe_attached_data`(IN id INTEGER)
BEGIN
SELECT * FROM ingredient_recipe WHERE recipe_id = id;
SELECT * FROM category_recipe WHERE recipe_id = id;
SELECT * FROM instruction_recipe WHERE recipe_id = id ORDER BY instruction_recipe.instruction_order;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `recipe_full`
--

/*!50001 DROP VIEW IF EXISTS `recipe_full`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`erikh`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `recipe_full` AS (select `recipe`.`id` AS `id`,`recipe`.`writer_id` AS `writer_id`,`recipe`.`img` AS `img`,`recipe`.`info` AS `info`,`recipe`.`title` AS `title`,`stats`.`likes` AS `likes`,`stats`.`repports` AS `repports`,(select `user`.`name` from `user` where (`user`.`id` = `recipe`.`writer_id`)) AS `writer` from (`recipe` join `stats`) where (`recipe`.`stats_id` = `stats`.`id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-04 16:13:05
