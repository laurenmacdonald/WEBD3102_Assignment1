-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 22, 2024 at 07:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `to_do_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `taskId` int(11) NOT NULL,
  `taskName` varchar(100) NOT NULL,
  `dueDate` date NOT NULL,
  `category` varchar(150) NOT NULL,
  `completeStatus` tinyint(1) NOT NULL,
  `dateCreated` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`taskId`, `taskName`, `dueDate`, `category`, `completeStatus`, `dateCreated`) VALUES
(4, 'clean kitchen', '2024-02-14', 'high', 1, '2024-02-14'),
(5, 'get groceries', '2024-02-14', 'high', 1, '2024-02-14'),
(8, 'clean yard', '2024-02-17', 'medium', 1, '2024-02-14'),
(9, 'laundry', '2024-02-17', 'low', 1, '2024-02-14'),
(10, 'get coffee', '2024-02-14', 'low', 1, '2024-02-14'),
(11, 'drink water', '2024-02-14', 'low', 1, '2024-02-14'),
(12, 'buy flowers', '2024-02-16', 'high', 0, '2024-02-16'),
(14, 'clean kitchen', '2024-02-23', 'high', 1, '2024-02-21'),
(15, 'do j2ee homework', '2024-02-21', 'high', 1, '2024-02-21'),
(16, 'do c# homework', '2024-02-23', 'high', 0, '2024-02-21'),
(18, 'follow up with job 1', '2024-02-21', 'high', 1, '2024-02-21'),
(19, 'Fix css', '2024-02-24', 'high', 1, '2024-02-21'),
(20, 'get pizza', '2024-03-02', 'high', 0, '2024-02-21'),
(22, 'find keys', '2024-02-26', 'high', 1, '2024-02-21'),
(23, 'email employee about job', '2024-02-25', 'low', 1, '2024-02-21'),
(25, 'car payment', '2024-02-12', 'high', 0, '2024-02-21'),
(26, 'oil payment', '2024-02-11', 'high', 0, '2024-02-21'),
(27, 'clean gutters', '2024-02-09', 'low', 1, '2024-02-21'),
(28, 'run 3km', '2024-02-23', 'high', 0, '2024-02-21'),
(31, 'create technical document', '2024-02-24', 'high', 0, '2024-02-21'),
(32, 'clean basement', '2024-02-05', 'low', 0, '2024-02-22'),
(33, 'drink water', '2024-02-22', 'high', 0, '2024-02-22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`taskId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `taskId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
