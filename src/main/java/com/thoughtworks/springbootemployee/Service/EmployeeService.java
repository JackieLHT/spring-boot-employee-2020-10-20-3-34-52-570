package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.controller.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    public Employee create(Employee employee) {
        employeeRepository.create(employee);
        return employee;
    }

    public Employee getEmployee(Integer employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }

    public Employee update(Integer employeeId, Employee employeeUpdate) {
        return employeeRepository.update(employeeId,employeeUpdate);
    }

    public void delete(Integer employeeId) {
        employeeRepository.delete(employeeId);
    }
}
