package com.github.erodriguezg.grpcjavalab.web.utils;

public final class StringUtil {

    public static String trimOrBlank(String input) {
        if (input != null) {
            return input.trim();
        } else {
            return "";
        }
    }

}
