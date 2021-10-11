package com.belean.payday.classification;

import com.belean.payday.transaction.PayCheck;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author belean
 * @date 2021/10/7
 */
public class HourlyClassification implements PaymentClassification {

    private double hourlyRate;
    private List<TimeCard> timeCards = new ArrayList<>();

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public TimeCard getTimeCard(final Date date) {
        List<TimeCard> timeCards = this.timeCards.stream()
                .filter(timeCard -> timeCard.getDate().equals(date))
                .collect(Collectors.toList());
        return CollectionUtils.isEmpty(timeCards)? null: timeCards.get(0);
    }

    public boolean addTimeCard(TimeCard timeCard) {
        return this.timeCards.add(timeCard);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public double calculatePay(PayCheck payCheck) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payCheck.getPayDate());
        calendar.set(Calendar.DATE, -5);
        payCheck.setPayPeriodEndDate(calendar.getTime());

        double totalPay = 0d;
        Date payPeriodEndDate = payCheck.getPayDate();
        for(TimeCard timeCard : timeCards) {
            if(isInPayPeriod(timeCard, payPeriodEndDate)) {
                totalPay += calculatePayForTimeCard(timeCard);
            }
        }
        return totalPay;
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        double hours = timeCard.getHours();
        double overtimeHours = Math.max(0d, hours - 8d);
        return (hours + overtimeHours * 0.5) * hourlyRate;
    }

    private boolean isInPayPeriod(TimeCard timeCard, Date payPeriodEndDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payPeriodEndDate);
        calendar.set(Calendar.DATE, -5);
        Date payPeriodStartDate = calendar.getTime();

        return (timeCard.getDate().getTime() >= payPeriodStartDate.getTime())
                && (timeCard.getDate().getTime() <= payPeriodEndDate.getTime());

    }
}
