package com.thoughtworks.springbootemployee.Repository;
import com.thoughtworks.springbootemployee.controller.Company;
import com.thoughtworks.springbootemployee.controller.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public List<Company> getAll() {
        return companies;
    }

    public Company create(Company company) {
        companies.add(company);
        return company;
    }

    public Company getCompany(Integer companyId) {
        return companies.stream().filter(company -> companyId.equals(company.getCompanyId())).findFirst().orElse(null);
    }

    public List<Employee> getCompanyEmployees(Integer companyId) {
        Company target = companies.stream().filter(company -> companyId.equals(company.getCompanyId())).findFirst().orElse(null);
        return target.getEmployees();
    }

    public Company update(Integer companyId, Company companyUpdate) {
        companies.stream().filter(company -> companyId.equals(company.getCompanyId())).findFirst().ifPresent(company -> {
            companies.remove(company);
            companies.add(companyUpdate);
        });
        return companyUpdate;
    }

    public void delete(Integer companyId) {
        companies.stream().filter(company -> companyId.equals(company.getCompanyId())).findFirst().ifPresent(company -> {
            companies.remove(company);
        });
    }
}
