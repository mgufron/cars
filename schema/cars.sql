SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
--
-- Database: `cars`
--
CREATE DATABASE IF NOT EXISTS `cars` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cars`;

-- --------------------------------------------------------

--
-- Table structure for table `car_collection`
--

CREATE TABLE IF NOT EXISTS `car_collection` (
  `car_id` int(10) NOT NULL AUTO_INCREMENT,
  `car_name` varchar(100) NOT NULL,
  `car_type` varchar(100) NOT NULL,
  `car_saved` datetime NOT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `car_name` (`car_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

