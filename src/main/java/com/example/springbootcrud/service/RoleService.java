package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.request.RoleDtoForCreate;
import com.example.springbootcrud.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role getRole(Long roleId);

    Role createRole(RoleDtoForCreate roleDtoForCreate);

    Role updateRole(RoleDtoForCreate roleDtoForCreate, Long roleId);

    void deleteRole(Long roleId);
}
