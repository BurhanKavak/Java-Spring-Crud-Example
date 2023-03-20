package com.example.springbootcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(UserNotFoundException ex)
    {
//        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()));
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }


    @ExceptionHandler(CompanyAlreadyExistException.class)
    public @ResponseBody ErrorResponse userAlreadyExist (CompanyAlreadyExistException ex){
        return new ErrorResponse(
                HttpStatus.ALREADY_REPORTED.value(), ex.getMessage()
        );

    }


}
