package com.example.Backend.service;

import com.example.Backend.model.UserModel;
import com.example.Backend.model.UserPrincipal;
import com.example.Backend.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepo.findByUsername(username);

        if(userModel == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userModel);

    }
}
