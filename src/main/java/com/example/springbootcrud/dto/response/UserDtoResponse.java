package com.example.springbootcrud.dto.response;

import com.example.springbootcrud.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {

    private long id;

    private String firstName;

    private String lastName;

    private Long companyId;
    private Long roleId;
}
