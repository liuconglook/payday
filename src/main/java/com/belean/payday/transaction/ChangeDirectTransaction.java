package com.belean.payday.transaction;

import com.belean.payday.method.DirectMethod;
import com.belean.payday.method.PaymentMethod;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeDirectTransaction extends ChangeMethodTransaction {
    public ChangeDirectTransaction(int empId, String bank, String account) {
        super(empId, new DirectMethod(bank, account));
    }
}
