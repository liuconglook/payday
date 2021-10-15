package com.belean.payroll.impl.schedule;

import com.belean.payroll.domain.PaymentSchedule;

import java.util.Calendar;
import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class WeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();;
        calendar.setTime(payDate);
        // 2 3 4 5 6 7 1
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return week == Calendar.FRIDAY;
    }

    @Override
    public Date getPayPeriodStartDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payDate);
        calendar.add(Calendar.DATE, -5);

        if(calendar.getTime().after(startDate)) {
            return calendar.getTime();
        }else {
            return startDate;
        }
    }
}
