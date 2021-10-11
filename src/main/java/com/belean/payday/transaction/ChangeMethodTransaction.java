package com.belean.payday.transaction;

import com.belean.payday.employee.Employee;
import com.belean.payday.method.PaymentMethod;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    private PaymentMethod method;

    public ChangeMethodTransaction(int empId, PaymentMethod method) {
        super(empId);
        this.method = method;
    }

    @Override
    public void change(Employee employee) {
        employee.setMethod(method);
    }
}
