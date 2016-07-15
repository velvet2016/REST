package com.lux.task.dao.models;

import java.util.Date;

public class ReportLine extends Purchase{
    public ReportLine() {
    }

    public ReportLine(Long id, Product product, Integer quantity, Date purchaseDate, double sum) {
        super(id, product, quantity, purchaseDate);
        this.sum = sum;
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
                ", purchaseDate=" + purchaseDate +
                ", sum=" + sum +
                '}';
    }
}
