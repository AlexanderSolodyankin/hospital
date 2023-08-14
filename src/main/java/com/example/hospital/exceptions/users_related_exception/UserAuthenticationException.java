package com.example.hospital.exceptions.users_related_exception;

public class UserAuthenticationException extends RuntimeException {
    public UserAuthenticationException() {
    }

    public UserAuthenticationException(String message) {
        super(message);
    }
}
