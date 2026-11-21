package com.yash.security_labs_jwt.security.userdetails;

import com.yash.security_labs_jwt.user.entity.User;
import com.yash.security_labs_jwt.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;
    public CustomUserDetailsService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}