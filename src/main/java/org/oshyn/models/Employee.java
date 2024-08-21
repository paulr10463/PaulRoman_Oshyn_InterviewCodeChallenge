package org.oshyn.models;

import org.oshyn.exceptions.EmployeeException;

public class Employee {
    private int id;
    private String name;
    private double salary;

    /**
     * @author Paul Roman
     *
     * Employee Entity for Oshyn Test
     */
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public Employee(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(String id) throws EmployeeException {
        int idParsed;
        try {
            idParsed = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new EmployeeException("ID must be a positive integer");
        }
        if (idParsed < 0) {
            throw new EmployeeException("ID must be a positive integer");
        }
        this.id = idParsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws EmployeeException {
        if(name!=null && !name.isEmpty()) {
            this.name = name;
        }else {
            throw new EmployeeException("Name must be non empty");
        }
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(String salary) throws EmployeeException {
        double salaryParsed;
        try {
            salaryParsed = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            throw new EmployeeException("Salary must be a positive decimal");
        }
        if (salaryParsed < 0.0) {
            throw new EmployeeException("Salary must be a positive decimal");
        }
        this.salary = salaryParsed;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Employee other = (Employee) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ", Nombre: " + this.name + ", Salario: " + this.salary;
    }
}
