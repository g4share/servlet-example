//Copyright (c) 2023 g4share

package com.g4share.rest.helper;

import jakarta.servlet.http.HttpServletRequest;

public class SessionHelper {

    public String path(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String basePath = request.getContextPath();
        return requestURI.substring(basePath.length());
    }
}