-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 17, 2021 at 07:17 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id17301733_fms`
--

-- --------------------------------------------------------

--
-- Table structure for table `ADMIN`
--

CREATE TABLE `ADMIN` (
  `ID` int(11) NOT NULL,
  `Username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ADMIN`
--

INSERT INTO `ADMIN` (`ID`, `Username`, `Password`) VALUES
(1, 'Admin123', 'Teacher'),
(3, 'FMS123', '123');

-- --------------------------------------------------------

--
-- Table structure for table `Fee_Details`
--

CREATE TABLE `Fee_Details` (
  `fee id` int(11) NOT NULL,
  `Invoice_number` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Student_ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Tution_Fee` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Sports_Fee` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `It_Fee` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Exam_Fee` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Book_Fee` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Stationary_Fee` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Due_Date` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Status` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Fee_Details`
--

INSERT INTO `Fee_Details` (`fee id`, `Invoice_number`, `Student_ID`, `Tution_Fee`, `Sports_Fee`, `It_Fee`, `Exam_Fee`, `Book_Fee`, `Stationary_Fee`, `Due_Date`, `Status`) VALUES
(131, '111229', 'STD22', '2', '367', '5', '2466', '56', '56', '2021/9/15', 'Pending'),
(138, '143879', 'STD22', '200', '50', '350', '250', '25', '5', '2021/8/19', 'Paid'),
(139, '94662', 'STD22', '5', '5', '5', '5', '5', '5', '2021/8/18', 'Pending'),
(140, '91125', 'STD29', '2000', '235', '235', '25000', '258', '23', '2021/8/19', 'Update');

-- --------------------------------------------------------

--
-- Table structure for table `Students_Details`
--

CREATE TABLE `Students_Details` (
  `id` int(11) NOT NULL,
  `Student_ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `First_Name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Last_Name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Students_Details`
--

INSERT INTO `Students_Details` (`id`, `Student_ID`, `First_Name`, `Last_Name`, `Username`, `Email`, `Password`) VALUES
(22, 'STD22', 'Farhan', 'Ali', 'Farhanali', 'Farhan@gmail.com', 'Computer4'),
(29, 'STD29', 'salma', 'tahir', 'salma Tahir', 'salma@gmail.com', '231');

-- --------------------------------------------------------

--
-- Table structure for table `validation_A`
--

CREATE TABLE `validation_A` (
  `id` int(11) NOT NULL,
  `Student_ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `A1` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ADMIN`
--
ALTER TABLE `ADMIN`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `Fee_Details`
--
ALTER TABLE `Fee_Details`
  ADD PRIMARY KEY (`fee id`),
  ADD KEY `Student_ID` (`Student_ID`);

--
-- Indexes for table `Students_Details`
--
ALTER TABLE `Students_Details`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Student_ID` (`Student_ID`);

--
-- Indexes for table `validation_A`
--
ALTER TABLE `validation_A`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Student_ID` (`Student_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ADMIN`
--
ALTER TABLE `ADMIN`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `Fee_Details`
--
ALTER TABLE `Fee_Details`
  MODIFY `fee id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

--
-- AUTO_INCREMENT for table `Students_Details`
--
ALTER TABLE `Students_Details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `validation_A`
--
ALTER TABLE `validation_A`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Fee_Details`
--
ALTER TABLE `Fee_Details`
  ADD CONSTRAINT `Fee_Details_ibfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `Students_Details` (`Student_ID`);

--
-- Constraints for table `validation_A`
--
ALTER TABLE `validation_A`
  ADD CONSTRAINT `validation_A_ibfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `Students_Details` (`Student_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
