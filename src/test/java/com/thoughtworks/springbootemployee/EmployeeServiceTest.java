package com.thoughtworks.springbootemployee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import com.thoughtworks.springbootemployee.controller.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
//    @InjectMocks
//    private EmployeeService employeeService;
//
//    @Mock
//    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_get_all_given_all_employees() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        final List<Employee> expected = Arrays.asList(new Employee(1,"david",22,"male",11111));
        when(employeeRepository.getAll()).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getAll();

        //then
        assertEquals(expected,employees);
    }

    @Test
    public void should_pass_employee_data_when_create_employee_give_nothing_in_database() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee(1, "Jackie", 22, "female", 99999);

        //when
        employeeService.create(employee);
        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).create(employeeArgumentCaptor.capture());

        //then
        final Employee actual = employeeService.create(employee);
        assertEquals(1, actual.getId());
        assertEquals("Jackie", actual.getName());
        assertEquals(22, actual.getAge());
        assertEquals("female", actual.getGender());
        assertEquals(99999, actual.getSalary());
    }

    @Test
    public void should_return_a_specific_employee_when_getEmployee_given_an_employeeId() {
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        employeeService.create(new Employee(1,"david",22,"male",99999));
        Employee expected = new Employee(2,"jackie",22,"female",22222);
        employeeService.create(expected);

        //when
        Employee actual = employeeService.getEmployee(2);

        //then
        assertEquals(expected,actual);
    }

    @Test
    public void should_update_employee_when_update_given_an_employeeId_and_request_info() {
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        employeeService.create(new Employee(2,"david",22,"male",22222));
        Employee expected = new Employee(2,"jackie",22,"female",22222);

        //when
        Employee actual = employeeService.update(2,expected);

        //then
        assertEquals(2, actual.getId());
        assertEquals("jackie", actual.getName());
        assertEquals(22, actual.getAge());
        assertEquals("female", actual.getGender());
        assertEquals(22222, actual.getSalary());
    }

    @Test
    public void should_delete_employee_when_delete_given_an_employeeId() {
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        employeeService.create(new Employee(2,"david",22,"male",22222));

        //when
        employeeService.delete(2);

        //then
        assertEquals(null, employeeService.getEmployee(2));
    }

}
