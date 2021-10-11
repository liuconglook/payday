package com.belean.payday.method;

import com.belean.payday.transaction.PayCheck;

import java.util.Date;

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
