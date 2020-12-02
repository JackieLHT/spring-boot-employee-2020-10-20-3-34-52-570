package com.thoughtworks.springbootemployee.controller;

import java.util.List;

public class Company {
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;

    public Company(String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.employees = employees;
        this.employeeNumber = employees.size();
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
