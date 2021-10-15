package com.belean.payroll.impl.schedule;

import com.belean.payroll.domain.PaymentSchedule;

import java.util.Calendar;
import java.util.Date;

/**
 * @author belean
 * @date 2021/10/7
 */
public class BiweeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();;
        calendar.setTime(payDate);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week % 2 == 0 && calendar.get(Calendar.DAY_OF_WEEK) -1 == Calendar.FRIDAY;
    }

    @Override
    public Date getPayPeriodStartDate(Date payDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payDate);
        calendar.add(Calendar.DATE, -12);

        if(calendar.getTime().after(startDate)) {
            return calendar.getTime();
        }else {
            return startDate;
        }
    }
}
