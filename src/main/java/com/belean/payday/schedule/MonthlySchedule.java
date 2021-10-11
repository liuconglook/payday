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

    private boolean isLastDayOfMonth(Date payDate) {
        int month = payDate.getMonth();
        Calendar calendar = Calendar.getInstance();;
        calendar.setTime(payDate);
        calendar.add(Calendar.DATE, 1);
        int month2 = calendar.get(Calendar.MONTH);

        return month != month2;
    }


}
