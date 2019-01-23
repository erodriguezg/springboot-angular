package com.github.erodriguezg.springbootangular.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringAppContext implements InitializingBean {

    private static SpringAppContext instance;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        setInstance(this);
    }

    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBeanFromContext(Class<T> clazz) {
        if (instance == null) {
            throw new IllegalStateException("No se ha inicializado el bean");
        }
        return instance.getBean(clazz);
    }

    private static void setInstance(SpringAppContext instanceParam) {
        instance = instanceParam;
    }

}

