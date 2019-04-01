//Copyright (c) 2023 g4share
package com.g4share.http.data;

import lombok.NonNull;

import java.util.Arrays;

public record User(String email,
                   @NonNull String name,
                   @NonNull String[] groups) {
    public static User anonymousUser() {
        return new User(null,
                "Anonymous",
                new String[] { "unauthorized" });
    }

    public boolean isInGroup(final String group) {
        return Arrays.asList(groups).contains(group);
    }
}
