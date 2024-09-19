package org.example.models;

import org.example.DateHelper;

import java.time.LocalDate;

/**
 * Represents work entry that has employee, payroll date and hours worked
 */
public class WorkEntry {

    private Employee employee;
    private LocalDate payrollDate;
    private double hoursWorked;

    // if we don't intend to ever edit payrollDate, these can be made final
    private boolean IsWeekend;
    private boolean IsHoliday;

    /**
     * Constructor with parameters to initalize data
     * @param employee employee
     * @param dateTime local date
     * @param hoursWorked hours worked
     */
    public WorkEntry(Employee employee, LocalDate dateTime, Double hoursWorked){
        this.employee = employee;
        this.payrollDate = dateTime;
        this.hoursWorked = hoursWorked;
        // assigned on construction. if we want to edit payroll date, these two properties
        // would need to be recalculated.
        this.IsWeekend = DateHelper.assertIfDateIsWeekend(payrollDate);
        this.IsHoliday = DateHelper.assertIfDateIsHoliday(payrollDate);
    }

    public double calculatePay(){
        // number of hours employee can work before overtime pay applies on a non-holiday weekday
        double REGULAR_HOURS = 7.5;
        // rate for overtime and holiday pay
        double TIME_AND_A_HALF = 1.5;
        Double payRate = getEmployee().getPayRate();

        // if holiday or weekend, get paid time and a half
        if( isWeekend() || isHoliday()) {
            return TIME_AND_A_HALF*hoursWorked*payRate;
        } else if (getHoursWorked() > REGULAR_HOURS){
            return REGULAR_HOURS*payRate + (hoursWorked- REGULAR_HOURS)*payRate*TIME_AND_A_HALF;
        } else {
            return hoursWorked*payRate;
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getPayrollDate() {
        return payrollDate;
    }

    // if used, would need to make methods to recalculate isWeekend and isHoliday()
    public void setPayrollDate(LocalDate payrollDate) {
        this.payrollDate = payrollDate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public boolean isWeekend() {
        return IsWeekend;
    }

    public boolean isHoliday() {
        return IsHoliday;
    }

    public String getFormattedPayRollDateWithTags() {
        String payRollDateWithTags = String.format("%s", payrollDate);

        // Holiday takes precedence over Weekend (no need to show both)
        // ex. 2020-12-25 is a Holiday, 2021-12-25 is a Holiday and a Weekend, but shows only Holiday
        if (IsHoliday) {
            payRollDateWithTags = payRollDateWithTags.concat(" (Holiday)");
        } else if (IsWeekend) {
            payRollDateWithTags = payRollDateWithTags.concat(" (Weekend)");
        }

        return String.format("%-20s ", payRollDateWithTags);
    }
}
