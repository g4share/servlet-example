//Copyright (c) 2023 g4share
package com.g4share.http.data;

public record Principal(boolean authenticated, User user) {
}
