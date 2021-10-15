package com.belean.payroll.transaction;

import com.belean.payroll.impl.classification.HourlyClassification;
import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.domain.PaymentSchedule;
import com.belean.payroll.impl.schedule.WeeklySchedule;
import com.belean.payroll.abstraction.AddEmployeeTransaction;


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
