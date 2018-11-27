-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2018 at 05:18 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `suki`
--

-- --------------------------------------------------------

--
-- Table structure for table `barangays`
--

CREATE TABLE `barangays` (
  `id` int(11) NOT NULL,
  `municipality_id` int(11) DEFAULT NULL,
  `barangay_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barangays`
--

INSERT INTO `barangays` (`id`, `municipality_id`, `barangay_name`) VALUES
(1, 1, 'Taglatawan'),
(2, 1, 'Poblacion'),
(3, 2, 'Barangay1'),
(4, 2, 'Barangay2');

-- --------------------------------------------------------

--
-- Table structure for table `municipalities`
--

CREATE TABLE `municipalities` (
  `id` int(11) NOT NULL,
  `municipality_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `municipalities`
--

INSERT INTO `municipalities` (`id`, `municipality_name`) VALUES
(1, 'Bayugan City'),
(2, 'Manila City');

-- --------------------------------------------------------

--
-- Table structure for table `product_postings`
--

CREATE TABLE `product_postings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `post_type` int(11) DEFAULT NULL,
  `quantity_type` int(11) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `quantity` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `product_photo` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_postings`
--

INSERT INTO `product_postings` (`id`, `user_id`, `post_type`, `quantity_type`, `category`, `status`, `product_name`, `quantity`, `price`, `product_description`, `product_photo`, `created_at`, `updated_at`) VALUES
(1, 1, 4, 6, 9, 10, 'Kamatis', '150.00', '60.50', 'Bagong Pitas po bosing.', NULL, '2018-10-31 16:00:00', '2018-11-23 20:00:00'),
(2, 1, 5, 7, 8, 11, 'Rice', '100.00', '21.00', 'Aanihin pa bosing kaya fresh na fresh...', NULL, '2018-11-24 14:50:59', '2018-11-24 14:50:59');

-- --------------------------------------------------------

--
-- Table structure for table `system_codes`
--

CREATE TABLE `system_codes` (
  `id` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `VALUE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `system_codes`
--

INSERT INTO `system_codes` (`id`, `category`, `VALUE`) VALUES
(1, 'USER_TYPE', 'SUPPLIER'),
(2, 'USER_TYPE', 'BUYER'),
(3, 'USER_TYPE', 'INDIVIDUAL'),
(4, 'POST_TYPE1', 'POST_TYPE_VALUE_1'),
(5, 'POST_TYPE2', 'POST_TYPE_VALUE_2'),
(6, 'QUANTITY_TYPE', 'KILOGRAM'),
(7, 'QUANTITY_TYPE', 'PIECES'),
(8, 'CATEGORY', 'ROOT_CROPS'),
(9, 'CATEGORY', 'FRUITS'),
(10, 'STATUS', 'SOLD'),
(11, 'STATUS', 'UNSOLD');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user_type` int(11) NOT NULL,
  `barangay_id` int(11) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `password` text,
  `birth_date` date DEFAULT NULL,
  `contact_number` varchar(50) DEFAULT NULL,
  `user_photo` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `user_type`, `barangay_id`, `first_name`, `middle_name`, `last_name`, `email`, `password`, `birth_date`, `contact_number`, `user_photo`) VALUES
(1, 1, 1, 'Christian', 'Luceno', 'Garillo', 'christian@gmail.com', '$2y$10$kbgYYOG231zFRatm5ZPeH.UXAnKZGQMxWHkDAqhR8nJhV29xxOuzO', '2018-11-08', '09055114055', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_business`
--

CREATE TABLE `user_business` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `business_name` varchar(100) DEFAULT NULL,
  `business_permit` varchar(100) DEFAULT NULL,
  `business_permit_photo` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_business`
--

INSERT INTO `user_business` (`id`, `user_id`, `business_name`, `business_permit`, `business_permit_photo`) VALUES
(1, 1, 'The Business', 'T-REX-123', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barangays`
--
ALTER TABLE `barangays`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barangay_municipality_id` (`municipality_id`);

--
-- Indexes for table `municipalities`
--
ALTER TABLE `municipalities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_postings`
--
ALTER TABLE `product_postings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_postings_user_id` (`user_id`),
  ADD KEY `product_postings_post_type` (`post_type`),
  ADD KEY `product_postings_quantity_type` (`quantity_type`),
  ADD KEY `product_postings_category` (`category`),
  ADD KEY `product_postings_status` (`status`);

--
-- Indexes for table `system_codes`
--
ALTER TABLE `system_codes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `users_user_type` (`user_type`),
  ADD KEY `users_barangay_id` (`barangay_id`);

--
-- Indexes for table `user_business`
--
ALTER TABLE `user_business`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_business_user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barangays`
--
ALTER TABLE `barangays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `municipalities`
--
ALTER TABLE `municipalities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product_postings`
--
ALTER TABLE `product_postings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `system_codes`
--
ALTER TABLE `system_codes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_business`
--
ALTER TABLE `user_business`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barangays`
--
ALTER TABLE `barangays`
  ADD CONSTRAINT `barangay_municipality_id` FOREIGN KEY (`municipality_id`) REFERENCES `municipalities` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `product_postings`
--
ALTER TABLE `product_postings`
  ADD CONSTRAINT `product_postings_category` FOREIGN KEY (`category`) REFERENCES `system_codes` (`id`),
  ADD CONSTRAINT `product_postings_post_type` FOREIGN KEY (`post_type`) REFERENCES `system_codes` (`id`),
  ADD CONSTRAINT `product_postings_quantity_type` FOREIGN KEY (`quantity_type`) REFERENCES `system_codes` (`id`),
  ADD CONSTRAINT `product_postings_status` FOREIGN KEY (`status`) REFERENCES `system_codes` (`id`),
  ADD CONSTRAINT `product_postings_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_barangay_id` FOREIGN KEY (`barangay_id`) REFERENCES `barangays` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `users_user_type` FOREIGN KEY (`user_type`) REFERENCES `system_codes` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_business`
--
ALTER TABLE `user_business`
  ADD CONSTRAINT `user_business_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
