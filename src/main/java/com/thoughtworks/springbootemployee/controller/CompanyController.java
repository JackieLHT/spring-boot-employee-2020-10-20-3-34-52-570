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
}
