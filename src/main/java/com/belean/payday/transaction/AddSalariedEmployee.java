package com.belean.payday.transaction;

import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.classification.SalariedClassification;
import com.belean.payday.schedule.MonthlySchedule;
import com.belean.payday.schedule.PaymentSchedule;

/**
 * @author belean
 * @date 2021/10/7
 */
public class AddSalariedEmployee extends AddEmployeeTransaction {

    private double salary;

    public AddSalariedEmployee(int empId, String name, String address, double salary) {
        super(empId, name, address);
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
