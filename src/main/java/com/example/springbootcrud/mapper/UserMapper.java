package com.example.springbootcrud.mapper;

import com.example.springbootcrud.dto.request.UserDtoForCreate;
import com.example.springbootcrud.dto.request.UserDtoForUpdate;
import com.example.springbootcrud.dto.response.UserDtoResponse;
import com.example.springbootcrud.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {

    // @Mapping(source = "companyId",target = "company.id")
    User userDtoForCreateToUser(UserDtoForCreate userDtoForCreate);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "role.id",target = "roleId")
    UserDtoResponse userToUserDtoResponse(User user);
    List<UserDtoResponse> userToUserDtoResponseList(List<User> users);
}
