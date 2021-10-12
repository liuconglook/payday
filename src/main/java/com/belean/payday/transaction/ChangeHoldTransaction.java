package com.belean.payday.transaction;

import com.belean.payday.method.HoldMethod;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId) {
        super(empId, new HoldMethod());
    }
}
