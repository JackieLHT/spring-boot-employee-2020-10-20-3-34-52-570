package com.thoughtworks.springbootemployee.Repository;
import com.thoughtworks.springbootemployee.controller.Company;
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
}
