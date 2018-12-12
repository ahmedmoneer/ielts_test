-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ielts_db
CREATE DATABASE IF NOT EXISTS `ielts_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ielts_db`;

-- Dumping structure for table ielts_db.candidate
CREATE TABLE IF NOT EXISTS `candidate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CANDIATE_NAME` varchar(50) DEFAULT NULL,
  `CANDIDATE_NUM` varchar(50) NOT NULL,
  `TEST_DATE` varchar(50) NOT NULL,
  `BIRTH_DATE` varchar(50) NOT NULL,
  `DOC_NUM` varchar(50) NOT NULL,
  `LISTENING` varchar(50) NOT NULL,
  `SPEAKING` varchar(50) NOT NULL,
  `READING` varchar(50) NOT NULL,
  `WRITING` varchar(50) NOT NULL,
  `OVERALL` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table ielts_db.dates
CREATE TABLE IF NOT EXISTS `dates` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDATE` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
