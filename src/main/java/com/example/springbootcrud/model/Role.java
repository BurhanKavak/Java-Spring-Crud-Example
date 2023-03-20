package com.example.springbootcrud.model;

import com.example.springbootcrud.model.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    long id;

    @Enumerated(EnumType.STRING)
    RoleEnum roleName;

//    @OneToMany(mappedBy = "role")
//    List<User> users;
}
