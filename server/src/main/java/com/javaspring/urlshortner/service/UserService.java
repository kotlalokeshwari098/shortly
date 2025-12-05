package com.javaspring.urlshortner.service;

import com.javaspring.urlshortner.models.User;
import com.javaspring.urlshortner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public User registerUser(User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }
}
