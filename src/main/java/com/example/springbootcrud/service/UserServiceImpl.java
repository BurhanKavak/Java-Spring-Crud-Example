package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.request.UserDtoForCreate;
import com.example.springbootcrud.dto.request.UserDtoForUpdate;
import com.example.springbootcrud.dto.response.UserDtoResponse;
import com.example.springbootcrud.exception.UserNotFoundException;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserDtoResponse> getUsers() {
        log.info("Tüm veriler getirildi");
        List<User> users = userRepository.findAll();
        List<UserDtoResponse> userDtoResponseList = users
                .stream()
                .map(user -> modelMapper.map(user,UserDtoResponse.class))
                .collect(Collectors.toList());

        return userDtoResponseList;
    }

    @Override
    public UserDtoResponse getUser(Long userId) {
        //TODO ex. handlending eklenecek.
//        log.info("{}. Kullanıcı getirildi",userId);
//        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        boolean id = userRepository.existsById(userId);
        if (id) {
            log.info("{}. Kullanıcı getirildi",userId);
            User user = userRepository.findById(userId).get();
            UserDtoResponse userDtoResponse = modelMapper.map(user,UserDtoResponse.class);
            return userDtoResponse;
        } else {
            throw new UserNotFoundException(userId);
        }

    }

    @Override
    public UserDtoForCreate createUser(UserDtoForCreate userDtoForCreate) {
        // TODO mail uniqe olması lazım
        //Var olan User eklemeye çalıştığımızda UserNotFoundException'ın diğer constructer'ını kullanalım.

        User user = modelMapper.map(userDtoForCreate,User.class);
        boolean firstName = userRepository.existsByFirstName(user.getFirstName());
        boolean lastName = userRepository.existsByLastName(user.getLastName());
        if (firstName && lastName ) {
            throw new UserNotFoundException("Bu kullanıcı mevcut!!!");
        } else {
            log.info("Kullanıcı kaydedildi");
            return modelMapper.map(userRepository.save(user), UserDtoForCreate.class);
        }

    }

    @Override
    public User updateUser(UserDtoForUpdate userDtoForUpdate, Long userId) {
        // TODO Age yerine doğum tarihi yap, LOGLAMA yap
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setEmail(userDtoForUpdate.getEmail());
            userRepository.save(foundUser);
            log.info("Bu kullanıcıya güncelleme yapıldı : ",foundUser);
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
}
