package com.belean.payroll.impl.method;

import com.belean.payroll.domain.PaymentMethod;
import com.belean.payroll.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/7
 */
public class DirectMethod implements PaymentMethod {
    private String bank;
    private String account;

    public DirectMethod(String bank, String account) {
        this.bank = bank;
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public void pay(PayCheck payCheck) {
        payCheck.setDisposition("Direct");
    }
}
