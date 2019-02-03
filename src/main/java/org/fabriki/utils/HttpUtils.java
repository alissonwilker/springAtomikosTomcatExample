package org.fabriki.utils;

public class HttpUtils {
    public static StringBuilder getUrlQueryParam(String key, String value, boolean firstParameter) {
        StringBuilder strBuilder = new StringBuilder();
        if (!firstParameter) {
            strBuilder.append("&");
        }

        return strBuilder.append(key).append("=").append(value);
    }
}
