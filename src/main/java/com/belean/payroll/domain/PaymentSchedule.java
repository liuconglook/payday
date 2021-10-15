package com.belean.payroll.domain;

import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public interface PaymentSchedule {

    /**
     * 时间表开始时间
     */
    Date startDate = new Date(2021, 1 - 1, 1);

    /**
     * 是否是支付日
     * @param payDate
     * @return
     */
    boolean isPayDate(Date payDate);

    /**
     * 获取支付开始时间
     * @param payDate
     * @return
     */
    Date getPayPeriodStartDate(Date payDate);
}
