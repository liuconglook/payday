package com.belean.payroll.transaction;

import com.belean.payroll.domain.Employee;
import com.belean.payroll.impl.method.MailMethod;
import com.belean.payroll.abstraction.ChangeMethodTransaction;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeMailTransaction extends ChangeMethodTransaction {

    private String address;

    public ChangeMailTransaction(int empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    public void change(Employee employee) {
        employee.setMethod(new MailMethod(address));
    }
}
