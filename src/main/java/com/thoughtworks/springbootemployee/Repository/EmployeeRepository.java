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

    public Employee getEmployee(Integer employeeId) {
        return employees.stream().filter(employee -> employeeId.equals(employee.getId())).findFirst().orElse(null);
    }

    public Employee update(Integer employeeId,Employee employeeUpdate) {
        employees.stream().filter(employee -> employeeId.equals(employee.getId())).findFirst().ifPresent(employee -> {
            employees.remove(employee);
            employees.add(employeeUpdate);
        });
        return employeeUpdate;
    }
}
