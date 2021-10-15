package com.belean.payroll.impl.classification;


import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.transaction.PayCheck;

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
        return salary;
    }
}
