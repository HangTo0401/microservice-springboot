CREATE DATABASE IF NOT EXISTS 'inventory-service';

'Run this below command to login db inventory-service'
'psql -d inventory-service -U postgres'

CREATE TABLE "t_inventory"(
   ID SERIAL PRIMARY KEY,
   sku_code  CHAR(50),
   QUANTITY  INT
);

INSERT INTO "public"."t_inventory" values(1, 'iphone_13', 100);
INSERT INTO "public"."t_inventory" values(2, 'iphone_13_red', 0);