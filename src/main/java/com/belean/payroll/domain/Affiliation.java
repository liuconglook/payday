package com.belean.payroll.domain;

import com.belean.payroll.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/7
 */
public interface Affiliation {

    /**
     * 计算扣除的费用
     * @param payCheck
     * @return
     */
    double calculateDeductions(PayCheck payCheck);
}
