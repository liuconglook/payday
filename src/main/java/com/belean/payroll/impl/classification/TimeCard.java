package com.belean.payroll.impl.classification;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class TimeCard {

    private Date date;
    private double hours;

    public TimeCard(Date date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public Date getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }
}
