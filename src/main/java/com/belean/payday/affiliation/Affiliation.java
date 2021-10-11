package com.belean.payday.affiliation;

import com.belean.payday.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/7
 */
public interface Affiliation {

    /**
     * 计算扣除的费用
     * @return
     */
    double calculateDeductions();
}
