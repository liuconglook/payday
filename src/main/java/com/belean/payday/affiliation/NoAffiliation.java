package com.belean.payday.affiliation;

import com.belean.payday.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/8
 */
public class NoAffiliation implements Affiliation{
    @Override
    public double calculateDeductions(PayCheck payCheck) {
        return 0d;
    }
}
