package com.example.springbootcrud.exception;


public class CompanyNotFoundException extends NotFoundException{
    public CompanyNotFoundException(Long companyId) {
        super(String.format("%s. Şirket Bulunamadı!!!",companyId));
    }

    public CompanyNotFoundException(String message) {
        super(String.format(message));
    }
}
