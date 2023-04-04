package com.example.springbootcrud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForAuth {

    @NotNull
    @Size(max = 12,message = "İsim null olamaz!")
    private String firstName;

    @NotBlank(message = "Soyisim boş olamaz!")
    private String lastName;

    private String password;


}
