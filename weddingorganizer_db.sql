-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2025 at 01:27 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `weddingorganizer_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id_booking` int(11) NOT NULL,
  `id_vendor` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  `nama_customer` varchar(255) DEFAULT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL,
  `agama` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `no_hp` varchar(255) DEFAULT NULL,
  `nama_vendor` varchar(255) DEFAULT NULL,
  `nama_paket` varchar(255) DEFAULT NULL,
  `harga` decimal(20,2) DEFAULT NULL,
  `tanggal_reservasi` date DEFAULT NULL,
  `status` enum('Belum Lunas','Sudah Lunas','Cancel') DEFAULT 'Belum Lunas',
  `sisa` decimal(20,2) DEFAULT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id_booking`, `id_vendor`, `id_customer`, `nama_customer`, `jenis_kelamin`, `agama`, `alamat`, `no_hp`, `nama_vendor`, `nama_paket`, `harga`, `tanggal_reservasi`, `status`, `sisa`, `deskripsi`) VALUES
(1, 10, 9, 'CICI', 'PEREMPUAN', 'ISLAM', 'JAKARTA', '1234567890', 'cintia wedding', 'a', '1000000.00', '2024-09-09', 'Belum Lunas', NULL, 'banyak hal yang harus diisi');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` int(11) NOT NULL,
  `id_booking` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_vendor` int(11) NOT NULL,
  `status` enum('Belum Lunas','Sudah Lunas') NOT NULL,
  `sisa` double NOT NULL,
  `tanggal_pembayaran` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Customer','Vendor','Admin') NOT NULL,
  `no_telp` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `username`, `password`, `role`, `no_telp`) VALUES
(1, 'Surya Erlamba', 'sryaerlmba', 'Surya729431', 'Customer', NULL),
(2, 'sryaerlmba', 'vendor', 'vendor123', 'Vendor', NULL),
(3, 'surya', 'vendor123', 'vendor123', 'Vendor', NULL),
(4, 'cintami dewi', 'cinta56', '1234', 'Customer', NULL),
(5, 'Eklesiaa2000', 'Ekle----', 'EKLE12345', 'Customer', NULL),
(6, '', 'admin', 'admin123', 'Admin', NULL),
(7, 'sryaerlmba', 'Surya729431', 'Surya729431', 'Customer', NULL),
(8, 'Cintami', 'cintami21', 'cintamiapa', 'Customer', NULL),
(9, 'cici', 'cici', '123', 'Customer', NULL),
(10, 'cici', 'cintia', '123', 'Vendor', NULL),
(11, 'Surya Erlamba', 'srya', 'Surya729431', 'Customer', '089614028027');

-- --------------------------------------------------------

--
-- Table structure for table `vendors`
--

CREATE TABLE `vendors` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `nama_vendor` varchar(100) NOT NULL,
  `nama_paket` varchar(100) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `harga` decimal(20,0) DEFAULT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vendors`
--

INSERT INTO `vendors` (`id`, `user_id`, `nama_vendor`, `nama_paket`, `no_hp`, `harga`, `deskripsi`) VALUES
(10, 2, 'surya', 'suryaerlmba', '089614028027', '5000999', 'aku sayang kamu'),
(11, 10, 'cintia wedding', 'a', '12345678', '1000000', 'banyak hal yang harus diisi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id_booking`),
  ADD KEY `id_vendor` (`id_vendor`),
  ADD KEY `id_customer` (`id_customer`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `id_booking` (`id_booking`),
  ADD KEY `id_customer` (`id_customer`),
  ADD KEY `id_vendor` (`id_vendor`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `vendors`
--
ALTER TABLE `vendors`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id_booking` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `vendors`
--
ALTER TABLE `vendors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`id_vendor`) REFERENCES `vendors` (`user_id`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`id_customer`) REFERENCES `users` (`id`);

--
-- Constraints for table `vendors`
--
ALTER TABLE `vendors`
  ADD CONSTRAINT `vendors_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
