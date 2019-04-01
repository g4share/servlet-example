//Copyright (c) 2023 g4share
package com.g4share.http.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public <T> String fromGson(T data) {
        return gson.toJson(data);
    }
}
