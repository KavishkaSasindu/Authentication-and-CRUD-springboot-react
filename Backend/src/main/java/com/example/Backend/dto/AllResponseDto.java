package com.example.Backend.dto;

import com.example.Backend.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllResponseDto {

    private String message;
    private String username;
    private String token;

//    get only data when register
    public AllResponseDto(String message, String username) {
        this.message = message;
        this.username = username;
    }

//    get Token with data when login
    public AllResponseDto(String message,String username, String token) {
        this.message = message;
        this.username = username;
        this.token = token;
    }
}
