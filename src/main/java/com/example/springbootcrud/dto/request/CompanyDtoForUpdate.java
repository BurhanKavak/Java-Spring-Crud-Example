package com.example.springbootcrud.dto.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CompanyDtoForUpdate {

    @Size(max = 100,message = "Açıklama 100 karakteri geçmemelidir!!!")
    private String description;
}
