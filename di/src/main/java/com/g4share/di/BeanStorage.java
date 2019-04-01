// Copyright (c) 2024 g4share

package com.g4share.di;

import java.util.HashMap;
import java.util.Map;

public class BeanStorage {
    private Map<String, Object> beanStorage = new HashMap<>();

    public <T> T add(Class<T> clazz, T instance) {
        beanStorage.put(clazz.getName(), instance);
        return instance;
    }

    public <T> T get(Class<T> clazz) {
        Object bean = beanStorage.get(clazz.getName());
        if (bean == null) {
            return null;
        }

        return (T) bean;
    }
}
