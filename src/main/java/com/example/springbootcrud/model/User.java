package com.example.springbootcrud.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "User",description = "Benim User Modelim")
public class User {
//TODO validation ekle

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "User nesnesinin tekil Id alanı")
    long id;

    @NotNull
    @Size(min = 2, message = "İsim null olamaz!")
    @ApiModelProperty(value = "User nesnesinin isim alanı")
    String firstName;

    @NotBlank(message = "Soyisim boş olamaz!")
    @ApiModelProperty(value = "User nesnesinin soyisim alanı")
    String lastName;


    @Email(message = "Geçerli posta giriniz!")
    @Column(unique = true)
    @ApiModelProperty(value = "User nesnesinin email alanı")
    String email;

    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "User nesnesinin doğum tarihi alanı")
    Date birthday;


}
