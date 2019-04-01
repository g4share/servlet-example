//Copyright (c) 2023 g4share

package com.g4share.rest;

import com.g4share.rest.exception.GenericException;
import com.g4share.rest.exception.MethodNotSupportedException;
import com.g4share.rest.helper.SessionHelper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = { "/*" })
public class DispatcherServlet extends HttpServlet {

    private SessionHelper sessionHelper = new SessionHelper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("DispatcherServlet init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        final String methodRaw = req.getMethod();

        final Optional<HTTP_METHOD> method = HTTP_METHOD.from(methodRaw);
        method.ifPresentOrElse(
                value -> service(value, req, resp),
                () -> { throw new MethodNotSupportedException(methodRaw); }
        );
    }

    private void service(HTTP_METHOD method, HttpServletRequest req, HttpServletResponse resp) {
        String body = body(method, req);

        System.out.println(req.getMethod() + ":  " + sessionHelper.path(req)
                + (body == null || body.isEmpty() ? "" : "\n" + body));
    }

    private String body(HTTP_METHOD method, HttpServletRequest req) {
        try {
            return method.hasBody()
                    ? req.getReader().lines().collect(Collectors.joining(System.lineSeparator()))
                    : null;
        } catch (IOException e) {
            throw new GenericException(e);
        }
    }
}