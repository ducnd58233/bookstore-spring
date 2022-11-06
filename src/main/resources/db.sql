CREATE DATABASE IF NOT EXISTS `book_store`;
USE `book_store`;

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255),
    `year` INT,
    `description` text,
    `price` DOUBLE,
    `image` JSON NULL,
    `status` INT NOT NULL DEFAULT '1',
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `status` (`status`) USING BTREE
);

INSERT INTO `books` (`title`, `year`, `description`, `price`, `status`, `created_at`, `updated_at`) VALUES
('Book 1', 1978, 'This is book 1 test', 200000, 1, '2022-11-04 01:30:53', '2022-11-04 01:30:53'),
('Book 2', 2000, 'This is book 2 test', 120000, 1, '2022-11-04 01:30:54', '2022-11-04 01:30:54'),
('Book 3', 1936, 'This is book 3 test', 380000, 1, '2022-11-04 01:30:55', '2022-11-04 01:30:55'),
('Book 4', 1999, 'This is book 4 test', 400000, 1, '2022-11-04 01:30:56', '2022-11-04 01:30:56'),
('Book 5', 1956, 'This is book 5 test', 230000, 1, '2022-11-04 01:30:57', '2022-11-04 01:30:57');

DROP TABLE IF EXISTS `authens`;
CREATE TABLE `authens` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(155) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `status` INT NOT NULL DEFAULT '1',
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(50) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(20) DEFAULT NULL,
    `role` enum('user','admin') NOT NULL DEFAULT 'user',
    `avatar` JSON DEFAULT NULL,
    `status` INT NOT NULL DEFAULT '1',
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)
);

DROP TABLE IF EXISTS `book_likes`;
CREATE TABLE `book_likes` (
    `book_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`book_id`,`user_id`),
    KEY `book_id` (`book_id`)
);

CREATE TABLE IF NOT EXISTS `images` (
    `id` INT NOT NULL,
    `url` TEXT NOT NULL,
    `width` DOUBLE NOT NULL,
    `height` DOUBLE NOT NULL,
    `cloud_name` VARCHAR(120) NULL,
    `extension` VARCHAR(10) NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);