package com.belean.payroll.transaction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.domain.Affiliation;
import com.belean.payroll.impl.affiliation.NoAffiliation;
import com.belean.payroll.impl.affiliation.UnionAffiliation;
import com.belean.payroll.domain.Employee;
import com.belean.payroll.abstraction.ChangeAffiliationTransaction;

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
