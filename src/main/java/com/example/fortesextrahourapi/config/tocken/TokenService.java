package com.example.fortesextrahourapi.config.tocken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.exceptions.CustomTokenCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expirationTime;
    public String generateToken(Employee employee) {
        try {
            return JWT.create()
                    .withIssuer("ExtraHoursAPI")
                    .withSubject(employee.getNome())
                    .withClaim("id", employee.getId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new CustomTokenCreationException("Falha ao criar o token JWT.", e);
        }
    }
}
