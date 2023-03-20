package com.example.springbootcrud.mapper;

import com.example.springbootcrud.dto.request.CompanyDtoForCreate;
import com.example.springbootcrud.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CompanyMapper {
    @Mapping(source = "companyEmail", target = "companyEmail")
    Company companyDtoForCreateToCompany(CompanyDtoForCreate companyDtoForCreate);
}
