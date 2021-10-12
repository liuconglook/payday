package com.belean.payday.transaction;

import com.belean.payday.classification.HourlyClassification;
import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.schedule.PaymentSchedule;
import com.belean.payday.schedule.WeeklySchedule;


/**
 * @author belean
 * @date 2021/10/7
 */
public class AddHourlyEmployee extends AddEmployeeTransaction {

    private double hourlyRate;

    public AddHourlyEmployee(int empId, String name, String address, double hourlyRate) {
        super(empId, name, address);
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
