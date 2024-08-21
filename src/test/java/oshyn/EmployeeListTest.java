package oshyn;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.oshyn.exceptions.EmployeeException;
import org.oshyn.models.Employee;
import org.oshyn.models.EmployeeMap;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

public class EmployeeListTest {

    private EmployeeMap employeeMap;
    private Employee employee1;
    private Employee employee2;

    @Before
    public void setUp() {
        employeeMap = new EmployeeMap();
        employee1 = new Employee(1, "John Doe", 50000.0);
        employee2 = new Employee(2, "Jane Smith", 60000.0);
    }

    @Test
    public void testAddEmployee(){
        assertTrue(employeeMap.addEmployee(employee1));
        assertTrue(employeeMap.addEmployee(employee2));

        Map<Integer, Employee> employees = employeeMap.getEmployees();
        assertEquals(2, employees.size());
        assertEquals(employee1, employees.get(1));
        assertEquals(employee2, employees.get(2));
    }

    @Test
    public void testDeleteEmployee() {
        employeeMap.addEmployee(employee1);
        employeeMap.addEmployee(employee2);

        String removedEmployeeName = employeeMap.deleteEmployee(1);
        assertEquals("John Doe", removedEmployeeName);

        assertNull(employeeMap.getEmployee(1));
        assertEquals(1, employeeMap.getEmployees().size());
    }

    @Test
    public void testDeleteEmployeeReturnsNullWhenEmployeeNotFound() {
        assertNull(employeeMap.deleteEmployee(999)); // Employee ID 999 does not exist
    }

    @Test
    public void testEditEmployee() {
        employeeMap.addEmployee(employee1);

        Employee updatedEmployee = new Employee(1, "Johnathan Doe", 55000);
        String oldEmployeeName = employeeMap.editEmployee(1, updatedEmployee);

        assertEquals("John Doe", oldEmployeeName);
        assertEquals(updatedEmployee, employeeMap.getEmployee(1));
    }

    @Test
    public void testEditEmployeeReturnsNullWhenEmployeeNotFound(){
        Employee updatedEmployee = new Employee(3, "Johnny Doe", 55000);
        assertNull(employeeMap.editEmployee(3, updatedEmployee)); // Employee ID 3 does not exist
    }

    @Test
    public void testIsEmpty() {
        assertTrue(employeeMap.isEmpty());
        employeeMap.addEmployee(employee1);
        assertFalse(employeeMap.isEmpty());
    }

    @Test
    public void testContainsEmployee() {
        employeeMap.addEmployee(employee1);

        assertTrue(employeeMap.containsEmployee(1));
        assertFalse(employeeMap.containsEmployee(2));
    }

    @Test
    public void testGetEmployee() {
        employeeMap.addEmployee(employee1);
        employeeMap.addEmployee(employee2);

        assertEquals(employee1, employeeMap.getEmployee(1));
        assertEquals(employee2, employeeMap.getEmployee(2));
    }
}
