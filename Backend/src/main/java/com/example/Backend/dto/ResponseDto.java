package com.example.Backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {

    private String message;

    public ResponseDto(String message) {
        this.message = message;
    }
}
