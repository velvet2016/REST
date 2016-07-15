CREATE TABLE PURCHASE(
purchase_id INT NOT NULL AUTO_INCREMENT,
product_id INT NOT NULL,
quantity INT NOT NULL,
purchase_date DATE NOT NULL,
PRIMARY KEY (purchase_id),
FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id)
)