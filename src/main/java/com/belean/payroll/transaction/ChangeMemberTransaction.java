package com.belean.payroll.transaction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.domain.Affiliation;
import com.belean.payroll.impl.affiliation.UnionAffiliation;
import com.belean.payroll.domain.Employee;
import com.belean.payroll.abstraction.ChangeAffiliationTransaction;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

    private int memberId;
    private double dues;

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public ChangeMemberTransaction(int empId, int memberId, double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    public Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, dues);
    }

    @Override
    public void recordMembership(Employee employee) {
        payrollDatabase.addUnionMember(memberId, employee);
    }

}
