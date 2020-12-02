package com.thoughtworks.springbootemployee.controller;

import java.util.List;

public class Company {
    private Integer companyId;
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;

    public Company(Integer companyId, String companyName, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employees = employees;
        this.employeeNumber = employees.size();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getName() {
        return companyName;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
