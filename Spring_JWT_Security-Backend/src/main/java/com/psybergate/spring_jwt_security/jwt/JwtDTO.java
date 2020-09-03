package com.psybergate.spring_jwt_security.jwt;

public class JwtDTO {

    private final String jwt;

    public JwtDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
