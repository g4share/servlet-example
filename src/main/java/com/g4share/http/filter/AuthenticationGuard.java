//Copyright (c) 2023 g4share
package com.g4share.http.filter;

import com.g4share.http.helper.SessionHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationGuard implements FilterGuard {

    private final SessionHelper sessionHelper;

    @Override
    public boolean check(final HttpServletRequest request,
                         final HttpServletResponse response) throws IOException {
        final String currentUrl = request.getRequestURI();

        if (sessionHelper.getPrincipal(request).authenticated()) {
            return true;
        }

        final String loginURI = request.getContextPath() + "/login";
        sessionHelper.setReturnUrl(request, currentUrl);
        response.sendRedirect(loginURI);

        return false;
    }
}