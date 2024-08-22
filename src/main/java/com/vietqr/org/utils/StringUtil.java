package com.vietqr.org.utils;

import java.security.SecureRandom;

public class StringUtil {
    final static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static String generateTerminalCode(String name) {
        final byte LENGTH = 50;
        final byte CONST_LENGTH = 40;
        final String symbol = "_";

        String result = name.toLowerCase().replaceAll("\\s+", "");
        if (result.length() >= LENGTH) {
            String firstPart = result.substring(0, CONST_LENGTH);
            String randomString = generateRandomString(LENGTH - CONST_LENGTH - 1);
            return firstPart + symbol + randomString;
        }
        return result + symbol + generateRandomString(LENGTH - result.length() - 1);
    }
}
