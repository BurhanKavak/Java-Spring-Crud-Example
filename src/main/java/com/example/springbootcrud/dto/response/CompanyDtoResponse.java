package com.example.springbootcrud.dto.response;

import lombok.Data;


@Data
public class CompanyDtoResponse {

    private String companyName;

    private String description;

    private String companyEmail;
}
