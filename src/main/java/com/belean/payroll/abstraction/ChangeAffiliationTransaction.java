package com.belean.payroll.abstraction;

import com.belean.payroll.domain.Affiliation;
import com.belean.payroll.domain.Employee;

/**
 * @author belean
 * @date 2021/10/8
 */
public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

    public ChangeAffiliationTransaction(int empId) {
        super(empId);
    }

    /**
     * 入会或退会
     * @param employee
     */
    @Override
    public void change(Employee employee) {
        recordMembership(employee);
        employee.setAffiliation(getAffiliation());
    }

    /**
     * 获取协会
     */
    public abstract Affiliation getAffiliation();

    /**
     * 记录成员身份
     * @param employee
     */
    public abstract void recordMembership(Employee employee);
}
