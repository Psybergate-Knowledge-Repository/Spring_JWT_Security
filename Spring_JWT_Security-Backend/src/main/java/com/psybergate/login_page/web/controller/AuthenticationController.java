package com.psybergate.login_page.web.controller;

import com.psybergate.login_page.jwt.JWTUtil;
import com.psybergate.login_page.jwt.JwtDTO;
import com.psybergate.login_page.user.MyUserDetailsService;
import com.psybergate.login_page.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService myUserDetailsService;

    private final JWTUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JWTUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtDTO> createAuthenticationToken(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect username or password", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(userDTO.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtDTO(jwt));
    }

}
