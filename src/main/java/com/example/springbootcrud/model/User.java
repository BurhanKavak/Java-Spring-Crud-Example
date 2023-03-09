package com.example.springbootcrud.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
//TODO validation ekle

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Size(min = 2, message = "İsim null olamaz!")
    String firstName;

    @NotBlank(message = "Soyisim boş olamaz!")
    String lastName;


    @Email(message = "Geçerli posta giriniz!")
    @Column(unique = true)
    String email;

    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    Date birthday;


}
