package com.example.springbootcrud.repository;

import com.example.springbootcrud.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    boolean existsByCompanyName(String companyName);
}
