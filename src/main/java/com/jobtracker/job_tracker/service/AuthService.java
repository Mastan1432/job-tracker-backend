package com.jobtracker.job_tracker.service;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.jobtracker.job_tracker.entity.User;
import com.jobtracker.job_tracker.repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User register(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        return userRepository.save(user);
    }


    public User login(String email,String password){
        Optional<User> optionalUser=userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            throw new RuntimeException("Invalid email or password");
        }

        User user=optionalUser.get();

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid email or password");
        }
        
        return user;
    }
}
