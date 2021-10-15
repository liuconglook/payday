package com.belean.payroll.impl.affiliation;

import com.belean.payroll.domain.Affiliation;
import com.belean.payroll.transaction.PayCheck;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author belean
 * @date 2021/10/7
 */
public class UnionAffiliation implements Affiliation {

    private int memberId;
    private double dues;
    private List<ServiceCharge> serviceCharges = new ArrayList<>();

    public UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(Date date) {
        List<ServiceCharge> serviceCharges = this.serviceCharges.stream().filter(serviceCharge -> serviceCharge.getDate().equals(date)).collect(Collectors.toList());
        return CollectionUtils.isEmpty(serviceCharges)? null: serviceCharges.get(0);
    }

    public boolean addServiceCharge(double charge, Date date) {
        ServiceCharge serviceCharge = new ServiceCharge(charge, date);
        return serviceCharges.add(serviceCharge);
    }

    public int getMemberId() {
        return memberId;
    }

    public double getDues() {
        return dues;
    }

    @Override
    public double calculateDeductions(PayCheck payCheck) {
        int fridays = numberOfFridaysInPayPeriod(payCheck.getPayPeriodStartDate(), payCheck.getPayDate());
        double totalDues = dues * fridays;

        double totalServiceCharge = 0d;
        for(ServiceCharge serviceCharge : serviceCharges) {
            if(isInPayPeriod(serviceCharge, payCheck)) {
                totalServiceCharge += serviceCharge.getCharge();
            }
        }

        return totalDues + totalServiceCharge;
    }

    private boolean isInPayPeriod(ServiceCharge serviceCharge, PayCheck payCheck) {
        Date payPeriodEndDate = payCheck.getPayDate();
        return (serviceCharge.getDate().getTime() >= payCheck.getPayPeriodStartDate().getTime())
                && (serviceCharge.getDate().getTime() <= payPeriodEndDate.getTime());
    }

    public int numberOfFridaysInPayPeriod(Date payPeriodStart, Date payPeriodEnd) {
        int fridays = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payPeriodStart);
        while(payPeriodEnd.getTime() >= calendar.getTime().getTime()) {
            if(calendar.get(Calendar.DAY_OF_WEEK) - 1 == Calendar.FRIDAY) {
                fridays++;
            }
            calendar.add(Calendar.DATE, 1);
        }
        return fridays;
    }
}
