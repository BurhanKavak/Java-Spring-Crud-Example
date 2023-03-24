package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.request.CompanyDtoForCreate;
import com.example.springbootcrud.dto.request.CompanyDtoForUpdate;
import com.example.springbootcrud.dto.response.CompanyDtoResponse;
import com.example.springbootcrud.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();

    Company getCompany(Long companyId);

    CompanyDtoResponse createCompany(CompanyDtoForCreate company);

    Company updateCompany(CompanyDtoForUpdate newCompany, Long companyId);

    void deleteCompany(Long companyId);

}
