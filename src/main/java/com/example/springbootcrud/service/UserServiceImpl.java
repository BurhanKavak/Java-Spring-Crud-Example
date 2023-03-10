package com.example.springbootcrud.service;

import com.example.springbootcrud.exception.UserNotFoundException;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getUsers() {
        log.info("Tüm veriler getirildi");
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        //TODO ex. handlending eklenecek.
//        log.info("{}. Kullanıcı getirildi",userId);
//        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        boolean id = userRepository.existsById(userId);
        if (id) {
            log.info("{}. Kullanıcı getirildi",userId);
            return userRepository.findById(userId).get();
        } else {
            throw new UserNotFoundException(userId);
        }

    }

    @Override
    public User createUser(User user) {
        // TODO mail uniqe olması lazım
        //Var olan User eklemeye çalıştığımızda UserNotFoundException'ın diğer constructer'ını kullanalım.
        boolean firstName = userRepository.existsByFirstName(user.getFirstName());
        boolean lastName = userRepository.existsByLastName(user.getLastName());
        if (firstName && lastName ) {
            throw new UserNotFoundException("Bu kullanıcı mevcut!!!");
        } else {
            log.info("Kullanıcı kaydedildi");
            return userRepository.save(user);
        }

    }

    @Override
    public User updateUser(User newUser, Long userId) {
        // TODO Age yerine doğum tarihi yap, LOGLAMA yap
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = getUser(userId);
            foundUser.setFirstName(newUser.getFirstName());
            foundUser.setLastName(newUser.getLastName());
            foundUser.setEmail(newUser.getEmail());
            foundUser.setBirthday(newUser.getBirthday());
            userRepository.save(foundUser);
            log.info("Bu kullanıcıya güncelleme yapıldı : ",foundUser);
            return foundUser;
        } else {
            // Optional ile sardığımız kullanıcıyı mevcut ise güncelle değilse hata fırlat.
            log.error("User bulunamadı  ");
            throw new UserNotFoundException(userId);




        }

    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(getUser(userId).getId());
        log.info("Kullanıcı silindi : ", userId);
    }
}
