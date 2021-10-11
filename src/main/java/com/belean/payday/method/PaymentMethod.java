package com.belean.payday.method;

import com.belean.payday.transaction.PayCheck;

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
