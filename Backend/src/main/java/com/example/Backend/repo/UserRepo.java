package com.example.Backend.repo;

import com.example.Backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Long> {
    public UserModel findByUsername(String username);
}
