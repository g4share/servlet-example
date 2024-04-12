// Copyright (c) 2024 g4share

package com.g4share.rest.di;

import com.g4share.di.AnnotatedType;
import com.g4share.di.Context;
import com.g4share.di.reflection.ReflectionHelper;
import com.g4share.rest.di.annotations.Component;
import com.g4share.rest.di.annotations.Inject;
import com.g4share.rest.di.annotations.RestController;
import com.g4share.rest.di.annotations.Service;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestContext {

    @Getter
    private final ReflectionHelper reflectionHelper = new ReflectionHelper(
            Thread.currentThread().getContextClassLoader());
    private Context context;

    private static class Holder {
        private static final RestContext INSTANCE = new RestContext();
    }

    public void init(final List<String> basePackages) {
       context = new Context(basePackages,
               List.of(RestController.class, Service.class, Component.class),
               Inject.class,
               reflectionHelper);
    }

    public static RestContext getInstance() {
        return Holder.INSTANCE;
    }

    public List<?> loadControllers() {
        List<AnnotatedType> restControllers = context.filter(RestController.class);

        List<?> controllers = restControllers.stream()
                .map(rc -> context.load(rc.clazz()))
                .toList();

        return controllers;
    }

    public Map<String, Obj> getEndpoints(List<?> controllers) {

    }
}
