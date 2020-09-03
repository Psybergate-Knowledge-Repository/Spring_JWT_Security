package com.psybergate.spring_jwt_security.web.controller;

import com.psybergate.spring_jwt_security.jwt.JWTUtil;
import com.psybergate.spring_jwt_security.jwt.JwtDTO;
import com.psybergate.spring_jwt_security.user.MyUserDetailsService;
import com.psybergate.spring_jwt_security.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*","http://localhost:9091/hello"})
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
    public ResponseEntity<Hello>  getHello(){
        return ResponseEntity.ok(new Hello("Hello World"));
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
