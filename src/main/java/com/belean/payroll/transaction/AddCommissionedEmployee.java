package com.belean.payroll.transaction;

import com.belean.payroll.impl.classification.CommissionedClassification;
import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.impl.schedule.BiweeklySchedule;
import com.belean.payroll.domain.PaymentSchedule;
import com.belean.payroll.abstraction.AddEmployeeTransaction;

/**
 * @author belean
 * @date 2021/10/7
 */
public class AddCommissionedEmployee extends AddEmployeeTransaction {

    private double salary;
    private double commissionRate;

    public AddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate) {
        super(empId, name, address);
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
