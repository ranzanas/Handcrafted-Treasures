-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2025 at 06:23 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `handcraftedtreasures`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cartId` int(11) NOT NULL,
  `userId` int(50) NOT NULL,
  `productId` int(30) NOT NULL,
  `cartCreatedDate` date NOT NULL,
  `cartProductQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cartId`, `userId`, `productId`, `cartCreatedDate`, `cartProductQuantity`) VALUES
(442, 130, 202, '2025-05-21', 1);

-- --------------------------------------------------------

--
-- Table structure for table `feedbacks`
--

CREATE TABLE `feedbacks` (
  `feedbackId` int(50) NOT NULL,
  `userId` int(50) NOT NULL,
  `feedbackDate` date NOT NULL,
  `feedbackDescription` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `orderQuantity` int(50) NOT NULL,
  `orderDate` date NOT NULL,
  `deliveryAddress` varchar(50) NOT NULL,
  `totalAmount` double NOT NULL,
  `paymentMethod` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `orderQuantity`, `orderDate`, `deliveryAddress`, `totalAmount`, `paymentMethod`) VALUES
(307, 2, '2025-05-20', 'Kamalpokhari', 250, 'Card'),
(308, 1, '2025-05-20', 'nkjnjkj', 300, 'Cash on Delivery'),
(309, 4, '2025-05-20', 'Kamalpokhari', 500, 'Cash on Delivery'),
(310, 1, '2025-05-21', 'Kathmandu', 1500, 'Card'),
(311, 2, '2025-05-21', 'Kathmandu', 950, 'Bank Transfer'),
(312, 2, '2025-05-21', 'ktm', 1650, 'Card');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productName` varchar(100) NOT NULL,
  `productId` int(30) NOT NULL,
  `productDescription` varchar(255) NOT NULL,
  `productPrice` float NOT NULL,
  `productQuantity` int(50) NOT NULL,
  `productStatus` varchar(50) NOT NULL,
  `productImage` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`productName`, `productId`, `productDescription`, `productPrice`, `productQuantity`, `productStatus`, `productImage`) VALUES
('Floral Hat KeyChain', 201, 'A cute, handcrafted keychain styled like a tiny hat adorned with floral accents.', 150, 10, 'Available', 'floralhatkeychain.jpg'),
('Floral Bookmark', 202, 'A delicate and vibrant floral-themed bookmark to brighten your reading moments.', 100, 5, 'In-Stock', 'floralbookmark.jpg'),
('Cherry Key Chain', 203, 'A playful and charming cherry-inspired keychain to sweeten up your keys or bags.', 150, 5, 'In-Stock', 'cherrykeychain.jpg'),
('Half Demon Half God', 204, 'A strong character drawing that mixes both dark and god-like features.', 1500, 7, 'In-Stock', 'halfdemonhalfgod.jpg'),
('Customized Portrait', 206, 'Hand-drawn personalized portrait - perfect for gifts.', 1500, 0, 'Out-of-Stock', 'customizedPortrait.jpg'),
('Unicorn Painting', 208, 'Magical unicorn painting with vibrant\r\ncolors.', 900, 8, 'In-Stock', 'unicornPainting.jpg'),
('Guitar Bookmark', 209, 'Cute handmade bookmarks for your reading moments.', 100, 20, 'In-Stock', 'guitarBookmark.jpg'),
('Blue dreamcatcher keychain', 210, 'Mini dreamcatcher keychain to ward off bad vibes.', 150, 15, 'In-Stock', 'blueDreamCatcherKeyChain.jpg'),
('Luffy hat keychain', 211, 'Inspired by One Piece - Luffy’s iconic straw hat keychain.', 150, 10, 'In-Stock', 'luffyHatKeyChain.png'),
('Lily bouquet', 212, 'Elegant paper lily bouquet with natural colors.', 850, 6, 'In-Stock', 'lilyBouquet.jpg'),
('Tulip bouquet', 213, 'Bright and colorful tulip bouquet to bring joy.', 300, 12, 'In-Stock', 'tulipBpquet.jpg'),
('Evil eye dreamcatcher keychain', 214, 'Traditional design to protect from negative energy.', 150, 14, 'In-Stock', 'blueEvilEyeDreamCatcher.jpg'),
('Scenery painting', 215, 'Peaceful landscape scenery for home decor.', 1200, 1, 'In-Stock', 'scenary_painting.jpg'),
('Pet portrait', 216, 'Custom hand-painted portrait of your furry friend.', 1000, 4, 'In-Stock', 'petPortrait.jpg'),
('Camping painting', 217, 'Scenic camping night in watercolor style.', 2000, 2, 'In-Stock', 'scenary painting.jpg'),
('Floral Bookmark 4pcs set', 218, 'A delicate and vibrant floral-themed bookmark to brighten your reading moments.', 400, 4, 'In-Stock', 'floralbookmark.jpg'),
('Aquamarine Beads Mala', 219, 'Beads for Tranquility and Courage.', 2000, 2, 'In-Stock', 'AquamarineMala.jpg'),
('Green Aventurine Beads Mala', 220, 'Beads for Prosperity and Healing.', 1500, 9, 'In-Stock', 'greenAventurine.jpg'),
('Mookaite Stone Mala', 221, 'Beads for Grounding and Vitality.', 1500, 5, 'In-Stock', 'MookaiteStoneMala.jpg'),
('Opalite Stone Mala', 222, 'Beads Meditation Necklace.', 1350, 7, 'In-Stock', 'opaliteStonemala.jpg'),
('Rose Quartz Stone Mala', 223, 'Beads for love and healing.', 1000, 4, 'In-Stock', 'roseQuartz stone mala.jpg'),
('Turquoise Stone Mala', 224, 'Beads for protection and healing.', 1550, 9, 'In-Stock', 'turquoiseStonemala.jpg'),
('Rudraksha Mala', 228, 'The mala for chanting mantras', 500, 15, 'In Stock', 'rudraksha.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `userprodord`
--

CREATE TABLE `userprodord` (
  `userId` int(50) NOT NULL,
  `orderId` int(50) NOT NULL,
  `productId` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `userFullName` varchar(50) NOT NULL,
  `userAddress` varchar(50) NOT NULL,
  `userDOB` date NOT NULL,
  `userEmail` varchar(50) NOT NULL,
  `userPhone` varchar(30) NOT NULL,
  `user_userName` varchar(50) NOT NULL,
  `userPassword` varchar(150) NOT NULL,
  `userRole` varchar(30) NOT NULL DEFAULT 'Customer',
  `userImagePath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `userFullName`, `userAddress`, `userDOB`, `userEmail`, `userPhone`, `user_userName`, `userPassword`, `userRole`, `userImagePath`) VALUES
(109, 'Amisha K.C.', 'Gongabu', '2000-10-17', 'amishakarki@gmail.com', '+9779842557399', 'amisha02', 'tSOhNU5lrjsMos8mUm/CHMLoo4/s+ApcMYZkDP/QetvaLV0sTRbDM6qSXquPPL2uyYiVp6E=', 'Customer', 'resources/images/profile_images/photo.jpg'),
(110, 'Swoyam Manandhar', 'Baneshwor', '2005-06-07', 'swomdr@gmail.com', '+9779813410110', 'admin01', 'S8sy4zVWFUmXNK4wf0vWSRh6Hex8my9YZazJ6d3QLu93sj46OLGlPKQBeIOnbgQ00fWXGc4=', 'Admin', ''),
(111, 'Rejina Shrestha', 'Lalitpur', '2000-10-25', 'rejinashrestha@gmail.com', '+9779842557399', 'rejina01', '8wXeUqdaeW1R3TUAVp9xE7u2L23gm7F0X11zyKXi6w2fBWQWkJA3DS8V8YpHP8TRYEWBoXM=', 'Customer', 'resources/images/profile_images/photo.jpg'),
(114, 'Unique Basnet', 'Kathmandu', '2002-06-20', 'uniquebasnet@gmail.com', '+9779842557355', 'unique02', '1jHLy70laKKQO2riOOr08+e2fmQGcsJy/zvQjTKaqlzrV61/HX5+cEK0/7iejs4ZlhbptJI=', 'Customer', 'resources/images/profile_images/photo.jpg'),
(130, 'Rosy Shrestha', 'Kathmandu', '2002-06-16', 'rosyshrestha@gmail.com', '+9779840521219', 'rosyshres2', 'TBNtUGYxiRFID1oFA2CxJFAwCRXcS+soeYG1zSc+bgWPkbTBioXcVWU7Lf+mqQkvH+ekR/iK9w==', 'Customer', 'resources/img/people/customizedPortrait.jpg'),
(133, 'Ranjana Silwal', 'Kathmandu', '2004-05-10', 'silwalranzana@gmail.com', '+9779842557312', 'ranjana01', 'YoJwsLFfd8NXMEcxZpIIOzobsMTbguaYZfLuLbIGKn3VgMKk6SHTpznmJT+uf07MYBrM2i0b+p8=', 'Customer', 'customizedPortrait.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cartId`),
  ADD KEY `users_cart_fk` (`userId`),
  ADD KEY `fk_cart_productId` (`productId`);

--
-- Indexes for table `feedbacks`
--
ALTER TABLE `feedbacks`
  ADD PRIMARY KEY (`feedbackId`),
  ADD KEY `users_feedbacks_fk` (`userId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `userprodord`
--
ALTER TABLE `userprodord`
  ADD KEY `users_UserProdOrd_fk` (`userId`),
  ADD KEY `orders_UserProdOrd_fk` (`orderId`),
  ADD KEY `fk_userProdOrd_productId` (`productId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cartId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=443;

--
-- AUTO_INCREMENT for table `feedbacks`
--
ALTER TABLE `feedbacks`
  MODIFY `feedbackId` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=510;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=313;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `productId` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=229;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=134;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_productId` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `users_cart_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Constraints for table `feedbacks`
--
ALTER TABLE `feedbacks`
  ADD CONSTRAINT `users_feedbacks_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);

--
-- Constraints for table `userprodord`
--
ALTER TABLE `userprodord`
  ADD CONSTRAINT `fk_userProdOrd_productId` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_UserProdOrd_fk` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`),
  ADD CONSTRAINT `users_UserProdOrd_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
