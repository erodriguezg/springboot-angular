package com.github.erodriguezg.springbootangular.config;

import com.github.erodriguezg.security.jwt.TokenAuthenticationHttpHandler;
import com.github.erodriguezg.security.jwt.TokenService;
import com.github.erodriguezg.springbootangular.security.SecurityMappings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class TokenSecurityConfig {

    @Bean
    public TokenAuthenticationHttpHandler jwtTokenAuthenticationHttpHandler(
            TokenService tokenService, SecurityMappings securityMappings) {
        return new TokenAuthenticationHttpHandler(
                tokenService,
                securityMappings::tokenSubjectMapToAuth,
                securityMappings::authToTokenSubjectMap);
    }

    @Bean
    public TokenService jwtTokenService(
            @Value("${app.jwt.secret-phrase}") String jwtSecretPhrase,
            @Value("${app.jwt.expiration-minutes}") Long jwtExpirationMinutes) {
        return new TokenService(jwtSecretPhrase, TimeUnit.MINUTES, jwtExpirationMinutes);
    }

    @Bean
    public SecurityMappings securityMappers() {
        return new SecurityMappings();
    }

}
