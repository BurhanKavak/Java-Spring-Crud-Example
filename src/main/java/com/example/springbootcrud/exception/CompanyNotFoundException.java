package com.example.springbootcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CompanyNotFoundException extends NotFoundException{
    public CompanyNotFoundException(Long companyId) {
        super(String.format("%s. Şirket Bulunamadı!!!",companyId));
    }

    public CompanyNotFoundException(String message) {
        super(String.format(message));
    }
}
