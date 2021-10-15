package com.belean.payroll.transaction;

import com.belean.payroll.domain.Employee;
import com.belean.payroll.impl.method.DirectMethod;
import com.belean.payroll.abstraction.ChangeMethodTransaction;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeDirectTransaction extends ChangeMethodTransaction {

    private String bank;
    private String account;

    public ChangeDirectTransaction(int empId, String bank, String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    public void change(Employee employee) {
        employee.setMethod(new DirectMethod(bank, account));
    }
}
