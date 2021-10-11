package com.belean.payday.affiliation;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        ServiceCharge serviceCharge = new ServiceCharge(memberId, charge, date);
        return serviceCharges.add(serviceCharge);
    }

    public int getMemberId() {
        return memberId;
    }

    public double getDues() {
        return dues;
    }

    @Override
    public double calculateDeductions() {
        Double charge = serviceCharges.stream().map(serviceCharge -> serviceCharge.getCharge()).reduce(0d, (a, b) -> a + b);
        return charge + dues;
    }
}
