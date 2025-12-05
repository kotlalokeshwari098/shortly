package com.javaspring.urlshortner.service;

import com.javaspring.urlshortner.models.User;
import com.javaspring.urlshortner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username:"+username));
        return UserDetailsImpl.build(user);
    }
}
