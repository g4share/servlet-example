//Copyright (c) 2023 g4share
package com.g4share.http.helper;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class MenuHelper {

    public static String ifActive(final HttpServletRequest request,
                                  final String cssClass,
                                  final String... pathsRequired) {
        final String servletPath = getServletPath(request);

        return Arrays.asList(pathsRequired).contains(servletPath) ? cssClass : "";
    }

    private static String getServletPath(final HttpServletRequest request) {
        final Object servletPath = request.getAttribute("servletPath");
        return servletPath == null
                ? request.getServletPath()
                : servletPath.toString();
    }
}
