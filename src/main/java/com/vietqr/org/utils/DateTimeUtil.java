package com.vietqr.org.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeUtil {
    static public long getNowUTC(){
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime nowUtc = now.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
        return nowUtc.toEpochSecond();
    }
}
