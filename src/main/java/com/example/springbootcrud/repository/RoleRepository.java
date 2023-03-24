package com.example.springbootcrud.repository;

import com.example.springbootcrud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
