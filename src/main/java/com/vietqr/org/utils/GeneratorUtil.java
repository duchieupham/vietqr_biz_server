package com.vietqr.org.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratorUtil {
    public static String generateTerminalCode(String name) {
        final byte LENGTH = 15;
        final byte CONST_LENGTH = 10;

        String result = name.toLowerCase().replaceAll("\\s+", "");
        if (result.length() > CONST_LENGTH) {
            String firstPart = result.substring(0, CONST_LENGTH);
            String randomString = StringUtil.generateRandomString(LENGTH - CONST_LENGTH);
            return firstPart + randomString;
        }
        return result + StringUtil.generateRandomString(LENGTH - result.length());
    }

    public static String generatePublicId(String head) {
        return head + StringUtil.generateRandomString(8);
    }

    public static String generateNameFormatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        return sdf.format(now);
    }
}
