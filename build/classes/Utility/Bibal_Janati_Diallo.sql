-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           10.1.12-MariaDB - mariadb.org binary distribution
-- Serveur OS:                   Win64
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Export de la structure de table bibalumlloria. usager
CREATE TABLE IF NOT EXISTS `usager` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `DateNais` date NOT NULL,
  `Sexe` varchar(255) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `Tel` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- Export de données de la table bibalumlloria.usager: ~101 rows (environ)
/*!40000 ALTER TABLE `usager` DISABLE KEYS */;
INSERT INTO `usager` (`ID`, `Nom`, `Prenom`, `DateNais`, `Sexe`, `Adresse`, `Tel`) VALUES
  (1, 'Soto', 'Lev', '2015-09-05', 'Homme ', '611-8071 Non, Impasse', '03 65 28 43 07'),
  (2, 'Solis', 'Penelope', '2016-10-01', 'Homme ', 'Appartement 464-4541 Sed Route', '03 45 29 51 48'),
  (3, 'Kerr', 'Evelyn', '2015-06-29', 'Homme ', '997-1437 Tellus Impasse', '07 54 60 67 78'),
  (4, 'Shepherd', 'Yardley', '2016-10-23', 'Homme ', 'Appartement 436-7802 Duis Av.', '04 04 29 43 90'),
  (5, 'BRIGGS', 'Cailin', '2016-12-02', 'Féminin', '486-130 Aliquam Av.', '0682024228'),
  (6, 'Boyer', 'Zachery', '2015-05-06', 'Homme ', 'CP 957, 7758 Euismod Av.', '09 89 44 08 34'),
  (7, 'Holmes', 'Nathaniel', '2016-10-18', 'Homme ', 'CP 168, 6599 Sit Ave', '04 60 84 81 58'),
  (8, 'Holland', 'Kirk', '2016-05-13', 'Homme ', '5343 Ornare, Av.', '07 34 48 84 30'),
  (9, 'Watts', 'Bernard', '2017-04-15', 'Homme ', '3710 Vulputate, Chemin', '02 96 66 62 40'),
  (10, 'Curtis', 'Victoria', '2015-09-13', 'Homme ', '198 Tellus. Ave', '09 28 39 37 64'),
  (11, 'Keith', 'Raya', '2016-01-26', ' Femme', 'Appartement 486-8127 Vel Chemin', '09 39 59 43 55'),
  (12, 'Chavez', 'Griffith', '2017-02-20', ' Femme', 'CP 821, 220 In Ave', '08 77 79 71 23'),
  (13, 'Stein', 'Xantha', '2016-12-19', ' Femme', '643 Libero Rd.', '05 30 56 02 34'),
  (14, 'Gamble', 'Jarrod', '2016-06-21', ' Femme', '9473 Mauris Route', '02 89 51 80 69'),
  (15, 'Le', 'Jescie', '2016-09-12', ' Femme', 'Appartement 402-9000 Lectus Route', '09 11 46 01 71'),
  (16, 'Oneil', 'Fletcher', '2015-11-17', ' Femme', '591-8894 Augue Ave', '02 63 89 85 85'),
  (17, 'Patterson', 'Lamar', '2016-12-04', ' Femme', '654-8974 Sapien Route', '08 30 26 08 28'),
  (18, 'Lindsey', 'Kadeem', '2015-05-16', ' Femme', 'CP 101, 9498 Nec Route', '06 43 26 80 11'),
  (19, 'Castro', 'William', '2017-02-13', ' Femme', '4584 Consectetuer Av.', '08 31 13 83 38'),
  (20, 'Riggs', 'Zachary', '2015-09-19', ' Femme', 'Appartement 969-7160 Magna. Rue', '06 20 79 39 10'),
  (21, 'Dudley', 'Charde', '2015-11-11', 'Homme ', 'Appartement 663-5855 Placerat, Impasse', '02 80 69 13 84'),
  (22, 'Mueller', 'Joshua', '2016-09-07', 'Homme ', 'Appartement 239-5202 Non Rue', '01 46 39 02 29'),
  (23, 'Weiss', 'Beau', '2017-01-08', 'Homme ', 'CP 463, 8955 Laoreet, Route', '02 17 20 11 09'),
  (24, 'Maddox', 'Dawn', '2017-01-05', 'Homme ', 'CP 297, 5309 Quam Rue', '04 54 18 13 69'),
  (25, 'Drake', 'Kelly', '2016-06-03', 'Homme ', '160-782 Pellentesque Rd.', '06 10 93 12 12'),
  (26, 'Witt', 'Eagan', '2016-11-16', 'Homme ', '984-4052 Rutrum Route', '01 25 32 49 78'),
  (27, 'Lloyd', 'Renee', '2015-10-29', 'Homme ', 'Appartement 658-8081 Proin Avenue', '02 90 17 58 24'),
  (28, 'Lane', 'Nathan', '2016-08-28', 'Homme ', 'Appartement 178-8324 Netus Ave', '05 21 44 80 62'),
  (29, 'Mcknight', 'Hector', '2015-11-13', 'Homme ', 'Appartement 853-3347 Phasellus Chemin', '01 34 86 33 85'),
  (30, 'Woodard', 'Bruno', '2016-08-12', 'Homme ', 'Appartement 662-345 Dolor. Chemin', '06 98 54 78 83'),
  (31, 'Giles', 'Devin', '2015-12-11', ' Femme', '8062 Augue Impasse', '08 81 11 85 72'),
  (32, 'Benjamin', 'Griffin', '2016-04-27', ' Femme', '4262 Proin Ave', '06 11 17 34 61'),
  (33, 'Wall', 'Nash', '2016-08-14', ' Femme', '2177 Ultricies Rue', '07 96 24 47 07'),
  (34, 'Gomez', 'Teegan', '2015-09-26', ' Femme', '860-1484 Arcu. Ave', '01 39 72 03 21'),
  (35, 'Daniels', 'Clark', '2015-12-18', ' Femme', 'Appartement 938-6022 Donec Route', '03 39 67 27 17'),
  (36, 'Nguyen', 'Mona', '2015-08-19', ' Femme', 'Appartement 740-7672 Interdum. Impasse', '03 63 64 58 28'),
  (37, 'Howell', 'Zelenia', '2015-07-27', ' Femme', '3695 Eu Impasse', '08 74 55 12 61'),
  (38, 'Burgess', 'Honorato', '2016-08-12', ' Femme', 'CP 584, 3377 Justo Route', '03 66 99 66 99'),
  (39, 'Nixon', 'Dolan', '2017-04-07', ' Femme', '2214 Et Rd.', '03 15 39 98 52'),
  (40, 'Casey', 'Amelia', '2016-09-02', ' Femme', '893-9437 Ligula. Rue', '04 76 36 18 83'),
  (41, 'Oneil', 'Keefe', '2016-10-24', 'Homme ', 'Appartement 621-4937 Turpis Av.', '01 50 72 09 29'),
  (42, 'Kinney', 'Rinah', '2015-09-27', 'Homme ', '1570 Est, Impasse', '09 70 12 65 47'),
  (43, 'Whitney', 'Quincy', '2017-03-01', 'Homme ', 'CP 774, 3014 Amet Avenue', '06 84 99 73 25'),
  (44, 'Prince', 'Eleanor', '2017-02-23', 'Homme ', '481-3562 Penatibus Rue', '04 37 19 33 58'),
  (45, 'Peters', 'Linus', '2016-05-26', 'Homme ', 'CP 945, 6472 Orci Chemin', '09 09 79 61 63'),
  (46, 'Hunt', 'Quinn', '2016-10-30', 'Homme ', 'Appartement 918-3066 Facilisis Rd.', '06 46 50 19 51'),
  (47, 'Hyde', 'Irma', '2016-11-15', 'Homme ', 'CP 764, 2652 A Rd.', '08 38 72 54 17'),
  (48, 'Mcknight', 'Alika', '2017-01-27', 'Homme ', 'Appartement 567-1884 In Impasse', '09 17 11 97 18'),
  (49, 'Kennedy', 'Sybill', '2017-03-23', 'Homme ', 'Appartement 821-6318 Tempus Chemin', '04 67 44 96 30'),
  (50, 'Church', 'Daria', '2015-10-22', 'Homme ', '1171 Lectus Chemin', '03 02 43 22 28'),
  (51, 'Stewart', 'Macon', '2016-06-14', ' Femme', 'CP 398, 5712 Vitae Rue', '02 10 56 75 02'),
  (52, 'Norman', 'Addison', '2016-11-16', ' Femme', '400 Ante Rd.', '08 31 83 58 92'),
  (53, 'Wood', 'Destiny', '2015-06-17', ' Femme', 'CP 377, 6873 Laoreet Av.', '05 80 06 06 09'),
  (54, 'Tucker', 'Yoshi', '2016-09-02', ' Femme', 'Appartement 413-6509 Lacinia Ave', '05 59 51 77 30'),
  (55, 'Hampton', 'Ainsley', '2016-11-13', ' Femme', 'Appartement 267-8909 Orci Av.', '07 72 43 91 97'),
  (56, 'Pittman', 'Cameron', '2017-03-18', ' Femme', 'CP 491, 8543 Proin Chemin', '07 10 58 34 83'),
  (57, 'Holden', 'Armand', '2016-11-30', ' Femme', 'Appartement 699-1639 Faucibus Av.', '03 16 64 82 60'),
  (58, 'Vance', 'Maile', '2017-01-30', ' Femme', '4411 Sem Ave', '05 28 57 12 66'),
  (59, 'Huffman', 'Bruce', '2016-09-15', ' Femme', 'CP 297, 7393 Auctor Ave', '05 89 43 20 05'),
  (60, 'Poole', 'Jarrod', '2015-07-03', ' Femme', 'CP 560, 4625 Lorem Rue', '04 78 37 75 10'),
  (61, 'Holloway', 'Paula', '2015-12-22', 'Homme ', '193-6448 Donec Chemin', '01 03 96 83 22'),
  (62, 'Freeman', 'Rose', '2015-08-27', 'Homme ', 'CP 467, 4149 Sed Ave', '04 41 10 11 02'),
  (63, 'Cervantes', 'Kaitlin', '2016-04-13', 'Homme ', 'Appartement 603-8179 Fusce Av.', '08 86 46 94 54'),
  (64, 'Santos', 'Destiny', '2016-02-07', 'Homme ', 'Appartement 804-9939 Ante Avenue', '01 28 92 33 90'),
  (65, 'Wolf', 'Hadley', '2015-12-01', 'Homme ', 'CP 726, 7368 Metus. Impasse', '08 59 21 50 51'),
  (66, 'Morin', 'Phyllis', '2016-09-11', 'Homme ', '933-9590 Vitae Av.', '07 66 43 44 63'),
  (67, 'Robles', 'Galena', '2015-08-06', 'Homme ', '272-6867 Mollis Avenue', '08 14 22 54 43'),
  (68, 'Hoover', 'Hayfa', '2016-09-16', 'Homme ', 'Appartement 957-5575 Neque. Av.', '03 33 25 47 01'),
  (69, 'Guy', 'Thor', '2016-01-22', 'Homme ', '565-8655 Urna. Impasse', '03 68 48 09 14'),
  (70, 'Cross', 'Hakeem', '2016-01-02', 'Homme ', '441-1228 Hendrerit. Impasse', '03 31 71 89 65'),
  (71, 'Norman', 'Shaeleigh', '2015-06-21', ' Femme', 'Appartement 322-4143 Elit Rd.', '09 93 55 96 26'),
  (72, 'Garner', 'Rebekah', '2015-05-07', ' Femme', '5557 Maecenas Chemin', '05 94 76 48 52'),
  (73, 'Madden', 'Pamela', '2016-03-02', ' Femme', 'Appartement 292-1706 Duis Rd.', '03 54 68 67 83'),
  (74, 'Daugherty', 'Guy', '2016-04-08', ' Femme', '5183 Eros Rue', '03 91 05 83 16'),
  (75, 'Mcneil', 'Michael', '2016-07-11', ' Femme', 'CP 654, 6262 Aliquet. Rue', '01 86 78 30 69'),
  (76, 'Michael', 'Howard', '2015-10-08', ' Femme', '404-9017 Nunc Rd.', '03 67 89 98 00'),
  (77, 'Ayers', 'Demetria', '2015-06-17', ' Femme', '5790 Arcu. Av.', '08 15 40 80 77'),
  (78, 'Walton', 'Thane', '2015-09-13', ' Femme', 'Appartement 624-3697 Interdum. Ave', '05 68 80 54 52'),
  (79, 'Blevins', 'Scarlet', '2016-03-30', ' Femme', 'CP 166, 1361 Mauris Rd.', '01 24 85 02 94'),
  (80, 'Fisher', 'Tanya', '2015-04-25', ' Femme', '1476 Sollicitudin Chemin', '05 74 68 19 01'),
  (81, 'Potter', 'Hayes', '2016-02-18', 'Homme ', 'CP 128, 1392 Elementum Route', '07 99 08 14 98'),
  (82, 'Molina', 'Olivia', '2015-05-26', 'Homme ', '103-9739 Leo, Route', '08 29 71 42 29'),
  (83, 'Blankenship', 'Shay', '2016-04-19', 'Homme ', 'CP 761, 6652 Feugiat Impasse', '09 96 39 87 18'),
  (84, 'Hayes', 'Macey', '2016-09-12', 'Homme ', '640-8801 Nulla. Avenue', '07 80 50 81 93'),
  (85, 'York', 'Kenyon', '2016-11-05', 'Homme ', 'Appartement 816-1880 Integer Ave', '09 17 45 21 57'),
  (86, 'Hudson', 'Lareina', '2016-07-06', 'Homme ', '966-6892 Eu Ave', '01 00 96 30 24'),
  (87, 'Parrish', 'Rebekah', '2016-04-11', 'Homme ', '2084 Sit Chemin', '03 39 27 04 39'),
  (88, 'Kline', 'Jamal', '2016-01-13', 'Homme ', '425-6964 Tempus Impasse', '06 69 40 48 82'),
  (89, 'Ellis', 'Lance', '2017-03-04', 'Homme ', 'Appartement 850-5715 Non, Rue', '07 63 09 62 13'),
  (90, 'Cox', 'Meredith', '2017-03-06', 'Homme ', '7638 Suspendisse Ave', '01 97 39 24 75'),
  (91, 'Haney', 'Phelan', '2016-03-23', ' Femme', '180-3626 Metus. Route', '09 00 25 14 85'),
  (92, 'Buck', 'Hammett', '2016-10-15', ' Femme', '9437 Et Chemin', '03 45 46 43 58'),
  (93, 'Wright', 'Celeste', '2015-09-17', ' Femme', 'Appartement 233-7468 Eros Rue', '04 73 81 82 35'),
  (94, 'Clements', 'Leslie', '2016-04-06', ' Femme', 'CP 519, 5535 Elementum Rue', '06 84 03 96 75'),
  (95, 'Schmidt', 'Arsenio', '2016-08-24', ' Femme', '150-5537 Id Chemin', '02 63 69 20 00'),
  (96, 'ROJAS', 'Cara', '2015-07-15', 'Féminin', 'CP 414, 5404 Aliquet Rue', '0487929742'),
  (97, 'Cummings', 'Jael', '2015-07-01', ' Femme', 'Appartement 421-6226 Feugiat Rue', '05 15 53 59 89'),
  (98, 'Wilcox', 'Belle', '2016-05-04', ' Femme', '389-3998 Aliquet, Rd.', '02 90 36 98 70'),
  (99, 'Lester', 'Portia', '2015-10-10', ' Femme', 'Appartement 687-2031 Magna. Route', '02 47 72 16 95'),
  (100, 'Conner', 'Rana', '2016-04-10', ' Femme', 'Appartement 916-4387 Donec Rd.', '01 76 37 37 94'),
  (111, 'JANATI', 'Idrissi Mohammed', '1992-04-07', 'Masculin', 'Kamra cym Rabat', '0679876590');


  -- Export de la structure de table bibalumlloria. oeuvre
CREATE TABLE IF NOT EXISTS `oeuvre` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Titre` varchar(255) NOT NULL,
  `Auteur` varchar(255) NOT NULL,
  `Categorie` varchar(255) NOT NULL,
  `NbResa` int(11) NOT NULL DEFAULT '0',
  `Lending` int(11) NOT NULL,
  `TypeOeuvre` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Export de données de la table bibalumlloria.oeuvre: ~21 rows (environ)
/*!40000 ALTER TABLE `oeuvre` DISABLE KEYS */;
INSERT INTO `oeuvre` (`ID`, `Titre`, `Auteur`, `Categorie`, `NbResa`, `Lending`, `TypeOeuvre`) VALUES
  (1, 'metus', 'Amery Tyler', 'Classiques ', 4, 10, 'Livre'),
  (2, 'magna et ipsum', 'Petra Fry', 'Classiques ', 0, 10, 'Livre '),
  (3, 'ligula.', 'Fritz Smith', 'Classiques ', 0, 10, 'Livre'),
  (4, 'ante. Vivamus', 'Candace Kidd', ' Policier ', 1, 30, 'Livre'),
  (5, 'tempor', 'Gretchen Austin', ' Policier ', 1, 30, ' Magazine'),
  (6, 'mi fringilla mi', 'Gwendolyn Livingston', ' Policier ', 2, 30, ' Magazine'),
  (7, 'cursus in', 'Jackson Ramsey', ' Historique ', 3, 10, 'Livre '),
  (8, 'porta', 'Destiny Taylor', ' Historique ', 1, 10, 'Livre '),
  (9, 'erat', 'Heidi Church', ' Historique ', 3, 10, 'Livre '),
  (10, 'magna et ipsum', 'Desirae Hodges', ' Humour', 2, 30, ' Magazine'),
  (11, 'Sed congue', 'Peter Lara', ' Humour', 2, 30, ' Magazine'),
  (12, 'justo sit amet', 'Nyssa Gillespie', ' Humour', 1, 30, ' Magazine'),
  (13, 'eu elit', 'Eagan Maldonado', 'Classiques ', 1, 10, 'Livre '),
  (14, 'felis ullamcorper viverra', 'William Coffey', 'Classiques ', 4, 10, 'Livre '),
  (15, 'Morbi', 'Lewis Fowler', 'Classiques ', 2, 10, 'Livre '),
  (16, 'arcu imperdiet', 'Helen Cross', ' Policier ', 2, 30, ' Magazine'),
  (17, 'tristique', 'Gage Crosby', ' Policier ', 5, 30, ' Magazine'),
  (18, 'parturient montes', 'Duncan Collins', ' Policier ', 1, 30, ' Magazine'),
  (19, 'volutpat ornare facilisis', 'Gail Whitley', ' Historique ', 3, 10, 'Livre '),
  (20, 'ante lectus', 'Cole Frost', ' Historique ', 2, 10, 'Livre'),
  (27, 'Les comptes philosohiques', 'Voltaire', 'Fiction', 0, 10, 'Livre');
/*!40000 ALTER TABLE `oeuvre` ENABLE KEYS */;

-- Export de la structure de table bibalumlloria. exemplaire
CREATE TABLE IF NOT EXISTS `exemplaire` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OeuvreID` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKExamplaire350739` (`OeuvreID`),
  CONSTRAINT `FK_Examplaire_Oeuvre` FOREIGN KEY (`OeuvreID`) REFERENCES `oeuvre` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- Export de données de la table bibalumlloria.exemplaire: ~41 rows (environ)
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
INSERT INTO `exemplaire` (`ID`, `OeuvreID`, `etat`) VALUES
  (1, 8, 'Neuf '),
  (2, 8, 'Neuf '),
  (3, 2, 'Neuf '),
  (4, 8, ' Bon '),
  (5, 18, ' Bon '),
  (6, 1, ' Bon '),
  (7, 14, ' Vieux'),
  (8, 20, ' Vieux'),
  (9, 11, ' Vieux'),
  (10, 1, 'Neuf '),
  (11, 4, 'Neuf '),
  (12, 1, 'Neuf '),
  (13, 1, ' Bon '),
  (14, 1, ' Bon '),
  (15, 3, ' Bon '),
  (16, 14, ' Vieux'),
  (17, 4, ' Vieux'),
  (18, 13, ' Vieux'),
  (19, 7, 'Neuf '),
  (20, 17, 'Neuf '),
  (21, 2, 'Neuf '),
  (22, 12, ' Bon '),
  (23, 12, ' Bon '),
  (24, 2, ' Bon '),
  (25, 2, ' Vieux'),
  (27, 6, ' Vieux'),
  (28, 13, 'Neuf '),
  (29, 13, 'Neuf '),
  (30, 3, 'Neuf '),
  (31, 19, ' Bon '),
  (32, 17, ' Bon '),
  (33, 10, ' Bon '),
  (34, 10, ' Vieux'),
  (35, 12, ' Vieux'),
  (36, 9, ' Vieux'),
  (37, 15, 'Neuf '),
  (38, 14, 'Neuf '),
  (39, 7, 'Neuf '),
  (40, 17, ' Bon '),
  (45, 27, 'Neuf'),
  (46, 27, 'Vieux');
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;

-- Export de la structure de table bibalumlloria. emprunt
CREATE TABLE IF NOT EXISTS `emprunt` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ExemplaireId` int(11) NOT NULL,
  `UsagerID` int(11) NOT NULL,
  `DateEmprunt` date NOT NULL,
  `DateRetourPrevu` date NOT NULL,
  `DateRetourEffective` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKEmprunt521388` (`UsagerID`),
  KEY `FKEmprunt665940` (`ExemplaireId`),
  CONSTRAINT `FK_Emprunt_Examplaire` FOREIGN KEY (`ExemplaireId`) REFERENCES `exemplaire` (`ID`),
  CONSTRAINT `FK_Emprunt_Usager` FOREIGN KEY (`UsagerID`) REFERENCES `usager` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- Export de données de la table bibalumlloria.emprunt: ~43 rows (environ)
/*!40000 ALTER TABLE `emprunt` DISABLE KEYS */;
INSERT INTO `emprunt` (`ID`, `ExemplaireId`, `UsagerID`, `DateEmprunt`, `DateRetourPrevu`, `DateRetourEffective`) VALUES
	(1, 4, 59, '2016-04-28', '2016-05-15', NULL),
	(2, 13, 56, '2016-04-29', '2016-05-16', NULL),
	(3, 4, 35, '2016-04-28', '2016-05-10', NULL),
	(4, 16, 18, '2016-04-22', '2016-05-15', NULL),
	(5, 34, 82, '2016-04-22', '2016-05-03', NULL),
	(6, 11, 68, '2016-04-21', '2016-05-14', NULL),
	(7, 9, 30, '2016-04-22', '2016-05-15', NULL),
	(8, 25, 92, '2016-04-21', '2016-05-14', NULL),
	(9, 40, 52, '2016-04-27', '2016-05-10', NULL),
	(10, 31, 44, '2016-04-24', '2016-05-16', NULL),
	(11, 22, 8, '2016-04-27', '2016-05-03', NULL),
	(12, 18, 83, '2016-04-29', '2016-05-13', NULL),
	(13, 22, 25, '2016-04-26', '2016-05-18', NULL),
	(14, 27, 36, '2016-04-21', '2016-05-17', NULL),
	(15, 30, 69, '2016-04-27', '2016-05-08', '2016-04-21'),
	(16, 38, 21, '2016-04-26', '2016-05-16', NULL),
	(17, 10, 72, '2016-04-29', '2016-05-15', NULL),
	(18, 7, 69, '2016-04-27', '2016-05-18', NULL),
	(19, 39, 90, '2016-04-27', '2016-05-06', NULL),
	(20, 19, 5, '2016-04-23', '2016-05-04', NULL),
	(21, 23, 91, '2016-04-29', '2016-05-16', NULL),
	(22, 12, 39, '2016-04-24', '2016-05-18', NULL),
	(23, 25, 81, '2016-04-23', '2016-05-02', NULL),
	(24, 18, 51, '2016-04-24', '2016-05-04', NULL),
	(25, 22, 34, '2016-04-21', '2016-05-14', NULL),
	(26, 4, 56, '2016-04-24', '2016-05-09', NULL),
	(27, 5, 93, '2016-04-26', '2016-05-17', NULL),
	(28, 39, 89, '2016-04-27', '2016-05-13', NULL),
	(29, 40, 46, '2016-04-27', '2016-05-04', NULL),
	(30, 25, 65, '2016-04-22', '2016-05-02', NULL),
	(31, 24, 82, '2016-04-21', '2016-05-13', '2016-04-21'),
	(32, 35, 9, '2016-04-22', '2016-05-06', NULL),
	(33, 30, 51, '2016-04-21', '2016-05-14', '2016-04-21'),
	(34, 18, 51, '2016-04-26', '2016-05-08', NULL),
	(35, 35, 22, '2016-04-25', '2016-05-17', NULL),
	(36, 5, 75, '2016-04-26', '2016-05-03', NULL),
	(37, 22, 98, '2016-04-27', '2016-05-04', NULL),
	(38, 36, 80, '2016-04-23', '2016-05-18', NULL),
	(39, 8, 69, '2016-04-28', '2016-05-01', NULL),
	(40, 39, 84, '2016-04-24', '2016-05-13', NULL),
	(41, 15, 1, '2016-04-21', '2016-05-01', NULL),
	(42, 6, 2, '2016-04-22', '2016-05-02', '2016-04-22'),
	(43, 6, 1, '2016-04-22', '2016-05-02', '2016-04-22');
/*!40000 ALTER TABLE `emprunt` ENABLE KEYS */;








-- Export de la structure de table bibalumlloria. reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OeuvreID` int(11) NOT NULL,
  `UsagerID` int(11) NOT NULL,
  `dateReservation` date NOT NULL,
  `DateAnnulation` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKReservatio502961` (`UsagerID`),
  KEY `FKReservatio107611` (`OeuvreID`),
  CONSTRAINT `FK_Reservatio_Oeuvre` FOREIGN KEY (`OeuvreID`) REFERENCES `oeuvre` (`ID`),
  CONSTRAINT `FK_Reservatio_Usager` FOREIGN KEY (`UsagerID`) REFERENCES `usager` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- Export de données de la table bibalumlloria.reservation: ~44 rows (environ)
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` (`ID`, `OeuvreID`, `UsagerID`, `dateReservation`, `DateAnnulation`) VALUES
	(1, 19, 26, '2016-04-02', NULL),
	(2, 19, 75, '2016-04-04', NULL),
	(3, 17, 86, '2016-04-01', NULL),
	(4, 17, 30, '2016-04-07', NULL),
	(5, 1, 54, '2016-04-06', NULL),
	(6, 1, 85, '2016-04-05', NULL),
	(7, 18, 29, '2016-04-04', NULL),
	(8, 17, 80, '2016-04-01', NULL),
	(9, 1, 21, '2016-04-07', NULL),
	(10, 14, 1, '2016-04-05', NULL),
	(11, 15, 34, '2016-04-01', NULL),
	(12, 9, 16, '2016-04-09', NULL),
	(13, 9, 13, '2016-04-10', NULL),
	(14, 9, 28, '2016-04-02', NULL),
	(15, 14, 65, '2016-04-05', NULL),
	(16, 19, 36, '2016-04-10', NULL),
	(17, 6, 7, '2016-04-06', NULL),
	(18, 14, 97, '2016-04-03', NULL),
	(19, 17, 90, '2016-04-02', NULL),
	(20, 11, 95, '2016-04-07', NULL),
	(21, 6, 62, '2016-04-03', NULL),
	(22, 5, 9, '2016-04-07', NULL),
	(23, 15, 87, '2016-04-07', NULL),
	(24, 20, 34, '2016-04-05', NULL),
	(25, 7, 41, '2016-04-06', NULL),
	(26, 4, 78, '2016-04-10', NULL),
	(27, 13, 87, '2016-04-04', NULL),
	(28, 20, 16, '2016-04-01', NULL),
	(29, 7, 8, '2016-04-03', NULL),
	(30, 16, 17, '2016-04-10', NULL),
	(31, 14, 34, '2016-04-09', NULL),
	(32, 1, 31, '2016-04-06', NULL),
	(33, 12, 90, '2016-04-08', NULL),
	(34, 17, 6, '2016-04-09', NULL),
	(35, 11, 99, '2016-04-05', NULL),
	(36, 7, 96, '2016-04-04', NULL),
	(37, 8, 21, '2016-04-04', NULL),
	(38, 16, 52, '2016-04-06', NULL),
	(39, 10, 45, '2016-04-09', NULL),
	(40, 10, 93, '2016-04-06', NULL),
	(41, 3, 1, '2016-04-21', '2016-04-21'),
	(42, 4, 3, '2016-04-21', '2016-04-21'),
	(43, 1, 1, '2016-04-22', '2016-04-22'),
	(44, 1, 1, '2016-04-22', '2016-04-22');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;



/*!40000 ALTER TABLE `usager` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
