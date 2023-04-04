package com.example.springbootcrud.repository;

import com.example.springbootcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByFirstName(String firstName);
    boolean existsByLastName(String lastName);

    boolean existsById(Long id);

    User findByFirstName(String firstName);

}
