package com.lux.task.dao.models;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonAutoDetect
public class Purchase {

    protected String productName;
    @Min(value=1)
    protected int quantity;
    protected Date purchaseDate;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String produtcName) {
        this.productName = produtcName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }
}
