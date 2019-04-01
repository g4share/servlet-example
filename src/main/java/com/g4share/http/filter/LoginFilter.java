//Copyright (c) 2023 g4share
package com.g4share.http.filter;

import com.g4share.http.helper.SessionHelper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName="LoginFilter", urlPatterns = { "/*" } )
public class LoginFilter implements Filter {

    private final Map<String, FilterGuard> guards = new HashMap<>();

    @Override
    public void init(final FilterConfig filterConfig)  {
        final SessionHelper sessionHelper = new SessionHelper();

        guards.put("authorization", new AuthorizationGuard(sessionHelper));
        guards.put("authentication", new AuthenticationGuard(sessionHelper));
    }

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        final boolean isAuthorised = checkGuard(request, response, "authorization");
        if (isAuthorised) {
            chain.doFilter(request, response);
            return;
        }

        final boolean isAuthenticated = checkGuard(request, response, "authentication");
        if (!isAuthenticated) {
            return;
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private boolean checkGuard(final HttpServletRequest request,
                               final HttpServletResponse response,
                               final String name) throws IOException {
        final FilterGuard guard = guards.get(name);
        if (guard == null) {
            return false;
        }

        return guard.check(request, response);
    }
}