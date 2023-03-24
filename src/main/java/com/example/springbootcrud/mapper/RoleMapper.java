package com.example.springbootcrud.mapper;

import com.example.springbootcrud.dto.request.RoleDtoForCreate;
import com.example.springbootcrud.model.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    Role roleDtoForCreateToRole(RoleDtoForCreate roleDtoForCreate);

}
