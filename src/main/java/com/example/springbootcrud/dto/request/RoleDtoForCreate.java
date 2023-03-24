package com.example.springbootcrud.dto.request;

import com.example.springbootcrud.model.enums.RoleEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
public class RoleDtoForCreate {

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;
}
