package com.belean.payroll.abstraction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.domain.Employee;

/**
 * @author belean
 * @date 2021/10/8
 */
public abstract class ChangeEmployeeTransaction implements Transaction {

    public int empId;

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public ChangeEmployeeTransaction(int empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        Employee employee = payrollDatabase.getEmployee(empId);
        if(employee == null) {
            throw new RuntimeException("No such employee.");
        }
        change(employee);
    }

    /**
     * 修改雇员明细
     * @param employee
     */
    public abstract void change(Employee employee);
}
