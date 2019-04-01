//Copyright (c) 2023 g4share
package com.g4share.http.servlet;

import com.g4share.http.data.Message;
import com.g4share.http.helper.GsonHelper;
import com.g4share.http.helper.SessionHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = { "/hello-api" })
public class HelloServletApi extends HttpServlet {
    private final SessionHelper sessionHelper = new SessionHelper();

    private final GsonHelper gsonHelper = new GsonHelper();

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final Message message = new Message(sessionHelper.path(request),
                "Hello",
                sessionHelper.getPrincipal(request).user());
        response.getWriter().print(gsonHelper.fromGson(message));
    }
}