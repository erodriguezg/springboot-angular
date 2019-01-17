package com.github.erodriguezg.springbootangular.config;

import com.github.erodriguezg.javautils.DateUtils;
import com.github.erodriguezg.springbootangular.utils.PropertyUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public DateUtils dateUtils() {
        return new DateUtils();
    }

    @Bean
    public PropertyUtils propertyUtils() {
        return new PropertyUtils();
    }

}
