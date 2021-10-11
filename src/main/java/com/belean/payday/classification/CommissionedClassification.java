package com.belean.payday.classification;

import com.belean.payday.transaction.PayCheck;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author belean
 * @date 2021/10/7
 */
public class CommissionedClassification implements PaymentClassification {

    private double salary;
    private double commissionRate;
    private List<SalesReceipt> salesReceipts = new ArrayList<>();

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public boolean addSalesReceipt(SalesReceipt salesReceipt) {
        return salesReceipts.add(salesReceipt);
    }

    public SalesReceipt getSalesReceipt(Date date) {
        List<SalesReceipt> salesReceipts = this.salesReceipts.stream().filter(salesReceipt -> salesReceipt.getDate().equals(date)).collect(Collectors.toList());
        return CollectionUtils.isEmpty(salesReceipts)?null: salesReceipts.get(0);
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    @Override
    public double calculatePay(PayCheck payCheck) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payCheck.getPayDate());
        calendar.set(Calendar.DATE, -14);
        payCheck.setPayPeriodEndDate(calendar.getTime());

        Double commission = salesReceipts.stream().map(SalesReceipt::getSales).reduce(0d, (a, b) -> a + b);
        return commission * commissionRate + salary;
    }
}
