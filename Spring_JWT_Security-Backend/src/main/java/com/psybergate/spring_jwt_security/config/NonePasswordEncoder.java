package com.psybergate.spring_jwt_security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NonePasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
