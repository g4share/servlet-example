//Copyright (c) 2023 g4share
package com.g4share.http.service;

import com.g4share.http.data.User;

import java.util.Optional;

public interface UserService {
    Optional<User> find(String email, String password);
}
