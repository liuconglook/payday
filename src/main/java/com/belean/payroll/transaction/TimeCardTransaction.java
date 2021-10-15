package com.belean.payroll.transaction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.abstraction.Transaction;
import com.belean.payroll.impl.classification.HourlyClassification;
import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.impl.classification.TimeCard;
import com.belean.payroll.domain.Employee;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class TimeCardTransaction implements Transaction {

    private int empId;
    private Date date;
    private double hours;

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public TimeCardTransaction(int empId, Date date, double hours) {
        this.empId = empId;
        this.date = date;
        this.hours = hours;
    }

    @Override
    public void execute() {
        Employee employee = payrollDatabase.getEmployee(empId);
        if(employee == null) {
            throw new RuntimeException("No such employee.");
        }
        PaymentClassification classification = employee.getClassification();
        if(classification == null) {
            throw new RuntimeException("error: No such classification.");
        }
        if(classification instanceof HourlyClassification) {
            HourlyClassification hourlyClassification = (HourlyClassification) classification;
            TimeCard timeCard = new TimeCard( date, hours);
            if(!hourlyClassification.addTimeCard(timeCard)) {
                throw new RuntimeException("error: add timecard failed.");
            }
        } else {
            throw new RuntimeException("Tried to add timecard to non-hourly employee.");
        }
    }
}
