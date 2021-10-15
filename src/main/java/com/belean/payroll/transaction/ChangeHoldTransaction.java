package com.belean.payroll.transaction;

import com.belean.payroll.domain.Employee;
import com.belean.payroll.impl.method.HoldMethod;
import com.belean.payroll.abstraction.ChangeMethodTransaction;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeHoldTransaction extends ChangeMethodTransaction {

    public ChangeHoldTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee employee) {
        employee.setMethod(new HoldMethod());
    }

}
