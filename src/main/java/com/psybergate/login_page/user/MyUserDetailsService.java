package com.psybergate.login_page.user;

import com.psybergate.login_page.role.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.psybergate.login_page.user.User byUserName = this.userRepository.findByUserName(username);
        String password = byUserName.getPassword();
        Set<Role> roles = byUserName.getRoles();
        boolean active = byUserName.getActive();
        return new User(username, password, active,active,active,active,roles);
    }
}
