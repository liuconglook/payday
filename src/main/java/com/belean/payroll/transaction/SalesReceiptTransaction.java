package com.belean.payroll.transaction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.abstraction.Transaction;
import com.belean.payroll.impl.classification.CommissionedClassification;
import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.impl.classification.SalesReceipt;
import com.belean.payroll.domain.Employee;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/8
 */
public class SalesReceiptTransaction implements Transaction {

    private int empId;
    private double sales;
    private Date date;

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public SalesReceiptTransaction(int empId, Date date, double sales) {
        this.empId = empId;
        this.date = date;
        this.sales = sales;
    }

    @Override
    public void execute() {
        Employee employee = payrollDatabase.getEmployee(empId);
        if(employee == null) {
            throw new RuntimeException("No such employee.");
        }
        PaymentClassification classification = employee.getClassification();
        if(classification == null) {
            throw new RuntimeException("No such classification.");
        }
        if(classification instanceof CommissionedClassification) {
            CommissionedClassification cc = (CommissionedClassification) classification;
            SalesReceipt salesReceipt = new SalesReceipt(sales, date);
            if(!cc.addSalesReceipt(salesReceipt)) {
                throw new RuntimeException("error: add sales receipt failed.");
            }
        } else {
            throw new RuntimeException("Tried to add sales receipt to non-commissioned employee.");
        }
    }
}
