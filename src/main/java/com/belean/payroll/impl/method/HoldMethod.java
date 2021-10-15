package com.belean.payroll.impl.method;

import com.belean.payroll.domain.PaymentMethod;
import com.belean.payroll.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/7
 */
public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(PayCheck payCheck) {
        payCheck.setDisposition("Hold");
    }
}
