CREATE DATABASE IF NOT EXISTS 'inventory-service';

CREATE TABLE "t_inventory"(
   ID SERIAL PRIMARY KEY,
   sku_code  CHAR(50),
   QUANTITY  INT
);