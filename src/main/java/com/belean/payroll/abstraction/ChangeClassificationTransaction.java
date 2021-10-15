package com.belean.payroll.abstraction;

import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.domain.Employee;
import com.belean.payroll.domain.PaymentSchedule;

/**
 * @author belean
 * @date 2021/10/8
 */
public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee employee) {
        employee.setClassification(getClassification());
        employee.setSchedule(getSchedule());
    }

    /**
     * 获取雇员薪水类型
     * @return
     */
    public abstract PaymentClassification getClassification();

    /**
     * 获取时间表
     * @return
     */
    public abstract PaymentSchedule getSchedule();
}
