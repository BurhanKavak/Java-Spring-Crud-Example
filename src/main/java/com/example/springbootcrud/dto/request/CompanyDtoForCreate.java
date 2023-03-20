package com.example.springbootcrud.dto.request;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class CompanyDtoForCreate {

    @Size(min = 2,max = 12,message = "Şirket ismi 2 ile 12 karakter arasında olmalıdır!!!")
    private String companyName;

    @Size(max = 100,message = "Açıklama 100 karakteri geçmemelidir!!!")
    private String description;

    @Email(message = "Geçerli şirket postası giriniz!!! ")
    private String companyEmail;
}
