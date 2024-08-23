package com.vietqr.org.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilTest {

    @Test
    void testGetNowUTC() {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime nowUtc = now.atZone(ZoneOffset.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
        long result = DateTimeUtil.getNowUTC();

        assertEquals(nowUtc.toEpochSecond(), result, 1, "The epoch second should be correct within 1 second tolerance.");
    }

    @Test
    void testGetTimeUTCNMonthsAgo() {
        final int numOfMonths = 6;
        LocalDateTime nMonthsAgo = LocalDateTime.now().minusMonths(numOfMonths);
        ZonedDateTime nMonthsAgoUtc = nMonthsAgo.atZone(ZoneOffset.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
        long result = DateTimeUtil.getTimeUTCNMonthsAgo(numOfMonths);

        assertEquals(nMonthsAgoUtc.toEpochSecond(), result, 1, "The epoch second should be correct within 1 second tolerance.");
    }

    @Test
    void testGetTimeUTCNMonthsAgoWithZeroMonths() {
        int numOfMonths = 0;
        LocalDateTime now = LocalDateTime.now().minusMonths(numOfMonths);
        ZonedDateTime nowUtc = now.atZone(ZoneOffset.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
        long result = DateTimeUtil.getTimeUTCNMonthsAgo(numOfMonths);

        assertEquals(nowUtc.toEpochSecond(), result, 1, "The epoch second should be correct within 1 second tolerance.");
    }

    @Test
    void testGetTimeUTCNMonthsAgoWithNegativeMonths() {
        final int numOfMonths = -3;
        LocalDateTime futureDate = LocalDateTime.now().minusMonths(numOfMonths);
        ZonedDateTime futureDateUtc = futureDate.atZone(ZoneOffset.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);
        long result = DateTimeUtil.getTimeUTCNMonthsAgo(numOfMonths);

        assertEquals(futureDateUtc.toEpochSecond(), result, 1, "The epoch second should be correct within 1 second tolerance.");
    }
}
