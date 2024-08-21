package oshyn;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.oshyn.exceptions.EmployeeException;
import org.oshyn.models.Employee;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee(1, "John Doe", 50000.0);
    }

    @Test
    public void testConstructorAndGetters() {
        Assert.assertEquals(1, employee.getId());
        Assert.assertEquals("John Doe", employee.getName());
        Assert.assertEquals(50000.0, employee.getSalary(), 0.0);
    }

    @Test
    public void testSetId() throws EmployeeException {
        employee.setId(2);
        Assert.assertEquals(2, employee.getId());
    }

    @Test
    public void testSetStringId() throws EmployeeException {
        employee.setId("123");
        Assert.assertEquals(123, employee.getId());
    }

    @Test(expected = EmployeeException.class)
    public void testSetIdThrowsExceptionForInvalidString() throws EmployeeException {
        employee.setId("abc"); // This should throw an exception
    }

    @Test(expected = EmployeeException.class)
    public void testSetIdThrowsExceptionForNegativeValue() throws EmployeeException {
        employee.setId("-1"); // This should throw an exception
    }

    @Test
    public void testSetName() throws EmployeeException {
        employee.setName("Jane Smith");
        Assert.assertEquals("Jane Smith", employee.getName());
    }

    @Test(expected = EmployeeException.class)
    public void testSetNameThrowsExceptionForEmptyName() throws EmployeeException {
        employee.setName(""); // This should throw an exception
    }

    @Test(expected = EmployeeException.class)
    public void testSetNameThrowsExceptionForNullName() throws EmployeeException {
        employee.setName(null); // This should throw an exception
    }

    @Test
    public void testSetSalary() throws EmployeeException {
        employee.setSalary("60000.0");
        Assert.assertEquals(60000.0, employee.getSalary(), 0.0);
    }

    @Test(expected = EmployeeException.class)
    public void testSetSalaryThrowsExceptionForInvalidString() throws EmployeeException {
        employee.setSalary("abc"); // This should throw an exception
    }

    @Test(expected = EmployeeException.class)
    public void testSetSalaryThrowsExceptionForNegativeValue() throws EmployeeException {
        employee.setSalary("-1000.0"); // This should throw an exception
    }

    @Test
    public void testEquals() {
        Employee otherEmployee = new Employee(1, "John Doe", 50000.0);
        Assert.assertTrue(employee.equals(otherEmployee));

        Employee differentEmployee = new Employee(2, "Jane Smith", 60000.0);
        Assert.assertFalse(employee.equals(differentEmployee));

        Assert.assertFalse(employee.equals(null));
        Assert.assertFalse(employee.equals(new Object()));
    }

    @Test
    public void testToString() {
        String expectedString = "ID:" + employee.getId() + ", Nombre: " + employee.getName() + ", Salario: " + employee.getSalary();
        Assert.assertEquals(expectedString, employee.toString());
    }
}
