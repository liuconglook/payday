package com.belean.payroll.transaction;

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
    private Date payPeriodStartDate;
    private String disposition;

    public PayCheck(Date payPeriodStartDate, Date payDate) {
        this.payPeriodStartDate = payPeriodStartDate;
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

    public Date getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getDisposition() {
        return disposition;
    }
}
