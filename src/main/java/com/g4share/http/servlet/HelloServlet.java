//Copyright (c) 2023 g4share
package com.g4share.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HelloServlet extends HttpServlet {

    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("nick", "g4share");
        request.getRequestDispatcher("hello.jsp").forward(request, response);
    }
}