package com.belean.payday.transaction;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/9
 */
public class PayCheck {

    private Date payDate;
    private double grossPay;
    private double deductions;
    private double netPay;
    private Date payPeriodEndDate;
    private String disposition;

    public PayCheck(Date payDate) {
        this.payDate = payDate;
    }

    public Date getPayDate() {
        return this.payDate;
    }

    public double getGrossPay() {
        return this.grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getDeductions() {
        return this.deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetPay() {
        return this.netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setPayPeriodEndDate(Date payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public Date getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getDisposition() {
        return disposition;
    }

    @Override
    public boolean equals(Object obj) {
        PayCheck payCheck = (PayCheck)obj;
        return this.payDate.equals(payCheck.getPayDate()) && this.payPeriodEndDate.equals(payCheck.getPayPeriodEndDate());
    }

    @Override
    public int hashCode() {
        return this.payDate.hashCode() + this.payPeriodEndDate.hashCode();
    }
}
