package com.belean.payroll.domain;

import com.belean.payroll.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/7
 */
public interface PaymentMethod {

    /**
     * 支付薪水
     * @param payCheck
     */
    void pay(PayCheck payCheck);
}
