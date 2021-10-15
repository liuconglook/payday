package com.belean.payroll.transaction;

import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.impl.classification.SalariedClassification;
import com.belean.payroll.impl.schedule.MonthlySchedule;
import com.belean.payroll.domain.PaymentSchedule;
import com.belean.payroll.abstraction.AddEmployeeTransaction;

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
