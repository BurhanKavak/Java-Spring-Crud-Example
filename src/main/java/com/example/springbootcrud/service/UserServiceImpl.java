package com.example.springbootcrud.service;

import com.example.springbootcrud.exception.UserNotFoundException;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        //TODO ex. handlending eklenecek.
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    }

    @Override
    public User createUser(User user) {
        // TODO mail uniqe olması lazım
        //Var olan User eklemeye çalıştığımızda UserNotFoundException'ın diğer constructer'ını kullanalım.
        // existingUser o anda veritabanında kayıtlı kullanıcı var mı ona bakıyoruz eğer null gelirse işleme devam etmeliyiz.
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("%s Bu kullanıcı mevcut!!!",user.getId());
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
            log.info("Log :",foundUser);
            return foundUser;
        } else {
            // Optional ile sardığımız kullanıcıyı mevcut ise güncelle değilse hata fırlat.
           // log.error("Log : ", new UserNotFoundException(userId));
            throw new UserNotFoundException(userId);




        }

    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(getUser(userId).getId());
    }
}
