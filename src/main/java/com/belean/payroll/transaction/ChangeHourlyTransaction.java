package com.belean.payroll.transaction;

import com.belean.payroll.impl.classification.HourlyClassification;
import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.domain.PaymentSchedule;
import com.belean.payroll.impl.schedule.WeeklySchedule;
import com.belean.payroll.abstraction.ChangeClassificationTransaction;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeHourlyTransaction extends ChangeClassificationTransaction {

    private double hourlyRate;

    public ChangeHourlyTransaction(int empId, double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
