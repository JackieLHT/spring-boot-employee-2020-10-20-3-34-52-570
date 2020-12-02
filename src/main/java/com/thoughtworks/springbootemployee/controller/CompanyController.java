package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAll(){
        return companyService.getAll();
    }

    @PostMapping
    public Company create(@RequestBody Company company) {
        return companyService.create(company);
    }

    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable Integer companyId) {
        //change null to exception
        return companyService.getCompany(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId) {
        return companyService.getCompanyEmployees(companyId);
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable Integer companyId, @RequestBody Company companyUpdate) {
        return companyService.update(companyId, companyUpdate);
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable Integer companyId) {
        companyService.delete(companyId);
    }
}
