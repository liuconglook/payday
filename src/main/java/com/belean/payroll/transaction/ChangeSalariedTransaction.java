package com.belean.payroll.transaction;

import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.impl.classification.SalariedClassification;
import com.belean.payroll.impl.schedule.MonthlySchedule;
import com.belean.payroll.domain.PaymentSchedule;
import com.belean.payroll.abstraction.ChangeClassificationTransaction;

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
