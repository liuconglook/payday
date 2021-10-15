package com.belean.payroll.impl.method;

import com.belean.payroll.domain.PaymentMethod;
import com.belean.payroll.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/7
 */
public class MailMethod implements PaymentMethod {
    private String address;

    public MailMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void pay(PayCheck payCheck) {
        payCheck.setDisposition("Mail");
    }
}
