// Copyright (c) 2024 g4share

package com.g4share.rest.controller;

import com.g4share.rest.di.annotations.Get;
import com.g4share.rest.di.annotations.RestController;

@RestController
public class HelloController {

    @Get("/hello")
    public String sayHello() {
        return "Hi, g4share";
    }
}
