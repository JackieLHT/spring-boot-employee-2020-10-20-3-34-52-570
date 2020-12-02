package com.thoughtworks.springbootemployee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import com.thoughtworks.springbootemployee.controller.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeServiceTest {

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
        //when
        employeeService.create(new Employee(1, "Jackie", 22, "female", 99999));
        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).create(employeeArgumentCaptor.capture());

        //then
        final Employee actual = employeeArgumentCaptor.getValue();
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

    @Test
    public void should_return_all_male_employees_when_getByGender_given_male() {
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee1 = new Employee(1,"david",22,"male",22222);
        Employee employee2 = new Employee(2,"sam",24,"male",11111);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        employeeService.create(employee1);
        employeeService.create(employee2);
        employeeService.create(new Employee(3,"jackie",22,"female",22222));

        //when
        List<Employee> acutal = employeeService.getByGender("male");

        //then
        assertEquals(expected, acutal);
    }

    @Test
    public void should_return_first_two_employees_when_getPaginatedAll_given_page_1_and_pageSize_2() {
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee1 = new Employee(1,"david",22,"male",22222);
        Employee employee2 = new Employee(2,"sam",24,"male",11111);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        employeeService.create(employee1);
        employeeService.create(employee2);
        employeeService.create(new Employee(3,"jackie",22,"female",22222));

        //when
        List<Employee> acutal = employeeService.getPaginatedAll(1,2);

        //then
        assertEquals(expected, acutal);
    }

}
