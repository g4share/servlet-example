//Copyright (c) 2023 g4share

package com.g4share.rest;

import com.g4share.rest.di.RestContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.List;

@WebListener
public class ContextListener implements ServletContextListener {

    private final RestContext restContext = RestContext.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        restContext.init(List.of("com.g4share.rest"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("contextDestroyed");
    }
}