package com.example.springbootcrud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForUpdate {

    @Email(message = "Geçerli posta giriniz!")
    private String email;
}
