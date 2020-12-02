package com.thoughtworks.springbootemployee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CompanyServiceTest {
    @Test
    public void should_return_all_companies_when_getAll_given_all_companies() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"david",22,"male",11111));
        employees.add(new Employee(1, "Jackie", 22, "female", 99999));
        final List<Company> expected = Arrays.asList(new Company("OOCL",employees));
        when(companyRepository.getAll()).thenReturn(expected);

        final List<Company> actual = companyService.getAll();

        assertEquals(expected,actual);
    }
}