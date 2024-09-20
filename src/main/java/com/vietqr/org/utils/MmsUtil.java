package com.vietqr.org.utils;

public class MmsUtil {
    public static boolean isMmsActive(String content){
        return content.contains("SQR");
    }
}
