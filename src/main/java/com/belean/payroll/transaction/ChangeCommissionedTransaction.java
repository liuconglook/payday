package com.belean.payroll.transaction;

import com.belean.payroll.impl.classification.CommissionedClassification;
import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.impl.schedule.BiweeklySchedule;
import com.belean.payroll.domain.PaymentSchedule;
import com.belean.payroll.abstraction.ChangeClassificationTransaction;

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
