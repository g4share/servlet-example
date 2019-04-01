//Copyright (c) 2023 g4share
package com.g4share.http.servlet;

import com.g4share.http.helper.SessionHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private final SessionHelper sessionHelper = new SessionHelper();

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        sessionHelper.invalidate(request);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}