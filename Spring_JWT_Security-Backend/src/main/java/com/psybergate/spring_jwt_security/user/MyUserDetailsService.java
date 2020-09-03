package com.psybergate.spring_jwt_security.user;

import com.psybergate.spring_jwt_security.role.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.psybergate.spring_jwt_security.user.User byUserName = this.userRepository.findByUserName(username);
        String password = byUserName.getPassword();
        List<Role> roles = byUserName.getRoles();
        boolean active = byUserName.getActive();
        return new User(username, password, active,active,active,active,roles);
    }
}
