package com.belean.payroll.transaction;

import com.belean.payroll.domain.Employee;
import com.belean.payroll.abstraction.ChangeEmployeeTransaction;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

    private String address;

    public ChangeAddressTransaction(int empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    public void change(Employee employee) {
        employee.setAddress(address);
    }
}
