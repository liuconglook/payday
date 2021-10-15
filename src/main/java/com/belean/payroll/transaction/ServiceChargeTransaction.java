package com.belean.payroll.transaction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.abstraction.Transaction;
import com.belean.payroll.domain.Affiliation;
import com.belean.payroll.impl.affiliation.UnionAffiliation;
import com.belean.payroll.domain.Employee;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class ServiceChargeTransaction implements Transaction {

    private int memberId;
    private double charge;
    private Date date;

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public ServiceChargeTransaction(int memberId, double charge, Date date) {
        this.memberId = memberId;
        this.charge = charge;
        this.date = date;
    }

    @Override
    public void execute() {
        Employee employee = payrollDatabase.getUnionMember(memberId);
        if(employee == null) {
            throw new RuntimeException("No such employee.");
        }
        Affiliation affiliation = employee.getAffiliation();
        if(affiliation == null) {
            throw new RuntimeException("No such affiliation.");
        }
        if(affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            if(!unionAffiliation.addServiceCharge(charge, date)) {
                throw new RuntimeException("error: add service charge failed.");
            }
        } else {
            throw new RuntimeException("Tried to add service charge to non-union employee.");
        }
    }
}
