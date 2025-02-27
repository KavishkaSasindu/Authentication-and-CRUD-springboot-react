package com.example.Backend.controller;

import com.example.Backend.dto.ResponseDto;
import com.example.Backend.model.UserModel;
import com.example.Backend.service.AuthService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Data
@NoArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    register user
    @PostMapping("/register")
    public ResponseEntity<?> registerUserDb(@RequestBody UserModel userModel) {
        try{

            if(userModel.getUsername().isBlank() && userModel.getPassword().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto("Please provide all required fields"));
            }
            UserModel returnValue = authService.registerUser(userModel);
            if(returnValue == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto("User already exists"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(returnValue);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(e.getMessage()));
        }
    }

}
