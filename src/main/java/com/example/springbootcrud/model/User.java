package com.example.springbootcrud.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    long id;

    String firstName;

    String lastName;

    @Column(unique = true)
    String email;

    Date birthday;


    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    Company company;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;


}
