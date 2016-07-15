package com.lux.task.dao.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lux.task.Constants;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.lux.task.Constants.*;

@JsonAutoDetect
public class Purchase {
    public Purchase() {
    }

    public Purchase(Product product, Integer quantity, Date purchaseDate) {
        this.product = product;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public Purchase(Long id, Product product, Integer quantity, Date purchaseDate) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    protected Long id;
    @NotNull
    protected Product product;

    @Min(value=1)
    @NotNull
    protected Integer quantity;

    @DateTimeFormat(pattern= DATE_FORMAT)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= DATE_FORMAT)
    @NotNull
    protected Date purchaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
