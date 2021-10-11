package com.belean.payday.transaction;

import com.belean.payday.MockDatabase;
import com.belean.payday.PayrollDatabase;
import com.belean.payday.affiliation.NoAffiliation;
import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.employee.Employee;
import com.belean.payday.method.HoldMethod;
import com.belean.payday.schedule.PaymentSchedule;

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
