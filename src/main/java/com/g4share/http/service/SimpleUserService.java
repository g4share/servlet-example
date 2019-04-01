//Copyright (c) 2023 g4share
package com.g4share.http.service;

import com.g4share.http.data.User;
import com.g4share.http.helper.ConfigReader;
import com.g4share.http.helper.Crypt;

import java.util.Optional;

public class SimpleUserService implements UserService {

    @Override
    public Optional<User> find(final String email, final String password) {
        final ConfigReader credentialsReader = new ConfigReader("auth/users/" + email, false);
        final String hashed = credentialsReader.read("password");

        if (!Crypt.checkMatch(email, password, hashed)) {
            return Optional.empty();
        }

        final String name = credentialsReader.read("name");
        final String groups = credentialsReader.read("groups");
        return Optional.of(new User(email, name, (groups + ",authorized").split(",")));
    }
}
