package com.belean.payroll.test;


import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.impl.affiliation.NoAffiliation;
import com.belean.payroll.impl.affiliation.ServiceCharge;
import com.belean.payroll.impl.affiliation.UnionAffiliation;
import com.belean.payroll.impl.classification.*;
import com.belean.payroll.domain.Employee;
import com.belean.payroll.impl.method.DirectMethod;
import com.belean.payroll.impl.method.HoldMethod;
import com.belean.payroll.impl.method.MailMethod;
import com.belean.payroll.impl.schedule.BiweeklySchedule;
import com.belean.payroll.impl.schedule.MonthlySchedule;
import com.belean.payroll.impl.schedule.WeeklySchedule;
import com.belean.payroll.transaction.*;

import junit.framework.TestCase;

import java.util.Date;


/**
 * @author belean
 * @date 2021/10/7
 */
public class PayrollTest extends TestCase {

    private PayrollDatabase payrollDatabase = new MockDatabase();

    /**
     * 添加雇员
     */
    public void testAddEmployeeTransaction() {
        int empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00d);
        ase.execute();

        Employee employee = payrollDatabase.getEmployee(empId);
        assertEquals("Bob", employee.getName());

        SalariedClassification salariedClassification = (SalariedClassification)employee.getClassification();
        assertNotNull(salariedClassification);
        assertEquals(1000.00d, salariedClassification.getSalary(), 0.001d);

        MonthlySchedule monthlySchedule = (MonthlySchedule)employee.getSchedule();
        assertNotNull(monthlySchedule);

        HoldMethod holdMethod = (HoldMethod)employee.getMethod();
        assertNotNull(holdMethod);
    }

    /**
     * 删除雇员
     */
    public void testDeleteEmployee() {
        int empId = 3;
        AddCommissionedEmployee ace = new AddCommissionedEmployee(empId, "Lance", "Home", 2500d, 3.2d);
        ace.execute();

        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        DeleteEmployeeTransaction det = new DeleteEmployeeTransaction(empId);
        det.execute();

        Employee e = payrollDatabase.getEmployee(empId);
        assertNull(e);

    }

    /**
     * 时间卡
     */
    public void testTimeCardTransaction() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        TimeCardTransaction tct = new TimeCardTransaction(empId, new Date(2021, 10 - 1, 7), 8.0d);
        tct.execute();

        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        HourlyClassification hourlyClassification = (HourlyClassification)employee.getClassification();

        TimeCard timeCard = hourlyClassification.getTimeCard(new Date(2021, 10 - 1, 7));
        assertNotNull(timeCard);
        assertEquals(8.0d, timeCard.getHours(), 0.01d);
    }

    /**
     * 添加销售凭条
     */
    public void testAddSalesReceipt() {
        int empId = 3;
        AddCommissionedEmployee ace = new AddCommissionedEmployee(empId, "Belean", "Home", 5000d, 20.25d);
        ace.execute();

        SalesReceiptTransaction srt = new SalesReceiptTransaction(empId, new Date(2021, 10 - 1, 7), 2000d);
        srt.execute();

        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        CommissionedClassification cc = (CommissionedClassification) employee.getClassification();
        SalesReceipt salesReceipt = cc.getSalesReceipt(new Date(2021, 10 - 1, 7));
        assertNotNull(salesReceipt);
        assertEquals(2000d, salesReceipt.getSales(), 0.001d);
    }

    /**
     * 记录服务费
     */
    public void testAddServiceCharge() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);

        int memberId = 86;
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId, 12.5d);
        employee.setAffiliation(unionAffiliation);

        payrollDatabase.addUnionMember(memberId, employee);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId,12.95d, new Date(2021, 10 - 1, 7));
        sct.execute();

        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(new Date(2021, 10 - 1, 7));
        assertNotNull(serviceCharge);
        assertEquals(12.95d, serviceCharge.getCharge(), 0.001d);
    }

    /**
     * 修改雇员姓名
     */
    public void testChangeNameTransaction() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        ChangeNameTransaction ct = new ChangeNameTransaction(empId, "Bob");
        ct.execute();

        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertEquals("Bob", employee.getName());
    }

    /**
     * 修改雇员地址
     */
    public void testChangeAddressTransaction() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        ChangeAddressTransaction ct = new ChangeAddressTransaction(empId, "bill home");
        ct.execute();

        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertEquals("bill home", employee.getAddress());
    }

    /**
     * 修改雇员类型
     */
    public void testChangeClassificationTransaction() {
        // 月薪
        int empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00d);
        ase.execute();

        // 修改为小时工
        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 15.25d);
        cht.execute();
        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertTrue(employee.getClassification() instanceof HourlyClassification);
        assertTrue(employee.getSchedule() instanceof WeeklySchedule);
        HourlyClassification hourlyClassification = (HourlyClassification) employee.getClassification();
        assertEquals(15.25d, hourlyClassification.getHourlyRate(), 0.001d);

        // 修改为月薪
        ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, 5000d);
        cst.execute();
        employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertTrue(employee.getClassification() instanceof SalariedClassification);
        assertTrue(employee.getSchedule() instanceof MonthlySchedule);
        SalariedClassification salariedClassification = (SalariedClassification) employee.getClassification();
        assertEquals(5000d, salariedClassification.getSalary(), 0.001d);

        // 修改为月薪+酬金
        ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, 6000, 5.5d);
        cct.execute();
        employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertTrue(employee.getClassification() instanceof CommissionedClassification);
        assertTrue(employee.getSchedule() instanceof BiweeklySchedule);
        CommissionedClassification commissionedClassification = (CommissionedClassification) employee.getClassification();
        assertEquals(6000d, commissionedClassification.getSalary(), 0.001d);
        assertEquals(5.5d, commissionedClassification.getCommissionRate(), 0.001d);

    }

    /**
     * 修改雇员的薪水发放方式
     */
    public void testChangeMethodTransaction() {
        // 薪水发放默认：邮寄支票到收纳人地址
        int empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00d);
        ase.execute();

        // 修改为：存入指定的银行帐号
        ChangeDirectTransaction cdt = new ChangeDirectTransaction(empId, "中国银行", "1234");
        cdt.execute();
        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertTrue(employee.getMethod() instanceof DirectMethod);
        DirectMethod directMethod = (DirectMethod) employee.getMethod();
        assertEquals("中国银行", directMethod.getBank());
        assertEquals("1234", directMethod.getAccount());

        // 修改为：邮寄支票到指定地址
        ChangeMailTransaction cmt = new ChangeMailTransaction(empId, "Bob Home");
        cmt.execute();
        employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertTrue(employee.getMethod() instanceof MailMethod);
        MailMethod mailMethod = (MailMethod) employee.getMethod();
        assertEquals("Bob Home", mailMethod.getAddress());

        // 修改为：邮寄支票到收纳人地址
        ChangeHoldTransaction cht = new ChangeHoldTransaction(empId);
        cht.execute();
        employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertTrue(employee.getMethod() instanceof HoldMethod);
    }

    /**
     * 雇员入会与退会
     */
    public void testChangeAffiliationTransaction() {
        int empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00d);
        ase.execute();

        // 加入协会
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, 1001, 10d);
        cmt.execute();
        Employee employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertNotNull(employee.getAffiliation());
        assertTrue(employee.getAffiliation() instanceof UnionAffiliation);
        UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
        assertEquals(1001, unionAffiliation.getMemberId());
        assertEquals(10d, unionAffiliation.getDues());
        Employee unionMember = payrollDatabase.getUnionMember(1001);
        assertEquals(employee, unionMember);

        // 退出协会
        ChangeUnaffiliatedTransaction cut = new ChangeUnaffiliatedTransaction(empId);
        cut.execute();
        employee = payrollDatabase.getEmployee(empId);
        assertNotNull(employee);
        assertNotNull(employee.getAffiliation());
        assertTrue(employee.getAffiliation() instanceof NoAffiliation);
        Employee noAffiliation = payrollDatabase.getUnionMember(1001);
        assertNull(noAffiliation);

    }

    /**
     * 支付单个受雇员工（月薪，支付日）
     */
    public void testPaySingleSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00d);
        ase.execute();

        Date payDate = new Date(2021, 9 - 1, 30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        PayCheck pc = pt.getPaycheck(empId);
        assertNotNull(pc);
        assertTrue(pc.getPayDate().equals(payDate));
        assertEquals(1000.00d, pc.getGrossPay(), 0.001d);
        assertTrue("Hold".equals(pc.getDisposition()));
        assertEquals(0.0d, pc.getDeductions(), 0.001d);
        assertEquals(1000.00d, pc.getNetPay(), 0.001d);
    }

    /**
     * 支付单个受雇员工（月薪，非支付日）
     */
    public void testPaySingleSalariedEmployeeOnWrongDate() {
        int empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00d);
        ase.execute();

        Date payDate = new Date(2021, 9 - 1, 29);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        PayCheck pc = pt.getPaycheck(empId);
        assertNull(pc);
    }

    /**
     * 支付单个受雇员工（小时工，没有时间卡、支付日）
     */
    public void testPaySingleHourlyEmployeeNoTimeCards() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        Date payDate = new Date(2021, 9 - 1, 10);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(empId, payDate, pt, 0d);
    }

    private void validatePaycheck(int empId, Date payDate, PaydayTransaction pt, double pay) {
        PayCheck paycheck = pt.getPaycheck(empId);
        assertNotNull(paycheck);
        assertEquals(payDate, paycheck.getPayDate());
        assertEquals("Hold", paycheck.getDisposition());
        assertEquals(pay, paycheck.getNetPay(), 0.001d);
        assertEquals(0d, paycheck.getDeductions(), 0.001d);
    }

    /**
     * 支付单个受雇员工（小时工，有时间卡，未加班、支付日）
     */
    public void testPaySingleHourlyEmployeeOneTimeCard() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        Date payDate = new Date(2021, 9 - 1, 10);
        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 2.0d);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(empId, payDate, pt, 30.5d);
    }

    /**
     * 支付单个受雇员工（小时工，有时间卡，有加班、支付日）
     */
    public void testPaysingleHourlyEmployeeOvertimeTimeCard() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        Date payDate = new Date(2021, 9 - 1, 10);
        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 9.0d);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(empId, payDate, pt, (8 + 1.5) * 15.25d);
    }

    /**
     * 支付单个受雇员工（小时工，有时间卡，有加班、非支付日）
     */
    public void testPaySingleHourlyEmployeeOnWrongDate() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        Date payDate = new Date(2021, 9 - 1, 11);
        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 9.0d);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        assertNull(pt.getPaycheck(empId));
    }

    /**
     * 支付单个受雇员工（小时工，有两个时间卡，有加班、支付日）
     */
    public void testPaySingleHourlyEmployeeTwoTimeCards() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        Date payDate = new Date(2021, 9 - 1, 9);
        Date payDate2 = new Date(2021, 9 - 1, 10);
        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 9.0d);
        tct.execute();
        TimeCardTransaction tct2 = new TimeCardTransaction(empId, payDate2, 2.0d);
        tct2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate2);
        pt.execute();
        validatePaycheck(empId, payDate2, pt, ((8 + 1.5) * 15.25d) + (2d * 15.25d));
    }

    /**
     * 支付单个受雇员工（小时工，有三个时间卡，有加班、支付日）
     * 测试支付本期内的时间卡
     */
    public void testPaySingleHourlyEmployeeWithTimeCardSpanningTwoPayPeriods() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        Date payDate = new Date(2021, 9 - 1, 9);
        Date payDate2 = new Date(2021, 9 - 1, 10);
        Date payDate3 = new Date(2021, 9 - 1, 2);
        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 9.0d);
        tct.execute();
        TimeCardTransaction tct2 = new TimeCardTransaction(empId, payDate2, 2.0d);
        tct2.execute();
        TimeCardTransaction tct3 = new TimeCardTransaction(empId, payDate3, 8.0d);
        tct3.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate2);
        pt.execute();
        validatePaycheck(empId, payDate2, pt, ((8 + 1.5) * 15.25d) + (2d * 15.25d));
    }

    /**
     * 扣除会费（月薪，加入协会，支付日）
     */
    public void testSalariedUnionMemberDues() {
        int empId = 1;
        AddSalariedEmployee ase = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00d);
        ase.execute();

        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, 7734, 9.42d);
        cmt.execute();

        Date payDate = new Date(2021, 4-1, 30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck paycheck = pt.getPaycheck(empId);
        assertNotNull(paycheck);
        assertEquals(payDate, paycheck.getPayDate());
        assertEquals("Hold", paycheck.getDisposition());
        assertEquals(1000.00d - (9.42d * 5d), paycheck.getNetPay(), 0.001d);
        assertEquals(9.42d * 5d, paycheck.getDeductions(), 0.001d);
    }

    /**
     * 扣除会费、服务费（小时工，加入协会，服务费，时间卡，支付日）
     */
    public void testHourlyUnionMemberServiceCharge() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42d);
        cmt.execute();

        Date payDate = new Date(2021, 9-1, 10);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 19.42d, payDate);
        sct.execute();

        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 8.0d);
        tct.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck paycheck = pt.getPaycheck(empId);
        assertNotNull(paycheck);
        assertEquals(payDate, paycheck.getPayDate());
        assertEquals("Hold", paycheck.getDisposition());
        assertEquals(9.42d + 19.42d, paycheck.getDeductions(), 0.001d);
        assertEquals((8d * 15.25d) - (9.42d + 19.42d), paycheck.getNetPay(), 0.001d);
    }

    /**
     * 扣除会费、服务费（小时工，加入协会，服务费，时间卡，支付日）
     */
    public void testServiceChargesSpanningMultiplePayPeriods() {
        int empId = 2;
        AddHourlyEmployee ahe = new AddHourlyEmployee(empId, "Bill", "Home", 15.25d);
        ahe.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42d);
        cmt.execute();

        Date payDate = new Date(2021, 9-1, 10);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 19.42d, payDate);
        sct.execute();


        Date preFriday = new Date(2021, 9-1, 3);
        ServiceChargeTransaction sct2 = new ServiceChargeTransaction(memberId, 100d, preFriday);
        sct2.execute();
        Date nextFriday = new Date(2021, 9-1, 17);
        ServiceChargeTransaction sct3 = new ServiceChargeTransaction(memberId, 200d, nextFriday);
        sct3.execute();

        TimeCardTransaction tct = new TimeCardTransaction(empId, payDate, 8.0d);
        tct.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck paycheck = pt.getPaycheck(empId);
        assertNotNull(paycheck);
        assertEquals(payDate, paycheck.getPayDate());
        assertEquals("Hold", paycheck.getDisposition());
        assertEquals(8 * 15.25d, paycheck.getGrossPay());
        assertEquals(9.42d + 19.42d, paycheck.getDeductions(), 0.001d);
        assertEquals((8d * 15.25d) - (9.42d + 19.42d), paycheck.getNetPay(), 0.001d);
    }


}
