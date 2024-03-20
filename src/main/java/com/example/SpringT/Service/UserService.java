package com.example.SpringT.Service;

import com.example.SpringT.models.User;
import com.example.SpringT.repo.UserRepository;
import com.example.SpringT.securities.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //checking if user's name and password are true
    public boolean loginUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        return existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
    }



}
