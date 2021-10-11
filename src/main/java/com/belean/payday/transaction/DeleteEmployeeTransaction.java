package com.belean.payday.transaction;

import com.belean.payday.MockDatabase;
import com.belean.payday.PayrollDatabase;

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
