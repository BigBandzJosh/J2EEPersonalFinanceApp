CREATE DATABASE IF NOT EXISTS `FinanceApp`;

USE `FinanceApp`;

CREATE TABLE IF NOT EXISTS `users`
(
    `userid`         int(11)      NOT NULL AUTO_INCREMENT,
    `username`   varchar(255) NOT NULL,
    `password`   varchar(255) NOT NULL,
    `email`      varchar(255) NOT NULL,
    `first_name` varchar(255) NOT NULL,
    `last_name`  varchar(255) NOT NULL,
    `DateOfBirth` date        NOT NULL,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`userid`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`)
    );

CREATE TABLE IF NOT EXISTS `accounts`
(
    `accountid`          int(11)      NOT NULL AUTO_INCREMENT,
    `user_id`     int(11)      NOT NULL,
    `accountName` varchar(255) NOT NULL,
    `balance`     decimal(10,2) NOT NULL,
    `created_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`accountid`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`)
    );

CREATE TABLE IF NOT EXISTS `transactions`
(
    `transactionid` int(11)      NOT NULL AUTO_INCREMENT,
    `account_id`    int(11)      NOT NULL,
    `amount`        decimal(10,2) NOT NULL,
    `type`          varchar(255) NOT NULL,
    `description`   varchar(255) NOT NULL,
    `notes`         varchar(255) NOT NULL,
    `created_at`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`transactionid`),
    FOREIGN KEY (`account_id`) REFERENCES `accounts` (`accountid`)
    );

CREATE TABLE IF NOT EXISTS `categories`
(
    `categoryid` int(11)      NOT NULL AUTO_INCREMENT,
    `user_id`    int(11)      NOT NULL,
    `category`   varchar(255) NOT NULL,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`categoryid`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`)
    );

CREATE TABLE IF NOT EXISTS `budgets`
(
    `budgetid`    int(11)      NOT NULL AUTO_INCREMENT,
    `user_id`     int(11)      NOT NULL,
    `category_id` int(11)      NOT NULL,
    `amount`      decimal(10,2) NOT NULL,
    `period`      varchar(255) NOT NULL,
    `created_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`budgetid`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`),
    FOREIGN KEY (`category_id`) REFERENCES `categories` (`categoryid`)
    );
