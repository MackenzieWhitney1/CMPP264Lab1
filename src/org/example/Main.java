/*
Author: Mackenzie Whitney
Date: Sept 2024

This application allows a user to:
- Create employees and list them.
- Add work entries for employees with Payroll Date and Hours Worked
- Summarize an employee to report on their work entries and total their earnings.
 */
package org.example;

public class Main {
    // entry point of the application.
    public static void main(String[] args) {
        System.out.println("Welcome to the Payroll Application!");
        // show the menu
        Menu.showMenu();
    }
}