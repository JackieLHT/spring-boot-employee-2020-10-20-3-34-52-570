package com.thoughtworks.springbootemployee.Repository;

import ch.qos.logback.core.joran.spi.EventPlayer;
import com.thoughtworks.springbootemployee.controller.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public List<Employee> getAll() {
        return employees;
    }

    public Employee create(Employee employee) {
        employees.add(employee);
        return employee;
    }
}
