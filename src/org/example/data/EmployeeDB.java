package org.example.data;
import org.example.models.Employee;
import java.util.ArrayList;

// simulate employee database
public class EmployeeDB {
    private static ArrayList<Employee> employeeList = new ArrayList<>();

    public static ArrayList<Employee> getEmployeeList(){
        return employeeList;
    }

    public static void addEmployee(Employee emp){
        employeeList.add(emp);
    }

    public static void listEmployees() {
        if(!employeeList.isEmpty()) {
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employees.");
        }
    }

    // get function for Employee that accepts id
    public static Employee getEmployee(String id) {
        for (Employee employee : employeeList) {
            if (employee.getId().equalsIgnoreCase(id)) {
                return employee;
            }
        }
        return null;
    }
}
