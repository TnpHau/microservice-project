package com.tnphuchau.userservice.domain.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getCurrentDateTimeWithFormat(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = new Date();

        return df.format(date);
    }
}
