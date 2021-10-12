package com.belean.payday.schedule;

import java.util.Calendar;
import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class MonthlySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Date payDate) {
        return isLastDayOfMonth(payDate);
    }

    @Override
    public Date getPayPeriodStartDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payDate);
        calendar.set(Calendar.DATE, 1); // 当月1号
        calendar.add(Calendar.DATE, -1); // 下月最后一天

        return calendar.getTime();
    }

    private boolean isLastDayOfMonth(Date payDate) {
        int month = payDate.getMonth();
        Calendar calendar = Calendar.getInstance();;
        calendar.setTime(payDate);
        calendar.add(Calendar.DATE, 1);
        int month2 = calendar.get(Calendar.MONTH);

        return month != month2;
    }


}
