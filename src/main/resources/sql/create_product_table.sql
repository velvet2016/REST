CREATE TABLE PRODUCT(
product_id INT NOT NULL AUTO_INCREMENT,
product_name VARCHAR(500) NOT NULL,
product_price DOUBLE(50,2) NOT NULL,
PRIMARY KEY (product_id),
UNIQUE (product_name)
)