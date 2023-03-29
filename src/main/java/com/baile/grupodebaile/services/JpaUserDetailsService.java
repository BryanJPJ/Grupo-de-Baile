package com.baile.grupodebaile.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baile.grupodebaile.models.SecurityUser;
import com.baile.grupodebaile.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    public JpaUserDetailsService(UserRepository repository) {
        this.userRepository = repository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SecurityUser user = userRepository.findByUsername(username)
        .map(SecurityUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("username not found"+username));

        return user;
    }
    
}

