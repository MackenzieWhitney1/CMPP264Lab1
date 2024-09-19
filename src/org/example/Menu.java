package org.example;
import org.example.data.EmployeeDB;
import org.example.models.Employee;
import org.example.models.WorkEntry;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class Menu {
    // shows the menu for listing and creating employees, adding work entries, and generating employee reports
    public static void showMenu() {
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. List Employees");
            System.out.println("2. Create Employees");
            System.out.println("3. Add Work Entries");
            System.out.println("4. Employee Report");
            System.out.println("5. Exit");
            choice = Console.getInt("Enter your choice: ", 0, 5);

            switch (choice) {
                case 1:
                    System.out.println("Listing employees.");
                    listEmployeeMenuOption();
                    break;
                case 2:
                    System.out.println("Create employee.");
                    createEmployeeMenuOption();
                    break;
                case 3:
                    System.out.println("Create work entry");
                    addWorkEntriesMenuOption();
                    break;
                case 4:
                    System.out.println("Employee report");
                    employeeReportMenuOption();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

    }

    // list employees
    private static void listEmployeeMenuOption() {
        EmployeeDB.listEmployees();
        Console.waitForEnter();
    }

    // create employees with option to add work entries and report on them afterward.
    public static void createEmployeeMenuOption() {
        String choice = "y";
        while(choice.equalsIgnoreCase("y")) {
            String inputName = Console.getValidNameString("What is the employee's name? (letter, dashes, apostrophes): ");
            Double inputPayRate = Console.getDoubleWithPrecision("What is the employee's pay rate? ", 0, 2);
            Employee createdEmployee = new Employee(inputName, inputPayRate);
            EmployeeDB.addEmployee(createdEmployee);
            // give user the option to add Work Entries for this employee
            String addWorkEntriesOption = Console.getNonEmptyString("Did you want to add work entries for this employee? (y/n) ");
            // we ask once here to enter the loop of enterWorkEntriesForEmployee
            if(addWorkEntriesOption.equalsIgnoreCase("y")) {
                enterWorkEntriesForEmployee(createdEmployee);
            }
            // give user the option to see Report
            String reportOption = Console.getNonEmptyString("Did you want to see this employee's report? (y/n) ");
            if (reportOption.equalsIgnoreCase("y")) {
                generateEmployeeReport(createdEmployee);
            }

            // give user the option to create another employee with same options to add work entries and report
            choice = Console.getNonEmptyString("Did you want to add another employee? (y/n) ");
        }
    }

    // adding work entries
    public static void addWorkEntriesMenuOption(){
        handleEmployeeMenuOption(Menu::enterWorkEntriesForEmployee);
    }

    // generate employee report
    private static void employeeReportMenuOption() {
        handleEmployeeMenuOption(Menu::generateEmployeeReport);
    }

    /* The use of Consumer allows the program to perform a specific action on org.example.models.Employee
    with the 'accept' method. This makes the program more DRY. Before the two refactored methods
    had the same while loop with the only change being the function applied to an employee if not
    found to be null.
     */
    private static void handleEmployeeMenuOption(Consumer<Employee> employeeAction) {
        if(EmployeeDB.getEmployeeList().isEmpty()){
            System.out.println("No employees to act on.");
            return;
        }
        while (true) {
            String choice = Console.getNonEmptyString("Please enter an employee id. ex. 'emp1' (Type 'q' to quit): ");
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
            Employee selectedEmployee = EmployeeDB.getEmployee(choice);
            if (selectedEmployee != null) {
                enterWorkEntriesForEmployee(selectedEmployee); // Consumer is used here.
            } else {
                System.out.println("Employee not found.");
            }
        }
    }

    // generate employee report action used by Consumer.
    private static void generateEmployeeReport(Employee selectedEmployee) {
        selectedEmployee.generateEmployeeReport();
    }

    // enter work entries action used by Consumer.
    public static void enterWorkEntriesForEmployee(Employee employee) {
        String choice = "y";
        while(choice.equalsIgnoreCase("y"))
        {
            List<LocalDate> existingDateList = employee.getExistingDatesInWorkEntryList();
            LocalDate formattedDate = Console.getPastLocalDateNotInList("Input date worked: ", existingDateList);
            double hoursWorked = Console.getDouble("Enter hours worked: ", 0, 16);
            WorkEntry workEntry = new WorkEntry(employee, formattedDate, hoursWorked);
            employee.addWorkEntry(workEntry);
            choice = Console.getNonEmptyString("Did you want to add another entry for this employee (y/n)? ");
        }
    }
}
