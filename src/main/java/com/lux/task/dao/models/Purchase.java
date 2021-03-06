package com.lux.task.dao.models;

import static com.lux.task.Constants.DATE_FORMAT;
import static com.lux.task.Constants.SIMPLE_DATE_FORMAT;
import static com.lux.task.Constants.TIMEZONE;
import static com.lux.task.Constants.UI_DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Calendar;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.time.DateUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.format.annotation.DateTimeFormat;

@JsonAutoDetect
public class Purchase {

    public Purchase() {
    }

    public Purchase(Product product, Integer quantity, Date purchaseDate) {
        this.product = product;
        this.quantity = quantity;
        setPurchaseDate(purchaseDate);
    }

    public Purchase(int productId, Integer quantity, Date purchaseDate) {
        this.product = new Product(productId);
        this.quantity = quantity;
        setPurchaseDate(purchaseDate);
    }

    public Purchase(int productId, Integer quantity) {
        this.product = new Product(productId);
        this.quantity = quantity;
        setPurchaseDate(purchaseDate);
    }

    public Purchase(Integer id, Product product, Integer quantity, Date purchaseDate) {
        this(product, quantity, purchaseDate);
        this.id = id;

    }

    protected Integer id;
    @NotNull
    protected Product product;

    @Min(value=1)
    @NotNull
    protected Integer quantity;

    @DateTimeFormat(pattern= UI_DATE_FORMAT)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= DATE_FORMAT, timezone= TIMEZONE)
    @NotNull
    protected Date purchaseDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        this.purchaseDate = DateUtils.truncate(purchaseDate, Calendar.SECOND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;

        Purchase purchase = (Purchase) o;

        if (product != null ? !product.equals(purchase.product) : purchase.product != null) return false;
        if (quantity != null ? !quantity.equals(purchase.quantity) : purchase.quantity != null) return false;
        return purchaseDate != null ? purchaseDate.equals(purchase.purchaseDate) : purchase.purchaseDate == null;

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", purchaseDate=" + SIMPLE_DATE_FORMAT.format(purchaseDate) +
                '}';
    }

}
