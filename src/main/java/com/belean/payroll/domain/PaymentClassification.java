package com.belean.payroll.domain;

import com.belean.payroll.transaction.PayCheck;

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
