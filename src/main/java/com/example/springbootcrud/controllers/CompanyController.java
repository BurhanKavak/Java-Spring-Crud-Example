package com.example.springbootcrud.controllers;

import com.example.springbootcrud.dto.request.CompanyDtoForCreate;
import com.example.springbootcrud.dto.request.CompanyDtoForUpdate;
import com.example.springbootcrud.model.Company;
import com.example.springbootcrud.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable("companyId") Long companyId) {
        Company company = companyService.getCompany(companyId);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody CompanyDtoForCreate companyDtoForCreate) {
        return new ResponseEntity<>(companyService.createCompany(companyDtoForCreate), HttpStatus.CREATED);
    }

     @PutMapping("/{companyId}")
    public ResponseEntity<Company> updateCompany(@RequestBody CompanyDtoForUpdate newCompany, @PathVariable("companyId") Long companyId) {
        return new ResponseEntity<>(companyService.updateCompany(newCompany,companyId),HttpStatus.ACCEPTED);
     }

     @DeleteMapping("/{companyId}")
     public ResponseEntity<Void> deleteCompany(@PathVariable("companyId") Long companyId) {
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
}
