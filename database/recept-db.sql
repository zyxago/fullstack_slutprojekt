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
INSERT INTO `category` VALUES ('Fisk'),('K√∂tt'),('Lactos');
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
INSERT INTO `category_recipe` VALUES (7,'Fisk'),(7,'K√∂tt');
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
INSERT INTO `ingredient` VALUES ('√§gg'),('fl√§sk'),('mj√∂lk'),('Salad');
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
INSERT INTO `ingredient_recipe` VALUES (7,'fl√§sk',300,'g'),(7,'Salad',100,'g');
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
INSERT INTO `recipe` VALUES (7,'test',1,_binary 'âPNG\r\n\Z\n\0\0\0\rIHDR\0\0\0@\0\0\0@\0\0\0™iq\ﬁ\0\0èIDATxú\Ìöyî\›Uï\Ô?\Á¸Ü;÷≠\È÷êTí\ @&BLê$ÇH;ÅJ\€F\\∂>_ª‘•OüÚz\–\÷\≈Ú˘∫\ÌßØ\Â\Ÿm˜k\Áa	\r\⁄N†à∞\0\rQîI$	d¢RUI\Õu\«\ﬂpÜ˜\«˘UlßWØ>k˝\Í\ﬁ[øi\Ô\Ô\Ÿg\ﬂ}\‡?\«éˇPCñ\√\ﬁ¡ÎäøÛıOß0øØ·ïºnäl!7\⁄Xˇ,ù¨ü˝L\ÀÙ¥èæ\ÕKD\Á\“\Ó•Aáˇ>/árEOä9[(\Ó\ÏÏ´Üœ¥|O\Î8Û\¬¡\ÓE˙ØYªÆo¶£,M° ≠\Ô[ÃáıJπ¸wˆ/æ¥?X˝¨\ %\€\Œ\Î9ç∑õ\…]fW¨X)±]]\≈k˙˚˚\≈3-\Á\”2.ºr\È\‡\÷\›\ﬂz\œ_.Kkì€≠=véµøxñΩx[\—\n∞•Ç|`\—\"\nß˙\\ˇ\Èˆ©Wº~Ö‹∑w\ÊÇp∫˘•\Î>±j\Ë¨uyHhF\ÿ °kr°å\À˘m\„\„Q˚Tüˇ.º\»œçè6\ﬂs\Ó\ÊÚ-_˚Ú˙°≥\÷\Ê±Qå©70≠&Û3ì3öπ\œoX\÷Û\√\'Ûé?XxÒ+ã\”Gö_xÛï›óø\Ó™~È°±\ÕfæÅiDêj\ÌW$ëx`\”\⁄\‚ªn∫{\Ã<ô˜¸A:åó\\Z-\œŒ´Ø~\Ëö\·_∞£C≠∞≠6f∂éûè±±F+x˜µµ\÷¡#\Êí\Ô\ﬁ\Ìz≤\Ôz\⁄-`\«⁄¢Ñ=sâ™z\"\›Ò!mAx\‡y\“Hkµgeª;üø=0\ﬁ7\Êd≥=7ì^˚Ò/¡ñg*¬∂€ò\Ÿ&z>¡F´\‡˚?é\Ì±)Òô\Ì\Î˝{æ{Ôìó\Ôi≥Ä5yø´$÷µ-\Ô\Ô\Ëˆ\œ\ŒU_Û\ \Ó‹¶\r!aÅ\÷£5™ù29ë≠\Ô7\Ìû˝*˘o_û{^I†HbL#F\œ*L\À`µ\Â¡}ä˜~™q[gŸªÙ∆ªZÒ©\ ˆúmπ∏i<o†˜)†øC˙o[ww¯\—m\œ)o¸Ûw÷ÆÒÑ)@éco\r\ËT\€}\«)¡(P6I1s\Z5o±â\ÂÒqÕü˝\Ô˙\œ\Œ^ûª\Ë\⁄o\‘ÍøãLÀüUÒÑ6K<DØMykGéÛ˙*ﬁ¢a˘	K Ç≤f©\nmWog)_C_\‰D.]zbπä\\\‡\€ÒJ(˜\nkö±I{∫\›ReÕéá1≥i\À\Ê˝\\˛\'CØz\Ôªs˝Ω©ã3R\0x“Å\0\'\0 ›ß\“`-§)$\n4êlKÇ28¢π˙\Õ}\Á,\Ì∏\‚£ﬂò˙ç\ /\Ÿ\“+”πdÖ_±Çv˙W√ãΩu\Ám\…Uv^ZíK˚#\Õ[˛|V˚%!\¬\Ô˘F\ÿ7*≠6sb©Lm\ÿ_6¸\œ˜¨¢\‚\«\‰KÇbwÄ|⁄©•\›6\‘\ÍöZ]QØk\Í5\≈˛G#5~§\’¯\‡_,\Î⁄∏\  rS`J ∞\¬aE¶˜ÇÚ\Ÿ\Èπﬂæ\ÁORhI\ \ËHõv\√\Á\»ˆÉˇ\“‹µa∞\Î è~{¸ØU|}iº\’a3˘´s\ŒÙ\œ{\Õ\ÀJ\Â\Áù_† 8nx6N\—Q\ \‰Lö¯FZ\Èdù\'Ω\Á{àÆvl0öçÑsñ5\ÏôÛùQÅ¬å`,`A+\ﬂ&ç.7†•A≥kîOÙ8\ \€\Ï~†@DD$\ÿZû@˚|\Ïã3\Ê\Óá\“\◊-.¸\Ÿ\'nüˇe•œπd±òiÆÙBs\’p/o{ıKK\’]\\\√\√!\“Û¡Ad\∆n4÷Äj\Ê\Áô˜î\’\÷\¬nk\Ìıûg•\√rıñ!‘äÛ\◊%\ÿ8Eh\rFÉ1`4\¬(˜[\≈ Zø	aA\n	~\Ët[\0\‡\‰\œ\„hXP	4\"h%$£1?¯ˆ~ﬁ£c †(<ˆ<*Õè˜\∆o˝\ﬁ√µ}\'+æı\¬\≈~«ê^üÃ´èúΩF^˚¡wwΩΩÔ™ñ\Œ\›Z\›\’2\¬/Çüû\√8\’\ÿf\¬¸°î\œ\‹TÒNz\ﬁl!\‰&)˘J>=RàeèJÛ\ÎóbÂÄÑXa£\€jCª≠¥\Z$sM\ÓπsñeKÄÉU≥c)æÙæ\œ:U≠u\ \À\–4Å®çùQ\Ã2V]¡w\Ó®Û\ﬁ\Ô\·y\€KtwIñˆÙ\»o\ﬂ\›\Óòj~ÿ∫L\Ê≠\È:;n%ˇÚ¸-˘˝\Ì{∫\Œ~\Î\Ì\rW≠\Í¿\À¿/ú§¥\0≠!U\Ó]qÑm&LH¯\‹w\Z#OpÇç\\\\µØ∑B	\ﬂlÛ\ﬂOˇı∂u\·\Í◊Ω†êﬂ∂.†\⁄%±)ë¡¯h\ \»H\nQ¶z†\…Áæûøæ¸^<ˆMJ]\„,∂POAe Ìú°6\'P\◊$\”\ﬁˇw˚yˆ{¯Œ≠áòoZ\∆\«SV.»±§\œ_;;\ZÆ=#ØÜ\”r∑/A∏\ÈuWvy+ÜÛÄú\¬V∏\Á\ZÂú©R†S\–\ ˝Oà≠öD \Îø2\Zõ¬ÄFV¨∞ˇ∂w<˛?◊´c%\ﬁ\‹\€!WùVÆÚ≤Úbp\–\Á‡°î≥\ŒÒ°)Pı\◊}˛£µ>P\‰_oRú±∫Œõ˛\À \ÿ&êy˘ƒÇ\ÁÉ2nVÑ¶ì°\ﬁ<\◊|‰´Ñ9»ï=∫z*\ﬂ\‚m\ﬁ~Yu\Ë\ÕG\Ê~xıªñÙº\‚“º\Ë´nñen©i\r*u\œT©¿.,Y„Æ±\Ó_:kkø5<xê\Ã>`\ﬂÂóÑüûù3Kæˆ\√\Ê\‚/\‹\÷|∑RbUπ¿öØ˝\€@_B\“\“\∆\Î\Ï\‹˘º¿Úëøv/˜$†ú\“*aAZ\‹}¢+\‚≠oX\ t+‚±â6;wv±aKŸöGTöl=ø\Õ\ﬂ\Ô˜æ˙üÆ.¿xY\Ë¥`b7\√*v˛DiÜµ\Ó=\‡\ﬁe$X¥@%iô<≠D(ëÂä∏ÛGw\ÔX\€\¬\Ã\0w\ﬂ\÷\‡ù˚9m•\ÿtvôO~j=]•»ô!äd\Œp\”\◊ÁôõL9g{ë≠[ãN0/Ñ¶¿$U\Œz\…qsE«êxº7f˜h ´\ﬁTEJ\œ˘Qm2Gú:•±\Œ\œ,¯ú/,h\“\0=r\œ˜oˇ¯£◊úV-ê$ò|E\ÿ\\!Üæÿêóá\‹¸\‹3òôl≥Ù\Ã\n\≈\\\Õ\… \r(/ë~HÛµ]-^~\’\"(	g≤2Çl)ái±\„Kôií≤d˝bæq\ﬂ(##\√\À|∑æï=a\‚ëEû4Ø\÷\À˛/ú\Z	Zmã\ƒ<vZ\0Ùm (#ªùIa\·\Õ\—7d\È[\ÊCªëÜPÇÃÅÒz4Wˇe/oå:©ˆ•†\'§µY:ê\ÕX¨h=Ò‡ß¶X\ŸÚA\Ây†ör\’K\÷q\›?=\¬5ÔØ∫ô\◊ ôπ¿9@≤\ÔR∫g-cAógÿîΩì1_\Ì?-\0t\ƒ\–ZiW\'0Äß!\…êBhAH=7+Å\ﬂ\‡uk™:{à≤ô\Á\∆	kà5fZÛ\–ısljj\n≠A0K\È:\⁄\…\·\›5“ÜG£\Âb∂\∆r\rD\Êq·èÖd\ÕdK\¬9@Ñ`\œh\”h\ \Êi1BF≥|\Á\Â(ü¥nâÖ)\Z\Ë\0BæÖ<êÛ\\\·mc∑û5@™Z\⁄9(îè\0\“bï$h)\n\ J@A≠¶ÿº§õ…±¨V0ÚD\∆)≤ó\‡‹Ω…ú°¿*ãM∫£i∫®\Ï%ßeÒñ\ÁJ±ˇs≥\Ï˝◊à\Ój\Ô\Ã*õ^\’GÆ\‹\0\Ÿr3o-ê∆∂-w\ﬁ\\\√\Î9ˇ¢.HÄ|>[™î\≈-πü˙®¢\√kA¢ôÜ%Ks\Î\ÿ˝º∏+\Ô\‡ÒTNd\’d6≤\Ï\⁄*ãUõf\ \◊!\◊\ÃÃôzØ\'\Zß@Qä\Â\ÌΩ5ˆ|6\‚\Â~\ÎgrØd∂ªã¡?\ Aπ\ÌÑ\‘\Z\È\ÃRY¢√äºÆç\Ï4ú˚¸|ªÇHd&\‡)\÷^X\Â\—C\‰\Ê\Êô+Fn$∑4¶∑h)âl\ÈH\Á≠u˙\Îl¶\r\ÿlb1-ãâ,∂mH\Á%\„è[\Zu530sZ\0ƒ©π\Ôáﬂå∂Ω™*&r\ËÙ\n\Ï\€\›dπPÄ_ÉUÆ\⁄K\·\Ëﬁîˇª£èv°õ\⁄cz\÷\·¬§ƒô≤Ù†í\‚o˜Y≥|Ä®\Ê3T,ë\∆u>Ú\≈_Æ˜ı∏ÇIi∞∞.\ﬂi\ZLd1±\≈\∆–Æ&g,p\Î˝ëΩoM\◊\Ï|ªa\'ï≤77îäN\0ìÚÒ~¬ü^º\⁄\Ê˝Öı\ÁAßÜ†â\r\‡<≥u $sc1CÖ˘0\œ\ﬁ#z6\Ê!bN\"L|Aä∑“ß§†\›lÒé´«£ã\Œ\Àï\≈∆£ù∫\Ë%X\œ`\"A2\r”£öcSÜØ\ﬁ\—“∑ˇ<i6\ÌDá±wtxˆ˙)œØï•ô_çôæ~˝ß\…	&ñ˝èMÛ\‡m˝m°ﬂ¶´cñÉ\›1ÆX\≈Ò!\»û8ëä\Z0R\"ü@¶Y∏\ bπ¡˘å<Ä\r&5\Ï˝π\‚\Œ\›\Ìè\‹s@]ˆí7U7˜\n\ÂÄ*zêz$cÇø˛¯t˚\Ê]Ò>k\Õ#Ö¿˛}N≤∑\ËS˚ÒÿÇ\ƒ\Í\ﬂ\ÈpZ\0‘èGy˚é\«zªo\„5=≈©©g]R\ÍÉ¥Âúö\ \“]´≥uïUE¸†Ü(\n/´Ç\Õ˙\"Kj¨v\Ê\ÌªòfSE˚˝\Áç\◊\“/˝bè\ﬁ|¡≥¸,Ùmü£\”9æ}\œ\—˚äÛ˙Ç˚\Ão˚	\„¥#~ë\›7~}Íç∑\ÏK\”\’/\ŒS®\‘¡4¿$\ÓÒÅ8±∂æ\«\ VX˛=¨|õOı\Ã&à\‰+§uV$mßá\r\›UÀÜ\Â˛\“\‹¸°øôúJ}\ﬂ9A/\0%\…¨ß¢¸S@{\€Q7|ÚSáo\ﬂˇpäç%6Ú\\\ﬁ[wDYBÑÑP#\≈E)π!\r(HçkwÕ¥a&Ü\Èj\ f,¬á†§y\ŸsãKC\√\‡¡G€∑8©≥!©M∞¡;5¶˚)iç\Ì€Øu>†ÉDClú\“uÎî®)ò\”P≥NQ\ﬂ@™!J°ù@3ÜF&ö0ó∏\ƒHYx\'ä\ZëÉM´=Q≠zÔ¥û˙¯Wnh§õhL[FæG(rß∂¨ü\0\ﬁ~\¬X[(\Â¥gB9ß&çS&∂P\”0õ¬¥ÇY\r\r\r\r3é*hf\Â´\Êx\⁄JN@xB•S∞§\«[ñ\ÁÅÆØˇll¬¢kät>\∆ObÚæ\ÏHç,ˇ\ﬁàTñ\n^°£$ú[\r≤µ\Ô{êófLM\"\·ò	¯©Ä{-\‹o`F\‚nÙ≤î\÷%A˘`ÑqµDP∞l>#X\⁄\€\Ô\r$-˝ˇ~ró±\ÈXJz$ÅHS-xΩS©\È˝ΩptJ,™î≈¢bQ∏páu)j®]8\Àga-T\r,6 R0)Ù\ZXîJûÖ¢Ä2\–a!ß2iV ¡\∆\’˘n•\Ï¶\ﬂ˛∫\Îfß\⁄S`bã<6ñ\n©°t*≤?%Ω¡Ω˚U«Ö\œŒóÖîé9V@(ú%x\÷\’\·H≠;W2pÜ\ÃJh\\¶\ËKWÖŸµF8\ƒ\Z°-~ﬁ≤vmHKzˇ\\}{ı\ U;ïbsh\…9ÉeQî¨®i¯Ω∞º®É\ÂCÅûÑ¿\"Ç\ÏÑ\0Kªi\»ôœîV¬ï\ i&A^@éê5S$h:ë\nYÙØÇÀ∑áÉWl+Pnj\Ã1\ÀD¡Ÿª\«“Ø\ﬂ”∏\ﬂ úTˇ~\0†\◊_æ~U\¬ rÆ‘µ\‡\Á†m∏\Î\Êi6l\Õ34îÕ®ƒô¸\':ñc@XT\√p\Àb.{EWFd,Tî\n|\≈`74G¸\ƒ„ìª\„Ù\ÊÉıc\≈\’=yq˜/¥˝˜\È\ﬁoOâh7≈ï+ó¯àP@æ\0\≈\»\Â¿/BπÉı\Îã|ˇª-\Á<\Î`q\ÎΩ   +ec|±î\…$Ä\\	\ne\»!»É\ÁaÅ°nI:\'¯\ O˚Âá¢?],\Ìπ{gıª\∆\’))ˇî\0∞s\'\Ã\◊\Ì\ Œí\Õzú!\‰\ÀPÍÑ∞AÅEÎ∫π\ÁaEj\»\Í˜,B¯ûÛGWY\«\ÏúÒ\0: ,ÄÙ\›=\“B \Ë˜à⁄Ü˙<\—ä7‘¥x\“zú6\0=2\Ï(EgO%´˜µqèïY?N˙¯]e.ª§\¬»àr◊à¨_\'ó\ \ \–)iJæΩK3º¢\√˘õUá“ëö\¬ÀÇ£\„1ã\Êb.\Ô´ÑW=c\0õ`QOE.\Œ˘ñâC¶9¢N$4A¿ˆã™|\ÊKÕå™\ŒRedáèkn¯õ∂<g{\'a.´\rÑ\0\ﬂ\Ì∞	ƒìÇB\\\‡ë1CR\◊\\\÷%ΩFj˛ÊúÇ|F\0xÙqù[\‹\Á\Áå\‹Ùù6w\‹VC\◊»ä\¬R\–3XfjN¢R\ﬂÕ¨v\Ï\È´\„öèO˙<˜\¬ﬁì\0†vN±˚ˆü˝bçúêLiã-◊é\Èvß\Ô\›s8∂˙7\…˘¥\'FˆJ\·˚Ç7º¥ƒ±G#ˆ\ﬂ7áôkBg\ﬁ=ÆzY7≥\Ÿk≠tK¿≥e\‡D˘—Éäj5¿\÷UÜıàÉ˜7πswƒïØ\Ï¢dªöòw6wO)y·öí|ıî±ì\œ\0\€\œÒ´K˚=)°\\∫±¿ç7Œ±g\◊fr\‚8kP*V\r<º\'\Œ\Íôô∂\Áœ£U7xB\“Qv;B\–\nöMj#5æ˛Ω9\ﬁ˛¶N\ 9I0P\"Wß{Ú;oôU?ôXV-\Ìºh˘ì\Írù6\0µÉã{%¯ \nP,xº\Ì¢27|≠Œë\Î\Ë£S07µ9:√à\Èc\n∞Ÿåü\‹\»ô©	û}f™\Ì\Z¥\Í4é\ŒÒ±O\œ\⁄WT(Ñ|\√mb¥iXªb\„`~«ë±\⁄\Ó˘˘d•ZŒü2ß¿\Ê\Õ»éç\¬+¨´^tn¯\‚£\„ˆù≈Çá>¢bUM°KÚé?™p\›\Ã=\⁄\∆N5∞µ&µôÑëÉi\n\'zvÚ8ÖùF`fõÿ©Y®\œR?<\«?|∫\∆k/Ó†ø\‚r∂\⁄L è~R\Áˇ€¢¡uKwÖ\¬ﬁö\”f\Õ˙û\“[.Z=xJu\0úƒ™ˇÆ£Ø\œ\ﬂ\÷mΩöIÆ]‘ü{«ï/)çOh\∆kÇ37Jó˝zê≥íUù>_Ω-¢∑\‡q◊ÆÑ[æÒ\'/≠–ø$\€9\"≥\ÍO+à*°\Â?hr)çy\√?|©…´/(±fµÔä£º x<´S20£y‰êí\Á//ÖúX˝¿h≥í¶\Õ\ÔçÃ´ôS\—\ÁîMfÛfπhfJ‹∏cS\È‹≥W\Â\ƒg¯\\ˆ¢2æQ\‰,63g”Çâ˝ÇªˆG¨ˆÿ∏µÑ\\\ÏÉg≤t8tKA)\«)çô\—\ÏΩOq¯à\Êº\Õ9*\√@1[2sn√î\«\·#\ﬂ{\\≥™/\‰[{\‹Ùp„ßá\Á‘ñS\’\ÁîkÅ\ÈY∂Y\√¿é≠16¶\ŸÛ®a®\“`\À˙¢\‡Å\‘º≤ÄP0X\Ï\‹Zp]\‡¢/\…\ÿa\œıÚ≠uñ\‡{ÄAv\÷oë¨_\„;.°\√B,`T¡¡f$Ûmü[D\\±Ωää5˚&*Å\ﬂ¯U¨\ÔSÄîÚ;âØΩ}w{\Â}/g,\ŒÛÖ/M\–3•X\’-táPÃö•˝÷ïªm%ñf\\A`OP\‡RX\Ì\Œu•åùp\ÿ¬¨r\Â±p\«\ﬁo\Ï&\'˚&bvnSˆ?e\Ìy>`~ŒòJ\…\ÓBæ˛\rWV\ÂÇaÒ¢Ä˜ß¨©¯àöA5pålG\∆B¶Gfˆ\'±\ƒ;\∆.qZ\ÿCîhy0\n∞0\'\\\›x %kÛÅÒâò˜\ﬂ>√±¶N\∆\Î\ÍM3±}\⁄\0<\Z≥3\Êíh^≠‹¥±HOó`ı!6\—\»\ƒ\"J¬ºÜi\rÛ∏(Å≥ÑWôqÄ\⁄•R@\”¿àÅé\nàº¨˝\Â2E£=\‚¶er\"\Âw\ŒP.H\Ó?\Z\ﬂ{∞Æ?ºP≥\√ÚÇ\·Ùê\Ÿı~;&Ñx¯»®z\ÕÙxÆXû£T\»vbXêF ïp\ÈÆéù50iaV@[B]@#˚\ﬁ\Ó˜1-\≈Y@∂õ√•V˚$ë`b<\·≥?õßée\◊h27\Ÿ2\Ôk*´Å^\\s˛8%\√o	ıø\0éØ)=¿\0∞$5∂îì¢\\\Õyg˝¯ßm±h †ª\ÀG$ñzl\‚ì3¢ì\‡\ƒ&à®®≥¶-\Ã\0\Ÿg˚\ƒ6ñG@≠%)\ÃM+n∏û˝\r≈è\'î\Z©\Îo\Œ&\∆\Œ\÷Cô¨7/XCF8ú\Z\0(˝¿r`∞	x6∞•ô\⁄\¬tMuØØïGˆF®\‘\“Wê=q\Ôc1gV\›&hqí1ö¨[%ñF\À\–jY\⁄-K;≤¥#h\'•!ê\“\Ìn\0ñ\Ô=8èo·∂ëò\Ô\Ôor\ÔîJ\◊ıX=µe`5∞\nXT2EõdÆ7;‘Ø\‡∑\Â>–ï)ø\"Cw®f/™A©\√gp†\À_\“]ˆÚól(≤æ\Àg◊ûºΩõΩèG\Ï=ñ⁄ëzjf\⁄ZE ö(µ&—òD[´O4≤é\œR\Œ¢ß\Ë\Â;˝‹™\ﬁ@ˆï=Ò¯x\¬DS∞o6MFf€ì\Õ\‘I\Ïî\“¿p¯900\‘8æg\Á\‘ê8í∫ˇ$•K8Ç;Ãæw\0yOP%ã+yyRˆ\ÎTèFö±X\€ciÅYø\‹X<ã∞\'¨0¡\ÕX,\∆\"è†då\rH@KQOµYp´Yo\r2ö¿$0Ld\ \'¸\ZÛˇ]\0\»\ÿr8«≤\‡÷ñüùO˙^\Œ>-\'\ÃoaT\÷ ?Æ¥\»\Óô\"¬í]≥\‡DLˆú$;≤\∆ Íóî”øt^ˇ:\≈\∆ˇ≠b\—\–\Ô5_\«\0\0\0\0IENDÆB`Ç','test text',14);
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
