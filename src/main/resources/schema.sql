CREATE TABLE PRODUCT(
product_id INT NOT NULL AUTO_INCREMENT,
product_name VARCHAR(500) NOT NULL,
product_price DOUBLE(50) NOT NULL,
PRIMARY KEY (product_id),
UNIQUE (product_name)
);

CREATE TABLE PURCHASE(
purchase_id INT NOT NULL AUTO_INCREMENT,
product_id INT NOT NULL,
quantity INT NOT NULL,
purchase_date DATETIME NOT NULL,
PRIMARY KEY (purchase_id),
FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id)
);