package com.belean.payday.schedule;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public interface PaymentSchedule {

    /**
     * 是否是支付日
     * @param payDate
     * @return
     */
    boolean isPayDate(Date payDate);
}
