package com.example.demo.Util;


import org.springframework.security.core.AuthenticationException;

public class VertificationCodeException extends AuthenticationException {
    public VertificationCodeException(){
        super("Failed to Auth");
    }
}
