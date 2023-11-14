package com.example.SpringT.Service;

import com.example.SpringT.models.User;
import com.example.SpringT.repo.UserRepository;
import com.example.SpringT.securities.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //Checking whether username is unique
    public User registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) {
           throw new SecurityException("The username is already taken");
        }

        //reminder to make password codder here

        return userRepository.save(user);
    }
}
