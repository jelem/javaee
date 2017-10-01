-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javaee
-- ------------------------------------------------------
-- Server version	5.7.18-log
-- no rows with images - workbench failed

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `author` text,
  `published` int(11) DEFAULT NULL,
  `image` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Посторонний','Альбер Камю',1942,NULL),(2,'В поисках утраченного времени','Марсель Пруст',1913,NULL),(3,'Процесс','Франц Кафка',1925,NULL),(4,'Маленький принц','Антуан де Сент-Экзюпери',1943,NULL),(5,'Удел человеческий','Андре Мальро',1933,NULL),(6,'Путешествие на край ночи','Луи-Фердинанд Селин',1932,NULL),(7,'Гроздья гнева','Джон Стейнбек',1939,NULL),(8,'По ком звонит колокол','Эрнест Хемингуэй',1940,NULL),(9,'Большой Мольн','Ален-Фурнье',1913,NULL),(10,'Пена дней','Борис Виан',1947,NULL),(11,'Второй пол','Симона де Бовуар',1949,NULL),(12,'В ожидании Годо','Сэмюэль Беккет',1952,NULL),(13,'Бытие и ничто','Жан-Поль Сартр',1943,NULL),(14,'Имя розы','Умберто Эко',1980,NULL),(15,'Архипелаг ГУЛАГ','Александр Солженицын',1973,NULL),(16,'Слова','Жак Превер',1946,NULL),(17,'Алкоголи','Гийом Аполлинер',1913,NULL),(18,'Голубой лотос','Эрже',1936,NULL),(19,'Дневник Анны Франк','Анна Франк',1947,NULL),(20,'Печальные тропики','Клод Леви-Стросс',1955,NULL),(21,'О дивный новый мир','Олдос Хаксли',1932,NULL),(22,'1984','Джордж Оруэлл',1949,NULL),(23,'Астерикс из Галлии','Рене Госинни и Альбер Удерзо',1959,NULL),(24,'Лысая певица','Эжен Ионеско',1952,NULL),(26,'Философский камень','Маргерит Юрсенар',1968,NULL),(27,'Лолита','Владимир Набоков',1955,NULL),(28,'Улисс','Джеймс Джойс',1922,NULL),(29,'Татарская пустыня','Дино Буццати',1940,NULL),(30,'Фальшивомонетчики','Андре Жид',1925,NULL),(31,'Всадник на крыше','Жан Жионо',1951,NULL),(32,'Прекрасная дама','Альбер Коэн',1968,NULL),(33,'Сто лет одиночества','Габриэль Гарсия Маркес',1967,NULL),(34,'Шум и ярость','Уильям Фолкнер',1929,NULL),(35,'Тереза Дескейру','Франсуа Мориак',1927,NULL),(36,'Зази в метро','Раймон Кено',1959,NULL),(37,'Смятение чувств','Стефан Цвейг',1927,NULL),(38,'Унесённые ветром','Маргарет Митчелл',1936,NULL),(39,'Любовник леди Чаттерлей','Дэвид Герберт Лоуренс',1928,NULL),(40,'Волшебная гора','Томас Манн',1924,NULL),(41,'Здравствуй, грусть!','Франсуаза Саган',1954,NULL),(42,'Молчание моря','Веркор',1942,NULL),(43,'Жизнь, способ употребления','Жорж Перек',1978,NULL),(44,'Собака Баскервилей','Артур Конан Дойль',1901,NULL),(45,'Под солнцем Сатаны','Жорж Бернанос',1926,NULL),(46,'Великий Гэтсби','Фрэнсис Скотт Фицджеральд',1925,NULL),(47,'Шутка','Милан Кундера',1967,NULL),(49,'Убийство Роджера Экройда','Агата Кристи',1926,NULL),(50,'Надя','Андре Бретон',1928,NULL),(51,'Орельен','Луи Арагон',1944,NULL),(52,'Атласный башмачок','Поль Клодель',1929,NULL),(53,'Шесть персонажей в поисках автора','Луиджи Пиранделло',1921,NULL),(54,'Карьера гангстера Артуро Уи, которой могло и не быть','Бертольт Брехт',1959,NULL),(55,'Пятница, или Тихоокеанский лимб','Мишель Турнье',1967,NULL),(56,'Война миров','Герберт Уэллс',1898,NULL),(57,'Человек ли это?','Примо Леви',1947,NULL),(58,'Властелин колец','Дж. Р. Р. Толкиен',1954,NULL),(59,'Усики виноградной лозы','Колетт',1908,NULL),(60,'Град скорби','Поль Элюар',1926,NULL),(61,'Мартин Иден','Джек Лондон',1909,NULL),(63,'Нулевая степень письма','Ролан Барт',1953,NULL),(64,'Потерянная честь Катарины Блюм','Генрих Бёлль',1974,NULL),(65,'Побережье Сирта','Жюльен Грак',1951,NULL),(66,'Слова и вещи. Археология гуманитарных наук','Мишель Фуко',1966,NULL),(67,'На дороге','Джек Керуак',1957,NULL),(68,'Чудесное путешествие Нильса с дикими гусями','Сельма Лагерлёф',1906,NULL),(69,'Своя комната','Вирджиния Вульф',1929,NULL),(70,'Марсианские хроники','Рэй Брэдбери',1950,NULL),(71,'Восхищение Лол Стайн','Маргерит Дюрас',1964,NULL),(72,'Протокол','Жан-Мари Гюстав Леклезио',1963,NULL),(73,'Тропизмы','Натали Саррот',1939,NULL),(74,'Дневник','Жюль Ренар',1925,NULL),(75,'Лорд Джим','Джозеф Конрад',1900,NULL),(76,'Сочинения','Жак Лакан',1966,NULL),(77,'Театр и его двойник','Антонен Арто',1938,NULL),(78,'Манхэттен','Джон Дос Пассос',1925,NULL),(79,'Вымыслы[en]','Хорхе Луис Борхес',1944,NULL),(80,'Мораважин','Блез Сандрар',1926,NULL),(81,'Генерал мёртвой армии','Исмаиль Кадаре',1963,NULL),(82,'Выбор Софи','Уильям Стайрон',1979,NULL),(83,'Цыганское романсеро','Федерико Гарсиа Лорка',1928,NULL),(84,'Петерс Латыш','Жорж Сименон',1931,NULL),(85,'Богоматерь цветов','Жан Жене',1944,NULL),(86,'Человек без свойств','Роберт Музиль',1930,NULL),(87,'Ярость и тайна','Рене Шар',1948,NULL),(88,'Над пропастью во ржи','Дж. Д. Сэлинджер',1951,NULL),(89,'Нет орхидей для мисс Блэндиш','Джеймс Хедли Чейз',1939,NULL),(90,'Блейк и Мортимер','Эдгар П. Жакоб',1950,NULL),(91,'Записки Мальте Лауридса Бригге','Райнер Мария Рильке',1910,NULL),(92,'Изменение','Мишель Бютор',1957,NULL),(93,'Истоки тоталитаризма','Ханна Арендт',1951,NULL),(94,'Мастер и Маргарита','Михаил Булгаков',1967,NULL),(95,'Сексус, Плексус, Нексус','Генри Миллер',1960,NULL),(96,'Глубокий сон','Раймонд Чандлер',1939,NULL),(97,'Створы','Сен-Жон Перс',1957,NULL),(98,'Гастон','Андре Франкин',1957,NULL),(99,'У подножия вулкана','Малькольм Лаури',1947,NULL),(100,'Дети полуночи','Салман Рушди',1981,NULL),(101,'Code Complete','Steve McConnell',2004,NULL),(102,'The Pragmatic Programmer: From Journeyman to Master','Andrew Hunt, David Thomas',1999,NULL),(103,'Structure and Interpretation of Computer Programs','Harold Abelson, Gerald J Sussman, Julie Sussman',1996,NULL),(104,'Introduction to Algorithms','Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest и Clifford Stein',2009,NULL),(105,'Refactoring: Improving the Design of Existing Code','Martin Fowler, Kent Beck, John Brant и William Opdyke',1999,NULL),(106,'Design Patterns: Elements of Reusable Object-Oriented Software','Erich Gamma, Richard Helm, Ralph Johnson и John Vlissides',1994,NULL),(107,'The Mythical Man-Month: Essays on Software Engineering','Frederick P. Brooks',1995,NULL),(108,'Art of Computer Programming, Volume 1: Fundamental Algorithms','Donald E. Knuth',1997,NULL),(109,'Compilers: Principles, Techniques, and Tools («Компиляторы: принципы, технологии и инструменты»), 2-е издание.','Alfred V. Aho, Monica S. Lam, Ravi Sethi и Jeffrey D. Ullman',2006,NULL),(110,'Head First Design Patterns','Elisabeth Freeman, Eric Freeman, Bert Bates and Kathy Sierra',2004,NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-22 13:27:28
