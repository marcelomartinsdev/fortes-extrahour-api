package com.example.fortesextrahourapi.config.tocken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.exceptions.CustomTokenCreationException;
import com.example.fortesextrahourapi.exceptions.CustomTokenValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expirationTime;

    public String generateToken(Employee employee) {
        try {
            return JWT.create().withIssuer("ExtraHoursAPI").withSubject(employee.getName()).withClaim("id", employee.getId()).withExpiresAt(new Date(System.currentTimeMillis() + expirationTime)).sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new CustomTokenCreationException("Falha ao criar o Token!", e);
        }
    }

    public String getSubject(String token) {
        try {
            //verificar se o token não é nulo ou vazio
            if (token == null || token.isEmpty()) {
                return null;
            }
            //verificar se o token está expirado
            if (isTokenExpired(token)) {
                return null;
            }

            return JWT.require(Algorithm.HMAC256("${jwt.secret}")).withIssuer("ExtraHoursAPI").build().verify(token).getSubject();
        } catch (JWTCreationException e) {
            throw new CustomTokenValidationException("Falha ao validar o Token!", e);
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            //captura a data de expiração do token
            Date expiration = JWT.decode(token).getExpiresAt();

            //se a data de expiração do token for anterior a data atual, ira cair no erro
            return expiration != null && expiration.before(Date.from(Instant.now()));
        } catch (JWTDecodeException e) {
            return true; // se o token estiver expirado, retorna true, está expirado
        }
    }
}
