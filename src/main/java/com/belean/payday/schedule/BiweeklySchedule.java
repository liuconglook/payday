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

    public static void main(String[] args) {
        BiweeklySchedule biweeklySchedule = new BiweeklySchedule();
        biweeklySchedule.isPayDate(new Date(2021, 1-1, 2));

    }
}
