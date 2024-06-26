package com.example.fortesextrahourapi.config.tocken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.exceptions.CustomTokenCreationException;
import com.example.fortesextrahourapi.exceptions.FortesException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
            return JWT.create().withIssuer("ExtraHoursAPI")
                    .withSubject(employee.getUsername())
                    .withClaim("id", employee.getId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new FortesException("Falha ao criar o Token!", HttpStatus.BAD_REQUEST);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("ExtraHoursAPI")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new FortesException("Token JWT inválido ou expirado!", HttpStatus.BAD_REQUEST);
        }
    }

        public boolean isTokenExpired (String token){
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
