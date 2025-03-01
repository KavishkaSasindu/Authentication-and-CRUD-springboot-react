package com.example.Backend.service.user;

import com.example.Backend.model.UserModel;
import com.example.Backend.repo.UserRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    get all users
    public List<UserModel> getALlUsers() {
        return userRepo.findAll();
    }
}
