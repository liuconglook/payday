package com.belean.payday.transaction;

import com.belean.payday.classification.CommissionedClassification;
import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.schedule.BiweeklySchedule;
import com.belean.payday.schedule.MonthlySchedule;
import com.belean.payday.schedule.PaymentSchedule;

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
