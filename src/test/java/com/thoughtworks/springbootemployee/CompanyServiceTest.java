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
        employees.add(new Employee(1, "Jackie", 22, "female", 11111));
        final List<Company> expected = Arrays.asList(new Company(1,"OOCL",employees));
        when(companyRepository.getAll()).thenReturn(expected);

        final List<Company> actual = companyService.getAll();

        assertEquals(expected,actual);
    }

    @Test
    public void should_create_and_return_new_company_when_create_given_a_company() {
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"david",22,"male",11111));
        employees.add(new Employee(2, "Jackie", 22, "female", 11111));
        Company actual = companyService.create(new Company(1,"OOCL",employees));

        //then
        assertEquals("OOCL", actual.getName());
        assertEquals(2, actual.getEmployeeNumber());
        assertEquals(employees, actual.getEmployees());
    }

    @Test
    public void should_return_specific_company_when_getCompany_given_companyId() {
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> employees1 = new ArrayList<>();
        List<Employee> employees2 = new ArrayList<>();
        employees1.add(new Employee(1,"david",22,"male",11111));
        employees1.add(new Employee(2, "Jackie", 22, "female", 11111));
        employees2.add(new Employee(3, "sam", 24, "male", 99999));
        companyService.create(new Company(2,"ABC",employees2));
        Company expected = companyService.create(new Company(1,"OOCL",employees1));


        //then
        assertEquals(expected, companyService.getCompany(1));

    }

    @Test
    public void should_return_employee_list_of_specific_company_when_getCompanyEmployees_given_companyId_and_employees() {
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> employees1 = new ArrayList<>();
        List<Employee> employees2 = new ArrayList<>();
        employees1.add(new Employee(1,"david",22,"male",11111));
        employees1.add(new Employee(2, "Jackie", 22, "female", 11111));
        employees2.add(new Employee(3, "sam", 24, "male", 99999));
        companyService.create(new Company(1,"OOCL",employees1));
        companyService.create(new Company(2,"ABC",employees2));

        List<Employee> actual = companyService.getCompanyEmployees(1);

        //then
        assertEquals(employees1, companyService.getCompanyEmployees(1));

    }

    @Test
    public void should_update_company_when_update_given_companyId_and_request_info() {
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> employees1 = new ArrayList<>();
        employees1.add(new Employee(1,"david",22,"male",11111));
        employees1.add(new Employee(2, "Jackie", 22, "female", 11111));
        companyService.create(new Company(1,"OOCL",employees1));

        Company acutal = companyService.update(1,new Company(1,"ABCC",employees1));

        //then
        assertEquals(1, acutal.getCompanyId());
        assertEquals("ABCC", acutal.getName());
        assertEquals(2, acutal.getEmployeeNumber());
        assertEquals(employees1,acutal.getEmployees());

    }

    @Test
    public void should_delete_employee_when_delete_given_an_employeeId() {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> employees1 = new ArrayList<>();
        employees1.add(new Employee(1,"david",22,"male",11111));
        employees1.add(new Employee(2, "Jackie", 22, "female", 11111));
        companyService.create(new Company(1,"OOCL",employees1));

        //when
        companyService.delete(1);

        //then
        assertEquals(null, companyService.getCompany(1));
    }

    @Test
    public void should_return_first_two_companies_when_getPaginatedAll_given_page_1_and_pageSize_2() {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> employeeList1 = new ArrayList<>();
        List<Employee> employeeList2 = new ArrayList<>();
        List<Employee> employeeList3 = new ArrayList<>();
        employeeList1.add(new Employee(1,"david",22,"male",22222));
        employeeList2.add(new Employee(2,"sam",24,"male",11111));
        employeeList3.add(new Employee(3,"peter",25,"male",11111));
        Company company1 = companyService.create(new Company(1,"OOCL",employeeList1));
        Company company2 = companyService.create(new Company(2,"KMB",employeeList2));
        companyService.create(new Company(3,"TVB",employeeList3));
        List<Company> expected = new ArrayList<>();
        expected.add(company1);
        expected.add(company2);

        //when
        List<Company> acutal = companyService.getPaginatedAll(1,2);

        //then
        assertEquals(expected, acutal);
    }
}
