package org.oshyn;

import org.oshyn.exceptions.EmployeeException;
import org.oshyn.models.Employee;
import org.oshyn.models.EmployeeMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static EmployeeMap employeeMap;

    public static void main(String[] args) {
        employeeMap = new EmployeeMap();
        System.out.println("Welcome to the Employee Management System!\n");
        mainMenu();
    }

    /**
     * Prints the main menu and handles user input for
     * main menu commands.
     */
    public static void mainMenu() {
        System.out.println("1. Add an employee");
        System.out.println("2. Delete an employee");
        System.out.println("3. Edit an employee");
        System.out.println("4. Print employees");
        System.out.println("5. Search employees");
        System.out.println("0. Exit\n");

        try {
            int userInput = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like to do."));

            if (userInput >= 0 && userInput <=5) {
                if (userInput == 1) addEmployee();
                if (userInput == 2) deleteEmployee();
                if (userInput == 3) editEmployee();
                if (userInput == 4) checkEmployees();
                if (userInput == 5) searchEmployee();
                if (userInput == 0) System.exit(0);
            }else {
                System.out.println("Please enter a number from 0 - 5");
                mainMenu();
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number from 0 - 5");
            mainMenu();
        }
    }



    /**
     * Menu Option: 1
     * Provides the user interface for adding a new employee.
     */
    public static void addEmployee() {
        Employee employeeToAdd = new Employee();
        try {
            String idString = inputOutput("Please type the employee ID: ");
            employeeToAdd.setId(idString);

            String nameString = inputOutput("Please type the employee name: ");
            employeeToAdd.setName(nameString);

            String salaryString = inputOutput("Please type the employee salary: ");
            employeeToAdd.setSalary(salaryString);

            boolean employeeAdded = employeeMap.addEmployee(employeeToAdd);
            if (employeeAdded) {
                System.out.println(nameString + " successfully added.\n");
            } else {
                System.out.println(nameString + " could not be added, ID must be unique.\n");
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        } finally {
            mainMenu();
        }
    }

    /**
     * Menu Option: 2
     * Provides the user interface for deleting an existing employee.
     */
    public static void deleteEmployee() {
        if (employeeMap.isEmpty()) {
            System.out.println("There is no employee to delete.\n");
            mainMenu();
            return;
        }
        printEmployees();

        int employeeToDeleteId = employeeListSelection("Please type the employee ID to delete.");
        if(employeeToDeleteId == -1){
            mainMenu();
        }

        String employeeDeleted = employeeMap.deleteEmployee(employeeToDeleteId);

        if (employeeDeleted != null) {
            System.out.println(employeeDeleted + " successfully deleted.\n");
        } else {
            System.out.println("Selected employee doesn't exist and could not be deleted.\n");
        }

        mainMenu();
    }

    /**
     * Menu Option: 3
     * Provides the user interface for updating an existing employee.
     */
    public static void editEmployee() {
        if (employeeMap.isEmpty()) {
            System.out.println("There is no employee to edit.\n");
            mainMenu();
            return;
        }
        printEmployees();
        int employeeToEditId = employeeListSelection("Please select the ID of the employee to edit.");
        if(employeeToEditId == -1){
            mainMenu();
            return;
        }

        Employee newEmployee = new Employee();
        try {
            newEmployee.setId(employeeToEditId);

            String nameString = inputOutput("Please enter the employee name: ");
            newEmployee.setName(nameString);

            String salaryString = inputOutput("Please enter the employee salary: ");
            newEmployee.setSalary(salaryString);

            String employeeEdited = employeeMap.editEmployee(employeeToEditId, newEmployee);

            if (employeeEdited != null) {
                System.out.println(employeeEdited + " successfully edited.\n");
            } else {
                System.out.println("Employee could not be edited.\n");
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        } finally {
            mainMenu();
        }
    }

    /**
     * Menu Option: 4
     * Provides the user interface for printing all the existing employees.
     */
    public static void checkEmployees() {
        if (employeeMap.isEmpty()) {
            System.out.println("No employees found.\n");
        } else {
            System.out.println("Employees:");
            printEmployees();
            System.out.println();
        }
        mainMenu();
    }

    /**
     * Menu Option: 5
     * Provides the user interface for searching an employee by his id.
     */
    private static void searchEmployee() {
        int employeeId = employeeListSelection("Enter the ID of an employee: ");
        if (employeeId != -1) {
            System.out.println(employeeMap.getEmployee(employeeId));
        }
        mainMenu();
    }

    /**
     * Print all employees, not related directly to menu options
     */
    public static void printEmployees() {
        for (Employee employee : employeeMap.getEmployees().values()) {
            System.out.println(employee);
        }
    }

    /**
     * Passes a prompt to the user and returns the user-specified string.
     *
     * @param message
     * @return String
     */
    private static String inputOutput(String message) {
        System.out.println(message);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String returnString = "";
        try {
            returnString = br.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input");
            mainMenu();
        }
        return returnString;
    }

    /**
     * Passes a prompt to the user and returns an existing positive integer ID from employees map .
     *
     * @param message
     * @return int
     */
    private static int employeeListSelection(String message) {
        String userSelection = inputOutput(message);
        int employeeId;
        try {
            employeeId = Integer.parseInt(userSelection);
            if (employeeId < 0) {
                throw new NumberFormatException();
            }
            // Verify if the entered ID exists in the HashMap
            if (!employeeMap.containsEmployee(employeeId)) {
                throw new EmployeeException("Employee ID not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please type a valid positive integer number corresponding to an employee ID.");
            return -1;
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return employeeId;
    }
}
