package com.belean.payday.classification;

import com.belean.payday.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/7
 */
public interface PaymentClassification {

    /**
     * 计算薪资
     * @param payCheck
     * @return
     */
    double calculatePay(PayCheck payCheck);
}
