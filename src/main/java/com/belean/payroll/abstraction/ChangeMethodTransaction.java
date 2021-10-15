package com.belean.payroll.abstraction;

import com.belean.payroll.domain.Employee;

/**
 * @author belean
 * @date 2021/10/8
 */
public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int empId) {
        super(empId);
    }

    /**
     * 修改支付方式
     * @param employee
     */
    @Override
    public abstract void change(Employee employee);
}
