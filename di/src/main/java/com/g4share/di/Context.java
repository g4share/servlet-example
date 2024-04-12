// Copyright (c) 2024 g4share

package com.g4share.di;

import com.g4share.di.exception.ClassInstantiationException;
import com.g4share.di.exception.ClassNotLoadedException;
import com.g4share.di.reflection.ReflectionHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;

public class Context {
    private final Class<? extends Annotation> injectAnnotation;

    private final ReflectionHelper reflectionHelper;

    private final List<AnnotatedType> loadedType;
    private final BeanStorage beanStorage = new BeanStorage();

    public Context(final List<String> basePackages,
                   final List<Class<? extends Annotation>> typeAnnotations,
                   final Class<? extends Annotation> injectAnnotation,
                   final ReflectionHelper reflectionHelper) {

        this.injectAnnotation = injectAnnotation;
        this.reflectionHelper = reflectionHelper;

        loadedType = reflectionHelper.load(basePackages, typeAnnotations);
    }

    public List<AnnotatedType> filter(Class<? extends Annotation> annotation) {
        return loadedType.stream()
                .filter(t -> t.annotations().contains(annotation))
                .toList();
    }

    public <T> T load(Class<T> clazz) throws ClassNotLoadedException {
        AnnotatedType annotatedType = locateClass(clazz);

        return (T)loadInternal(annotatedType.clazz());
    }

    private AnnotatedType locateClass(Class<?> clazz) {
        return loadedType.stream()
                .filter(at -> at.clazz().equals(clazz))
                .findAny()
                .orElseThrow(() -> new ClassNotLoadedException(clazz.getName()));
    }

    //TODO: Make Tread Safe
    private <T> T loadInternal(Class<T> loadedClass) {
        T loadedObject = beanStorage.get(loadedClass);
        if (loadedObject != null) {
            return loadedObject;
        }

        Constructor<T> constructor = reflectionHelper.constructor(loadedClass, injectAnnotation);

        Class<?>[] argTypes = reflectionHelper.initArgs(constructor);
        Object[] initArgs = new Object[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            initArgs[i] = load(argTypes[i]);
        }

        T constructedObject = construct(constructor, initArgs);

        return beanStorage.add(loadedClass, constructedObject);
    }

    private <T> T construct(Constructor<T> constructor, Object[] initArgs) {
        try {
            return constructor.newInstance(initArgs);
        } catch (Exception e) {
            throw new ClassInstantiationException(e);
        }
    }
}
