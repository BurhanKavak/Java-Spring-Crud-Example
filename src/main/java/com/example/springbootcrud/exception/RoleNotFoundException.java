package com.example.springbootcrud.exception;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(Long roleId) {
        super(String.format("%s. Role Bulunamadı!!!",roleId));
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
