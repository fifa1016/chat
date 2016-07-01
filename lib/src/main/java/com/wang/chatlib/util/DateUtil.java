package com.wang.chatlib.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wang on 16-7-1.
 */
public class DateUtil {
    public static String getSimpleTimeString(){
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return simpleDateFormat.format( new Date() );
    }
}
