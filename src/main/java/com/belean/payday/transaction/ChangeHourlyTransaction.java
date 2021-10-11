package com.belean.payday.transaction;

import com.belean.payday.classification.HourlyClassification;
import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.schedule.PaymentSchedule;
import com.belean.payday.schedule.WeeklySchedule;

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
