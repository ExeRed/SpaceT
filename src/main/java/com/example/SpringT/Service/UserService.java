package com.example.SpringT.Service;

import com.example.SpringT.models.User;
import com.example.SpringT.repo.UserRepository;
import com.example.SpringT.securities.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new SecurityException("The username is already taken");
        }

        return userRepository.save(user);
    }


    //checking if user's name and password are true

    public boolean loginUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        return existingUser != null && existingUser.getPassword().equals(user.getPassword());
    }


}
