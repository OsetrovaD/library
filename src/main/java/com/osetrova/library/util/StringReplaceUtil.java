package com.osetrova.library.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringReplaceUtil {

    public static String replaceQuestionMark(String originalString, String replacingValue) {
        return originalString.replace("?", replacingValue);
    }
}
