package com.vietqr.org.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    public static <T> String generateUniqueId(JpaRepository<T, String> repo) {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (repo.existsById(uuid));

        return uuid;
    }
}
