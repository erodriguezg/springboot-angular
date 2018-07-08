package com.github.erodriguezg.springbootangular.config;

import com.github.erodriguezg.security.jwt.StatelessAuthenticationFilter;
import com.github.erodriguezg.security.jwt.TokenAuthenticationHttpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Filter statelessAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(TokenAuthenticationHttpHandler jwtTokenAuthenticationHttpHandler) {
        this.statelessAuthenticationFilter = new StatelessAuthenticationFilter(jwtTokenAuthenticationHttpHandler);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/hello").permitAll()
                .antMatchers("/hello-protected").authenticated()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/*.html", "**/*.html").permitAll()
                .antMatchers("/*.js", "**/*.js").permitAll()
                .antMatchers("/*.css", "**/*.css").permitAll()

                // Protected refresh jwt token
                .antMatchers("/security/refresh").authenticated()

                // Allow anonymous logins
                .antMatchers("/security/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "**").permitAll()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .and()
                .anonymous()
                .and()
                .servletApi()
                .and()
                .headers().cacheControl().disable()
                .and()
                .addFilterAt(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
