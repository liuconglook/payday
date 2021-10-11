package com.belean.payday.transaction;

import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.employee.Employee;
import com.belean.payday.schedule.PaymentSchedule;

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
