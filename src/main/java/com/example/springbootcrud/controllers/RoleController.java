package com.example.springbootcrud.controllers;

import com.example.springbootcrud.dto.request.RoleDtoForCreate;
import com.example.springbootcrud.model.Role;
import com.example.springbootcrud.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roleList = roleService.getRoles();
        return ResponseEntity.ok(roleList);
    }

    @GetMapping("/{roleId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Role> getRole(@PathVariable("roleId") Long roleId) {
        Role role = roleService.getRole(roleId);
        return ResponseEntity.ok(role);
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Role> createRole(@RequestBody RoleDtoForCreate roleDtoForCreate) {
        return new ResponseEntity<>(roleService.createRole(roleDtoForCreate), HttpStatus.CREATED);
    }

    @PutMapping("{/roleId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Role> updateRole(@RequestBody RoleDtoForCreate roleDtoForCreate,@PathVariable("roleId") Long roleId) {
        return new ResponseEntity<>(roleService.updateRole(roleDtoForCreate,roleId),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{/roleId}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteRole (@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

