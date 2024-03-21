package com.example.fortesextrahourapi.config.tocken;

import com.example.fortesextrahourapi.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {


    @Autowired
    public TokenService tokenService;

    @GetMapping
    public String getToken() {
        return tokenService.generateToken(new Employee());
    }
}
