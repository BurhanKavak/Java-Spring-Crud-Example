package com.example.springbootcrud.exception;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(Long id) {
        super(String.format("%s. Kullanıcı Bulunamadı!!!", id));

    }

    public UserNotFoundException(String message ,Long id) {
        super(String.format(message, id));
    }


    public UserNotFoundException(String message) {
        super(String.format(message));
    }
}
