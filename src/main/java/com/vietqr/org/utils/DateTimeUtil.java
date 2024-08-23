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
    static public long getTimeUTCNMonthsAgo(int numOfMonths){
        LocalDateTime nMonthsAgo = LocalDateTime.now().minusMonths(numOfMonths);
        ZonedDateTime nMonthsAgoUtc = nMonthsAgo.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
        return nMonthsAgoUtc.toEpochSecond();
    }
}
