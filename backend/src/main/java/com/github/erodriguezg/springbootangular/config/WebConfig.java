package com.github.erodriguezg.springbootangular.config;

import com.github.erodriguezg.springbootangular.security.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by eduar on 15/05/2017.
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
    FileUpload
     */

    @Bean
    public CommonsMultipartResolver filterMultipartResolver() {
        CommonsMultipartResolver filterMultipartResolver = new CommonsMultipartResolver();
        filterMultipartResolver.setMaxUploadSize(20971520);
        return filterMultipartResolver;
    }

    /*
    Filter CORS
     */

    @Bean
    public FilterRegistrationBean<CorsFilter> urlCorsFilter() {
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.INCLUDE, DispatcherType.ASYNC, DispatcherType.ERROR));
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }

}
