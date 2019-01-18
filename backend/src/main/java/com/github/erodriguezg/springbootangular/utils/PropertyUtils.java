package com.github.erodriguezg.springbootangular.utils;

import java.lang.reflect.InvocationTargetException;

public class PropertyUtils {

    public void copyProperties(Object destiny, Object origin) {
        try {
            org.apache.commons.beanutils.PropertyUtils
                    .copyProperties(destiny, origin);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

}
