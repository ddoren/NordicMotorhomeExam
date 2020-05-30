package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee extends  Person{
    @Id
    private int employee_id;
    private String employ_pass;
    private String type_employee;

    public Employee() {
    }
    public Employee(int employee_id,String first_name,String last_name, String email, String employ_pass, String type_employee)
    {
        super(first_name,last_name,email);
        this.employee_id=employee_id;
        this.employ_pass=employ_pass;
        this.type_employee=type_employee;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmploy_pass() {
        return employ_pass;
    }

    public void setEmploy_pass(String employ_pass) {
        this.employ_pass = employ_pass;
    }

    public String getType_employee() {
        return type_employee;
    }

    public void setType_employee(String type_employee) {
        this.type_employee = type_employee;
    }
}
