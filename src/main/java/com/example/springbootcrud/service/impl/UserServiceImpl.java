package com.example.springbootcrud.service.impl;

import com.example.springbootcrud.dto.request.UserDtoForCreate;
import com.example.springbootcrud.dto.request.UserDtoForUpdate;
import com.example.springbootcrud.dto.response.UserDtoResponse;
import com.example.springbootcrud.exception.CompanyNotFoundException;
import com.example.springbootcrud.exception.RoleNotFoundException;
import com.example.springbootcrud.exception.UserNotFoundException;
import com.example.springbootcrud.mapper.UserMapper;
import com.example.springbootcrud.model.Company;
import com.example.springbootcrud.model.Role;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.repository.UserRepository;
import com.example.springbootcrud.service.CompanyService;
import com.example.springbootcrud.service.RoleService;
import com.example.springbootcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CompanyService companyService;

    private final RoleService roleService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDtoResponse> getUsers() {
        log.info("Tüm veriler getirildi");
        List<User> users = userRepository.findAll();

        List<UserDtoResponse> userDtoResponseList = userMapper.userToUserDtoResponseList(users);

        return userDtoResponseList;
    }

    @Override
    public UserDtoResponse getUser(Long userId) {
//        log.info("{}. Kullanıcı getirildi",userId);
//        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        boolean id = userRepository.existsById(userId);
        if (id) {
            log.info("{}. Kullanıcı getirildi", userId);
            User user = userRepository.findById(userId).get();
            UserDtoResponse userDtoResponse = userMapper.userToUserDtoResponse(user);
            return userDtoResponse;
        } else {
            throw new UserNotFoundException(userId);
        }

    }

    @Override
    public UserDtoResponse createUser(UserDtoForCreate userDtoForCreate) {
        Company company = companyService.getCompany(userDtoForCreate.getCompanyId());
        Role role = roleService.getRole(userDtoForCreate.getRoleId());

        User user = userMapper.userDtoForCreateToUser(userDtoForCreate);
        user.setCompany(company);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userDtoForCreate.getPassword()));
        userRepository.save(user);

        UserDtoResponse userDtoResponse = userMapper.userToUserDtoResponse(user);

        if (company == null) {
            throw new CompanyNotFoundException("Company Bilgisi Girmeniz Gerekmektedir!!!");
        } else if (role == null) {
            throw new RoleNotFoundException("Role Bilgisi Girmeniz Gerekmektedir!!!");
        }
        else {
            log.info("Kullanıcı kaydedildi");
            return userDtoResponse;
        }

    }

    @Override
    public User updateUser(UserDtoForUpdate userDtoForUpdate, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Company company = companyService.getCompany(userDtoForUpdate.getCompanyId());
        Role role = roleService.getRole(userDtoForUpdate.getRoleId());
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setEmail(userDtoForUpdate.getEmail());
            foundUser.setCompany(company);
            foundUser.setRole(role);
            userRepository.save(foundUser);
            log.info("Bu kullanıcıya güncelleme yapıldı : ", foundUser);
            return foundUser;
        } else {
            // Optional ile sardığımız kullanıcıyı mevcut ise güncelle değilse hata fırlat.
            log.error("Kullanıcı bulunamadı  ");
            throw new UserNotFoundException(userId);

        }

    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(getUser(userId).getId());
        log.info("{}. Kullanıcı silindi ", userId);
    }

    @Override
    public User findByFirstName(String firstName) {
       return userRepository.findByFirstName(firstName);
    }

    @Override
    public User createUserToAuth(User user) {
        return userRepository.save(user);
    }


}
