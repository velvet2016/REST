package com.lux.task.dao.models;

import java.util.Date;

import static com.lux.task.Constants.SIMPLE_DATE_FORMAT;

public class ReportLine extends Purchase{
    public ReportLine() {
    }

    public ReportLine(Integer id, Product product, Integer quantity, Date purchaseDate, double sum) {
        super(id, product, quantity, purchaseDate);
        this.sum = sum;
    }

    public ReportLine(Purchase purchase) {
        super(purchase.id, purchase.product, purchase.quantity, purchase.purchaseDate);
        this.sum = product.getPrice()*purchase.getQuantity();
    }

    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportLine)) return false;
        if (!super.equals(o)) return false;

        ReportLine that = (ReportLine) o;

        return Double.compare(that.sum, sum) == 0;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ReportLine{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", purchaseDate=" + SIMPLE_DATE_FORMAT.format(purchaseDate) +
                ", sum=" + sum +
                '}';
    }
}
