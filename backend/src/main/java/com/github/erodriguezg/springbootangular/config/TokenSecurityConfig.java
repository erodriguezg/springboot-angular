package com.github.erodriguezg.springbootangular.config;

import com.github.erodriguezg.security.jwt.SecretWindowRotation;
import com.github.erodriguezg.security.jwt.TokenAuthenticationHttpHandler;
import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.security.jwt.TokenServiceBuilder;
import com.github.erodriguezg.springbootangular.security.Identidad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.concurrent.TimeUnit;

@Configuration
public class TokenSecurityConfig {

    @Bean
    public TokenAuthenticationHttpHandler<Identidad> jwtTokenAuthenticationHttpHandler(
            TokenService<Identidad> tokenService) {
        return new TokenAuthenticationHttpHandler<>(
                tokenService,
                identidad -> new UsernamePasswordAuthenticationToken(
                        identidad, identidad.getPassword(), identidad.getAuthorities()),
                authentication -> (Identidad) authentication.getPrincipal()
        );
    }


    @Bean
    public TokenService<Identidad> jwtTokenService(
            @Value("${app.jwt.secret-phrase}") String jwtSecretPhrase,
            @Value("${app.jwt.expiration-minutes}") Long jwtExpirationMinutes) {
        return new TokenServiceBuilder<>(Identidad.class)
                .setSecretWindowRotation(new SecretWindowRotation(jwtSecretPhrase, TimeUnit.MINUTES, (int) (jwtExpirationMinutes / 2)))
                .setExpirationTime(TimeUnit.MINUTES, jwtExpirationMinutes)
                .build();
    }

}
