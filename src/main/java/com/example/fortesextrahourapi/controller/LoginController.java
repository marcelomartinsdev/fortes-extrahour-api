package com.example.fortesextrahourapi.controller;


import com.example.fortesextrahourapi.config.tocken.TokenService;
import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.dto.BaseResponseDTO;
import com.example.fortesextrahourapi.dto.AuthenticationDataDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class LoginController extends BaseController {


    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> getToken(@RequestBody @Valid AuthenticationDataDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        var authentication =  manager.authenticate(token);
        return ok(tokenService.generateToken((Employee) authentication.getPrincipal()));
    }
}
