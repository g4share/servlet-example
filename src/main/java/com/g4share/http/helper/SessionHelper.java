//Copyright (c) 2023 g4share
package com.g4share.http.helper;

import com.g4share.http.data.Principal;
import jakarta.servlet.http.HttpServletRequest;

import static com.g4share.http.data.User.anonymousUser;

public class SessionHelper {

    public void invalidate(final HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public void setPrincipal(final HttpServletRequest request, final Principal principal) {
        setAttribute(request, "principal", principal);
    }

    public Principal getPrincipal(final HttpServletRequest request) {
        final Principal principal = getAttribute(request, "principal");
        return principal != null
                ? principal
                : new Principal(false, anonymousUser());
    }

    public void setReturnUrl(final HttpServletRequest request, final String returnUrl) {
        setAttribute(request, "returnUrl", returnUrl);
    }

    public String getReturnUrl(final HttpServletRequest request) {
        return getAttribute(request, "returnUrl");
    }

    private <T> void setAttribute(final HttpServletRequest request, final String name, final T value) {
        request.getSession().setAttribute(name, value);
    }

    private <T> T getAttribute(final HttpServletRequest request, final String name) {
        return (T) request.getSession().getAttribute(name);
    }

    public String path(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String basePath = request.getContextPath();
        return requestURI.substring(basePath.length());
    }
}
