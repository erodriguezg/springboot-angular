package com.github.erodriguezg.springbootangular.config;

import com.github.erodriguezg.javautils.CodecUtils;
import com.github.erodriguezg.javautils.DateUtils;
import com.github.erodriguezg.springbootangular.security.CorsFilter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by eduar on 15/05/2017.
 */

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CommonsMultipartResolver filterMultipartResolver() {
        CommonsMultipartResolver filterMultipartResolver = new CommonsMultipartResolver();
        filterMultipartResolver.setMaxUploadSize(20971520);
        return filterMultipartResolver;
    }

    /*
    Servlet Dispatcher MVC
     */

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistration() {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(
                dispatcherServlet(), "/*");
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }

    /*
    Listeners
    */

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
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

    /*
    Utilitarios
     */

    @Bean
    public CodecUtils codecUtils() {
        return new CodecUtils();
    }

    @Bean
    public DateUtils dateUtils() {
        return new DateUtils();
    }

}
