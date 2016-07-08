package com.lux.task.dao.models;

public class ReportLine extends Purchase{
    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "{" +
                "productName=" + productName  +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate  +
                ", sum=" + sum +
                "}";
    }
}
