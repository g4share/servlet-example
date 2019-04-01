//Copyright (c) 2023 g4share
package com.g4share.http.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface FilterGuard {
    boolean check(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
