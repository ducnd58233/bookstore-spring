CREATE DATABASE IF NOT EXISTS `book_store`;
USE `book_store`;

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(255),
    `year` INT,
    `description` TEXT,
    `price` DOUBLE,
    `status` INT NOT NULL DEFAULT '1',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO `books` (`title`, `year`, `description`, `price`, `status`, `created_at`, `updated_at`) VALUES
('Book 1', 1978, 'This is book 1 test', 200000, 1, '2022-11-04 01:30:53', '2022-11-04 01:30:53'),
('Book 2', 2000, 'This is book 2 test', 120000, 1, '2022-11-04 01:30:54', '2022-11-04 01:30:54'),
('Book 3', 1936, 'This is book 3 test', 380000, 1, '2022-11-04 01:30:55', '2022-11-04 01:30:55'),
('Book 4', 1999, 'This is book 4 test', 400000, 1, '2022-11-04 01:30:56', '2022-11-04 01:30:56'),
('Book 5', 1956, 'This is book 5 test', 230000, 1, '2022-11-04 01:30:57', '2022-11-04 01:30:57');