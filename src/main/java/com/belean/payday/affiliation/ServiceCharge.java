package com.belean.payday.affiliation;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class ServiceCharge {
    private int memberId;
    private double charge;
    private Date date;

    public ServiceCharge(int memberId, double charge, Date date) {
        this.memberId = memberId;
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
