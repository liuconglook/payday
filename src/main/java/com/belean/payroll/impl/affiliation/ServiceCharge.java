package com.belean.payroll.impl.affiliation;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class ServiceCharge {
    private double charge;
    private Date date;

    public ServiceCharge(double charge, Date date) {
        this.charge = charge;
        this.date = date;
    }

    public double getCharge() {
        return charge;
    }

    public Date getDate() {
        return date;
    }
}
