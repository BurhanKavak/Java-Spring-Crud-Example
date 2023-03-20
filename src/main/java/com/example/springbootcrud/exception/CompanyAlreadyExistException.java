package com.example.springbootcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class CompanyAlreadyExistException extends NotFoundException{

    public CompanyAlreadyExistException(String message) {
        super(message);
    }
}
