package com.lux.task.dao.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonAutoDetect
public class Purchase {


    private static final String DATE_FORMAT = "dd.MM.yyyy";

    protected String productName;
    @Min(value=1)
    @NotNull
    protected Integer quantity;

    @DateTimeFormat(pattern=DATE_FORMAT)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=DATE_FORMAT)
    @NotNull
    protected Date purchaseDate;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String produtcName) {
        this.productName = produtcName;
    }

    public Integer getQuantity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (!productName.equals(purchase.productName)) return false;
        if (!quantity.equals(purchase.quantity)) return false;
        return purchaseDate.equals(purchase.purchaseDate);

    }

    @Override
    public int hashCode() {
        int result = productName.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + purchaseDate.hashCode();
        return result;
    }
}
