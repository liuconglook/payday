package com.belean.payday.classification;


import com.belean.payday.transaction.PayCheck;

import java.util.Calendar;

/**
 * @author belean
 * @date 2021/10/7
 */
public class SalariedClassification implements PaymentClassification {

    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double calculatePay(PayCheck payCheck) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payCheck.getPayDate());
        calendar.set(Calendar.MONTH, -1);
        payCheck.setPayPeriodEndDate(calendar.getTime());

        return salary;
    }
}
