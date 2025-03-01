package com.example.Backend.controller.user;

import com.example.Backend.dto.ResponseDto;
import com.example.Backend.model.UserModel;
import com.example.Backend.service.user.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    get all users from db
    @GetMapping("/allUsers")
    public ResponseEntity<?> getAll() {
        List<UserModel> allUsers = userService.getALlUsers();
        if(allUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto("No Users are found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }
}
