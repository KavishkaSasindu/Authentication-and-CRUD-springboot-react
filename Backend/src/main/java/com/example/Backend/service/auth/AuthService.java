package com.example.Backend.service.auth;

import com.example.Backend.jwt.JwtService;
import com.example.Backend.model.UserModel;
import com.example.Backend.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
@NoArgsConstructor
public class AuthService {

    private UserRepo userRepo;
    private JwtService jwtService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepo userRepo, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

//    login user
    public String loginUser(UserModel userModel) {
        UserModel user = userRepo.findByUsername(userModel.getUsername());
        if(user != null) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword()));
            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            }
            return null;
        }

        return null;
    }

}
