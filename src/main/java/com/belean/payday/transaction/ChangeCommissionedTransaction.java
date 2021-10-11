package com.belean.payday.transaction;

import com.belean.payday.classification.CommissionedClassification;
import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.schedule.BiweeklySchedule;
import com.belean.payday.schedule.PaymentSchedule;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {

    private double salary;
    private double commissionRate;

    public ChangeCommissionedTransaction(int empId, double salary, double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
