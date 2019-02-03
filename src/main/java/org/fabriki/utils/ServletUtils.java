package org.fabriki.utils;

import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
    public static String recuperarBaseUrl(HttpServletRequest request) {
        try (Scanner scanner = new Scanner(request.getRequestURL().toString())) {
            String servletPath = request.getServletPath();
            scanner.useDelimiter(servletPath.substring(servletPath.indexOf('/')));
            return scanner.next();
        }
    }

}
