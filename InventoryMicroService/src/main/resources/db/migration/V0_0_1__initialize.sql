CREATE TABLE inventory(sku_id bigint not null,inventory_on_hand integer not null,min_qty_req integer not null,price double not null,product_label varchar,product_name varchar,PRIMARY KEY(sku_id));

INSERT INTO inventory values(123,25,10,30,'product1','productname1');