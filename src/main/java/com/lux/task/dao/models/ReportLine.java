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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportLine that = (ReportLine) o;

        return Double.compare(that.sum, sum) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(sum);
        return (int) (temp ^ (temp >>> 32));
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
