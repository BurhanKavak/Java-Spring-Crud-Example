package com.example.springbootcrud.dto.request;


import com.example.springbootcrud.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForCreate {

    @NotNull
    @Size(max = 12,message = "İsim null olamaz!")
    private String firstName;

    @NotBlank(message = "Soyisim boş olamaz!")
    private String lastName;

    @Email(message = "Geçerli posta giriniz!")
    private String email;


    private Date birthday;

    private Long companyId;
}
