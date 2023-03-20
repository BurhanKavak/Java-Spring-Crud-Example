package com.example.springbootcrud.service.impl;

import com.example.springbootcrud.dto.request.CompanyDtoForCreate;
import com.example.springbootcrud.dto.request.CompanyDtoForUpdate;
import com.example.springbootcrud.exception.CompanyAlreadyExistException;
import com.example.springbootcrud.exception.CompanyNotFoundException;
import com.example.springbootcrud.exception.UserNotFoundException;
import com.example.springbootcrud.mapper.CompanyMapper;
import com.example.springbootcrud.model.Company;
import com.example.springbootcrud.repository.CompanyRepository;
import com.example.springbootcrud.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;



    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(companyId));
    }

    @Override
    public Company createCompany(CompanyDtoForCreate companyDtoForCreate) {

        Company company = companyMapper.companyDtoForCreateToCompany(companyDtoForCreate);

        boolean companyName = companyRepository.existsByCompanyName(company.getCompanyName());

        if (companyName) {
            throw new CompanyAlreadyExistException("Bu Şirket Mevcut!!!");
        } else {
            log.info("Company Kaydedildi");
            return companyRepository.save(company);
        }

    }

    @Override
    public Company updateCompany(CompanyDtoForUpdate newCompany, Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            Company foundCompany = company.get();
            foundCompany.setDescription(newCompany.getDescription());
            companyRepository.save(foundCompany);
            return foundCompany;
        } else {
            log.error("Company bulunamadı  ");
            throw new UserNotFoundException(companyId);
        }
    }

    @Override
    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(getCompany(companyId).getId());
        log.info("{}. Company silindi ", companyId);
    }
}
