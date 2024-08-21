package org.oshyn.models;

import org.oshyn.exceptions.EmployeeException;

import java.util.HashMap;
import java.util.Map;

public class EmployeeMap {
    /**
     * @author Paul Roman
     *
     * Employees Map for Oshyn Test
     */
    private Map<Integer, Employee> employeeMap;

    public EmployeeMap(){
        this.employeeMap = new HashMap<>();
    }

    public Map<Integer, Employee> getEmployees(){
        return this.employeeMap;
    }

    public boolean addEmployee(Employee employee) {
        if (employeeMap.containsKey(employee.getId())) {
            return false;
        }
        employeeMap.put(employee.getId(), employee);
        return true;
    }

    public String deleteEmployee(int employeeToDeleteId) {
        Employee removedEmployee = this.employeeMap.remove(employeeToDeleteId);
        if (removedEmployee != null) {
            return removedEmployee.getName();
        } else {
            return null;
        }
    }

    public String editEmployee(int employeeToEditId, Employee newEmployee) {
        if (this.employeeMap.containsKey(employeeToEditId)) {
            Employee oldEmployee = this.employeeMap.put(employeeToEditId, newEmployee);
            return oldEmployee.getName();
        } else {
            return null;
        }
    }

    public boolean isEmpty(){
        return this.employeeMap.isEmpty();
    }
    public boolean containsEmployee(int employeeId){
        return this.employeeMap.containsKey(employeeId);
    }
    public Employee getEmployee(int employeeId){
        return this.employeeMap.get(employeeId);
    }
}
