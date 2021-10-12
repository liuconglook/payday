package com.belean.payday;

import com.belean.payday.employee.Employee;

import java.util.List;
import java.util.Map;

/**
 * @author belean
 * @date 2021/10/7
 */
public interface PayrollDatabase {

    /**
     * 根据empId获取雇员
     * @param empId
     * @return
     */
    Employee getEmployee(int empId);

    /**
     * 获取所有雇员
     * @return
     */
    Map<Integer, Employee> employees();

    /**
     * 添加雇员
     * @param empId
     * @param employee
     */
    void addEmployee(int empId, Employee employee);

    /**
     * 删除雇员
     * @param empId
     */
    void deleteEmployee(int empId);

    /**
     * 添加会员
     * @param memberId
     * @param employee
     */
    void addUnionMember(int memberId, Employee employee);

    /**
     * 根据memberId获取协会成员
     * @param memberId
     * @return
     */
    Employee getUnionMember(int memberId);

    /**
     * 根据memberId删除协会成员
     * @param memberId
     */
    void removeUnionMember(int memberId);

}
