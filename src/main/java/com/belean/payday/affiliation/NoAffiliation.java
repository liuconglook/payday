package com.belean.payday.affiliation;

/**
 * @author belean
 * @date 2021/10/8
 */
public class NoAffiliation implements Affiliation{
    @Override
    public double calculateDeductions() {
        return 0d;
    }
}
