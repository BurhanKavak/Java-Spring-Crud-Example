package com.example.springbootcrud.model;

import com.example.springbootcrud.model.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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

    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    @JsonIgnore
    List<User> users;
}
