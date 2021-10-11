package com.belean.payday.transaction;

import com.belean.payday.employee.Employee;

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
