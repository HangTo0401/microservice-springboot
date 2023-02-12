CREATE DATABASE IF NOT EXISTS 'order-service';

'mysql -uroot -p1234'

USE order-service

SHOW FULL TABLES;

CREATE TABLE `t_order_line_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_orders_order_line_items_list` (
  `order_id` bigint NOT NULL,
  `order_line_items_list_id` bigint NOT NULL,
  UNIQUE KEY `UK_nqpoocsk2utvq7va8bgth1mj9` (`order_line_items_list_id`),
  KEY `FK3wdgw7hxmodjqfulfaymc8aof` (`order_id`),
  CONSTRAINT `FK1qq155yd0omg8y9in6526bhaj` FOREIGN KEY (`order_line_items_list_id`) REFERENCES `t_order_line_items` (`id`),
  CONSTRAINT `FK3wdgw7hxmodjqfulfaymc8aof` FOREIGN KEY (`order_id`) REFERENCES `t_orders` (`id`)
);