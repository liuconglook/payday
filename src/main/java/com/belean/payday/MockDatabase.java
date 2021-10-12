package com.belean.payday;

import com.belean.payday.employee.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author belean
 * @date 2021/10/7
 */
public class MockDatabase implements PayrollDatabase {

    private final static Map<Integer, Employee> employees = new HashMap<>();
    private final static Map<Integer, Employee> unionMembers = new HashMap<>();

    @Override
    public Employee getEmployee(int empId) {
        return employees.get(empId);
    }

    @Override
    public Map<Integer, Employee> employees() {
        return employees;
    }

    @Override
    public void addEmployee(int empId, Employee employee) {
        employees.put(empId, employee);
    }

    @Override
    public void deleteEmployee(int empId) {
        employees.remove(empId);
    }

    @Override
    public void addUnionMember(int memberId, Employee employee) {
        unionMembers.put(memberId, employee);
    }

    @Override
    public Employee getUnionMember(int memberId) {
        return unionMembers.get(memberId);
    }

    @Override
    public void removeUnionMember(int memberId) {
        unionMembers.remove(memberId);
    }
}
