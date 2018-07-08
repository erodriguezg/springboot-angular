package com.github.erodriguezg.springbootangular.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class FechaUtils {

    private FechaUtils() {}

    public static Date parse(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

}
