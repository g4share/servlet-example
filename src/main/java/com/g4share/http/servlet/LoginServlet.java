//Copyright (c) 2023 g4share
package com.g4share.http.servlet;

import com.g4share.http.data.Principal;
import com.g4share.http.data.User;
import com.g4share.http.helper.SessionHelper;
import com.g4share.http.service.SimpleUserService;
import com.g4share.http.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new SimpleUserService();
    private final SessionHelper sessionHelper = new SessionHelper();

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        final Map<String, String> messages = new HashMap<>();
        validateCredentials(messages, email, password);

        if (!messages.isEmpty()) {
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        final Optional<User> user = userService.find(email, password);

        if (user.isEmpty()) {
            messages.put("login", "Unknown login, please try again");

            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        sessionHelper.setPrincipal(request, new Principal(true, user.get()));

        final String returnUrl = sessionHelper.getReturnUrl(request);
        response.sendRedirect(returnUrl == null
                ? request.getContextPath() + "/index.jsp"
                : returnUrl);
    }


    private void validateCredentials(final Map<String, String> messages,
                                     final String email,
                                     final String password) {
        if (email == null || email.isEmpty()) {
            messages.put("email", "Please enter email");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }
    }
}