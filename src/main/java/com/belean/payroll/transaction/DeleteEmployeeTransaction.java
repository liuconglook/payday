package com.belean.payroll.transaction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.abstraction.Transaction;

/**
 * @author belean
 * @date 2021/10/7
 */
public class DeleteEmployeeTransaction implements Transaction {

    private int empId;

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public DeleteEmployeeTransaction(int empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        payrollDatabase.deleteEmployee(empId);
    }


}
