package com.belean.payroll.abstraction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.impl.affiliation.NoAffiliation;
import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.domain.Employee;
import com.belean.payroll.impl.method.HoldMethod;
import com.belean.payroll.domain.PaymentSchedule;

/**
 * @author belean
 * @date 2021/10/7
 */
public abstract class AddEmployeeTransaction implements Transaction {

    private int empId;
    private String name;
    private String address;

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public AddEmployeeTransaction(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    @Override
    public void execute() {
        PaymentClassification classification = getClassification();
        PaymentSchedule schedule = getSchedule();
        HoldMethod holdMethod = new HoldMethod();
        Employee employee = new Employee(empId, name, address);
        employee.setClassification(classification);
        employee.setSchedule(schedule);
        employee.setMethod(holdMethod);
        employee.setAffiliation(new NoAffiliation());
        payrollDatabase.addEmployee(empId, employee);
    }

    /**
     * 获取薪水数据
     * @return
     */
    public abstract PaymentClassification getClassification();

    /**
     * 获取时间表
     * @return
     */
    public abstract PaymentSchedule getSchedule();
}
