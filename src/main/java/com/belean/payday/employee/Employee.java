package com.belean.payday.employee;

import com.belean.payday.affiliation.Affiliation;
import com.belean.payday.classification.PaymentClassification;
import com.belean.payday.method.PaymentMethod;
import com.belean.payday.schedule.PaymentSchedule;
import com.belean.payday.transaction.PayCheck;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class Employee {

    private int empId;
    private String name;
    private String address;
    private PaymentSchedule schedule;
    private PaymentClassification classification;
    private PaymentMethod method;
    private Affiliation affiliation;

    public Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public void setClassification(PaymentClassification classification) {
        this.classification = classification;
    }

    public PaymentClassification getClassification() {
        return classification;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public boolean isPayDate(Date payDate) {
        return schedule.isPayDate(payDate);
    }

    public void payDay(PayCheck payCheck) {
        double grossPay = classification.calculatePay(payCheck);
        double deductions = affiliation.calculateDeductions(payCheck);
        double netPay = grossPay - deductions;
        payCheck.setGrossPay(grossPay);
        payCheck.setDeductions(deductions);
        payCheck.setNetPay(netPay);
        method.pay(payCheck);
    }

    public Date getPayPeriodStartDate(Date payDate) {
        return schedule.getPayPeriodStartDate(payDate);
    }
}
