package com.belean.payday.transaction;

import com.belean.payday.method.MailMethod;
import com.belean.payday.method.PaymentMethod;

/**
 * @author belean
 * @date 2021/10/8
 */
public class ChangeMailTransaction extends ChangeMethodTransaction {
    public ChangeMailTransaction(int empId, String address) {
        super(empId, new MailMethod(address));
    }
}
