package com.belean.payday.transaction;

import com.belean.payday.employee.Employee;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeNameTransaction extends ChangeEmployeeTransaction {

    private String name;

    public ChangeNameTransaction(int empId, String name) {
        super(empId);
        this.name = name;
    }

    @Override
    public void change(Employee employee) {
        employee.setName(name);
    }
}
