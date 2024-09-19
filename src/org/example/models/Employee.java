package org.example.models;

import org.example.FormatHelper;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents employee that has id, name, pay rate, work entries, and calculated total pay
 */
public class Employee {
    // static counter used when generating employee ids
    private static int idCounter = 1;
    private final String id;
    private String name;
    private Double payRate;
    private ArrayList<WorkEntry> workEntries;
    private Double totalPay;

    /**
     * Constructor with parameters to initialize data
     * @param name name of employee
     * @param payRate pay rate of employee
     */
    public Employee(String name, Double payRate) {
        this.id = generateId();
        this.name = name;
        this.payRate = payRate;
        this.workEntries = new ArrayList<>();
        this.totalPay = 0.0;
    }

    // method to generate employee ids
    private String generateId() {
        return "EMP" + (idCounter++);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " +
                "Name: " + name + ", " +
                "Pay Rate: " + payRate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPayRate() {
        return payRate;
    }

    /* if pay rate can be set, need to update work entry
    to store pay rate at time of their entries, so that
    pay is calculated correctly for it.
     */
    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public void addWorkEntry(WorkEntry workEntry) {
        workEntries.add(workEntry);
        calculateTotalPay();
    }

    public void calculateTotalPay() {
        totalPay = 0.0;
        for(WorkEntry workEntry:workEntries){
            totalPay += workEntry.calculatePay();
        }
    }

    public ArrayList<WorkEntry> getWorkEntries(){
        return workEntries;
    }
    /* method to get existing dates in work entry list
    * to assert date hasn't been entered for a work entry for an employee
    */
    public ArrayList<LocalDate> getExistingDatesInWorkEntryList() {
        ArrayList<LocalDate> existingDates = new ArrayList<>();
        for(WorkEntry workEntry: workEntries){
            LocalDate payrollDate = workEntry.getPayrollDate();
            if(!existingDates.contains(payrollDate)){
                existingDates.add(payrollDate);
            }
        }
        return existingDates;
    }

    public void generateEmployeeReport() {
        StringBuilder report = new StringBuilder(String.format("Report for %s %s, Pay Rate: %.2f%n", id, name, payRate));
        String headerRow = String.format("%-20s %-10s %-10s%n", "Payroll Date", "Hours", "Pay");
        report.append(headerRow);
        for(WorkEntry workEntry : workEntries) {
            String formattedPayRollDateWithTags = workEntry.getFormattedPayRollDateWithTags();
            report.append(formattedPayRollDateWithTags);
            String hoursWorked = String.format("%-10s ", workEntry.getHoursWorked());
            report.append(hoursWorked);
            String payForRow = String.format("%-10s%n", FormatHelper.formatCurrency(workEntry.calculatePay()));
            report.append(payForRow);
        }
        String totalPayRow = String.format("%nTotal Pay: %s%n", FormatHelper.formatCurrency(totalPay));
        report.append(totalPayRow);
        System.out.println(report);
    }
}
