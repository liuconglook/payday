package com.belean.payroll.impl.schedule;

import com.belean.payroll.domain.PaymentSchedule;

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
        // 当月1号
        calendar.set(Calendar.DATE, 1);
        // 上个月最后一天
        calendar.add(Calendar.DATE, -1);

        if(calendar.getTime().after(startDate)) {
            return calendar.getTime();
        }else {
            return startDate;
        }
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
