package com.hd.musik.payload;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
