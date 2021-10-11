package com.belean.payday.transaction;

import com.belean.payday.method.HoldMethod;
import com.belean.payday.method.PaymentMethod;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId) {
        super(empId, new HoldMethod());
    }
}
