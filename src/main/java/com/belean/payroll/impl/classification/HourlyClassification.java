package com.belean.payroll.impl.classification;

import com.belean.payroll.domain.PaymentClassification;
import com.belean.payroll.transaction.PayCheck;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
        double totalPay = 0d;
        for(TimeCard timeCard : timeCards) {
            if(isInPayPeriod(timeCard, payCheck)) {
                totalPay += calculatePayForTimeCard(timeCard);
            }
        }
        return totalPay;
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        double hours = timeCard.getHours();
        double overtimeHours = Math.max(0d, hours - 8d);
        return (hours + overtimeHours * 0.5d) * hourlyRate;
    }

    private boolean isInPayPeriod(TimeCard timeCard, PayCheck payCheck) {
        Date payPeriodEndDate = payCheck.getPayDate();
        return (timeCard.getDate().getTime() >= payCheck.getPayPeriodStartDate().getTime())
                && (timeCard.getDate().getTime() <= payPeriodEndDate.getTime());
    }
}
