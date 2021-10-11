package com.belean.payday.transaction;

import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.classification.SalariedClassification;
import com.belean.payday.schedule.MonthlySchedule;
import com.belean.payday.schedule.PaymentSchedule;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeSalariedTransaction extends ChangeClassificationTransaction {

    private double salary;

    public ChangeSalariedTransaction(int empId, double salary) {
        super(empId);
        this.salary = salary;
    }

    @Override
    public PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
