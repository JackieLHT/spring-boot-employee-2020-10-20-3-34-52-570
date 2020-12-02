package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.controller.Company;

import java.util.List;

public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }
}
