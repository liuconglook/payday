package com.belean.payday.affiliation;

import com.belean.payday.transaction.PayCheck;
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
        return dues * fridays;
    }

    public int numberOfFridaysInPayPeriod(Date payPeriodStart, Date payPeriodEnd) {
        int fridays = 0;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.setTime(payPeriodStart);
        while(payPeriodEnd.after(calendar.getTime())) {
            calendar.add(Calendar.DATE, 1);
            if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                System.out.println(calendar.getTime());
                fridays++;
            }
        }
        return fridays;
    }
}
