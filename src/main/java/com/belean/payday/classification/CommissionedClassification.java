package com.belean.payday.classification;

import com.belean.payday.transaction.PayCheck;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
        double totalSales = 0d;
        for(SalesReceipt salesReceipt : salesReceipts) {
            if(isInPayPeriod(salesReceipt, payCheck)) {
                totalSales += salesReceipt.getSales();
            }
        }
        return totalSales * commissionRate + salary;
    }

    private boolean isInPayPeriod(SalesReceipt salesReceipt, PayCheck payCheck) {
        Date payPeriodEndDate = payCheck.getPayDate();
        return (salesReceipt.getDate().getTime() >= payCheck.getPayPeriodStartDate().getTime())
                && (salesReceipt.getDate().getTime() <= payPeriodEndDate.getTime());
    }
}
