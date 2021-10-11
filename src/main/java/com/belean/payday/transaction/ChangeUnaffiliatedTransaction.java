package com.belean.payday.transaction;

import com.belean.payday.MockDatabase;
import com.belean.payday.PayrollDatabase;
import com.belean.payday.affiliation.Affiliation;
import com.belean.payday.affiliation.NoAffiliation;
import com.belean.payday.affiliation.UnionAffiliation;
import com.belean.payday.employee.Employee;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public ChangeUnaffiliatedTransaction(int empId) {
        super(empId);
    }

    @Override
    public Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    @Override
    public void recordMembership(Employee employee) {
        Affiliation affiliation = employee.getAffiliation();
        if(affiliation == null) {
            throw new RuntimeException("unaffiliated.");
        }
        if(affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            int memberId = unionAffiliation.getMemberId();
            payrollDatabase.removeUnionMember(memberId);
        } else {
            throw new RuntimeException("Tried to unaffiliated to non-union employee.");
        }
    }
}
