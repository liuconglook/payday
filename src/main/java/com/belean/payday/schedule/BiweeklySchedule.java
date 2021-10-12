package com.belean.payday.schedule;

import java.util.Calendar;
import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class BiweeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payDate);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH) - 1;
        System.out.println(weekOfMonth);
        return weekOfMonth == 1;
    }

    @Override
    public Date getPayPeriodStartDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payDate);
        calendar.add(Calendar.DATE, -14);

        return calendar.getTime();
    }
}
