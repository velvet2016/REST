insert into PRODUCT (product_name, product_price) values ('banana', 100.5);
insert into PRODUCT (product_name, product_price) values ('apple', 15.9);
insert into PRODUCT (product_name, product_price) values ('orange', 29);
insert into PRODUCT (product_name, product_price) values ('mango', 50);
insert into PRODUCT (product_name, product_price) values ('chery', 60);
insert into PRODUCT (product_name, product_price) values ('kiwi', 40.9);
--
insert into PURCHASE (product_id, quantity, purchase_date) values (1, 1, TO_DATE('04/30/2020', '%m/%d/%Y') );
insert into PURCHASE (product_id, quantity, purchase_date) values (2, 2, TO_DATE('04/30/2004', '%m/%d/%Y') );
insert into PURCHASE (product_id, quantity, purchase_date) values (3, 10, TO_DATE('04/29/2004', '%m/%d/%Y') );
insert into PURCHASE (product_id, quantity, purchase_date) values (4, 10, TO_DATE('04/29/2016', '%m/%d/%Y') );
insert into PURCHASE (product_id, quantity, purchase_date) values (5, 10, TO_DATE('04/29/2016', '%m/%d/%Y') );
insert into PURCHASE (product_id, quantity, purchase_date) values (3, 10, TO_DATE('05/29/2016', '%m/%d/%Y') );
insert into PURCHASE (product_id, quantity, purchase_date) values (5, 10, TO_DATE('06/29/2016', '%m/%d/%Y') );