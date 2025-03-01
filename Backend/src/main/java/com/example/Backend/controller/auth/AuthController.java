package com.example.Backend.controller.auth;

import com.example.Backend.dto.AllResponseDto;
import com.example.Backend.dto.ResponseDto;
import com.example.Backend.model.UserModel;
import com.example.Backend.service.auth.AuthService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Data
@NoArgsConstructor
@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {
        if((userModel.getUsername() .isBlank()) && (userModel.getPassword() .isBlank())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("Please provide required fields"));
        }
        UserModel user = authService.registerUser(userModel);

        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("User already exists"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AllResponseDto("User is added successfully",user.getUsername()));
    }

//    login user
    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody UserModel userModel) {
        if((userModel.getUsername() .isBlank()) && (userModel.getPassword() .isBlank())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("Please provide required fields"));
        }

        String tokenValue = authService.loginUser(userModel);
       try{
           if(tokenValue != null) {
               return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(new AllResponseDto("User logged in successfully",userModel.getUsername(),tokenValue));

           }
       }catch (BadCredentialsException e) {
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto(e.getMessage()));
       }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto("Invalid username or password"));

    }

}



