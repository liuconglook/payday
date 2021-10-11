package com.belean.payday.transaction;

import com.belean.payday.MockDatabase;
import com.belean.payday.PayrollDatabase;
import com.belean.payday.affiliation.Affiliation;
import com.belean.payday.classification.CommissionedClassification;
import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.classification.SalesReceipt;
import com.belean.payday.employee.Employee;

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
