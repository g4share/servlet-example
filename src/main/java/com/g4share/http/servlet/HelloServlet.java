//Copyright (c) 2023 g4share
package com.g4share.http.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.MessageFormat;

public class HelloServlet extends HttpServlet {

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(sayHello(null));
    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        final String name = data.get("name").getAsString();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(sayHello(name));
    }

    private String sayHello(final String name) {

        return "{ \"message\": \"Hello, World\" " +
                (name == null
                        ? "}"
                        : MessageFormat.format(", \"name\": \"{0}\" }", name));

    }
}