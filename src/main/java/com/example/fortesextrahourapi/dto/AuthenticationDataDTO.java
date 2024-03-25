package com.example.fortesextrahourapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationDataDTO {

    private String username;
    private String password;
}
