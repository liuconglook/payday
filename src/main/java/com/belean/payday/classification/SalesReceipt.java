package com.belean.payday.classification;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class SalesReceipt {

    private double sales;
    private Date date;

    public SalesReceipt(double sales, Date date) {
        this.sales = sales;
        this.date = date;
    }

    public double getSales() {
        return sales;
    }

    public Date getDate() {
        return date;
    }
}
