package com.example.springbootcrud.service.impl;

import com.example.springbootcrud.exception.RoleNotFoundException;
import com.example.springbootcrud.dto.request.RoleDtoForCreate;
import com.example.springbootcrud.mapper.RoleMapper;
import com.example.springbootcrud.model.Role;
import com.example.springbootcrud.repository.RoleRepository;
import com.example.springbootcrud.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;



    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));
    }

    @Override
    public Role createRole(RoleDtoForCreate roleDtoForCreate) {

        Role role = roleMapper.roleDtoForCreateToRole(roleDtoForCreate);
        log.info("Role Kaydedildi");
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(RoleDtoForCreate newRole, Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()) {
            Role foundRole = role.get();
            foundRole.setRoleName(newRole.getRoleName());
            roleRepository.save(foundRole);
            return foundRole;
        } else {
            log.error("Role Bulunamadı");
            throw new RoleNotFoundException(roleId);
        }
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(getRole(roleId).getId());
        log.info("{}. Role Silindi",roleId);
    }
}
