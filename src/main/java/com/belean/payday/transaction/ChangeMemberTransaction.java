package com.belean.payday.transaction;

import com.belean.payday.MockDatabase;
import com.belean.payday.PayrollDatabase;
import com.belean.payday.affiliation.Affiliation;
import com.belean.payday.affiliation.UnionAffiliation;
import com.belean.payday.employee.Employee;

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
