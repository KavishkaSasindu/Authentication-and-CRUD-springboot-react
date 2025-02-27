package com.example.Backend.service;

import com.example.Backend.model.UserModel;
import com.example.Backend.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@NoArgsConstructor
public class AuthService {

    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    register user
    public UserModel registerUser(UserModel userModel) {
        UserModel user = userRepo.findByUsername(userModel.getUsername());

        if(user != null) {
            return null;
        }
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        return userRepo.save(userModel);
    }
}
