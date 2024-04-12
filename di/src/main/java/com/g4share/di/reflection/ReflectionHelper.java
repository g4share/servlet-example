// Copyright (c) 2024 g4share

package com.g4share.di.reflection;

import com.g4share.di.AnnotatedType;
import com.g4share.di.exception.MultipleConstructorsFoundException;
import com.g4share.di.exception.NoConstructorsFoundException;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ReflectionHelper {

    private final ClassLoader classLoader;

    public ReflectionHelper(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public List<AnnotatedType> load(List<String> basePackages, List<Class<? extends Annotation>> typeAnnotations) {
        List<URL> urls = (List<URL>) collect(ArrayList::new, basePackages,
                p -> ClasspathHelper.forPackage(p, classLoader));

        ConfigurationBuilder confBuilder = new ConfigurationBuilder().setUrls(urls);
        Reflections reflections = new Reflections(confBuilder);

        Set<Class<?>> classes = (Set<Class<?>>) collect(HashSet::new,
                typeAnnotations,
                reflections::getTypesAnnotatedWith);

        return classes.stream()
                .map(c -> annotatedType(c, typeAnnotations))
                .toList();
    }

    private AnnotatedType annotatedType(Class<?> clazz,
                                        List<Class<? extends Annotation>> typeAnnotations) {
        List<Class<? extends Annotation>> annotations = new ArrayList<>();
        typeAnnotations.forEach(a -> {
            Annotation annotation = clazz.getAnnotation(a);
            if (annotation != null) {
                annotations.add(a);
            }
        });

        return new AnnotatedType(clazz, annotations );
    }

    public Set<Class<?>> transform(List<AnnotatedType> types) {
        return types.stream()
                .map(AnnotatedType::clazz)
                .collect(Collectors.toSet());
    }

    private <R, I> Collection<R> collect(Supplier<Collection<R>> resultCreator,
                                         List<I> input,
                                         Function<I, Collection<? extends R>> transformer) {
        Collection<R> collection = resultCreator.get();
        input.forEach(p -> collection.addAll(transformer.apply(p)));
        return collection;
    }

    public <T> Constructor<T> constructor(Class<T> loadedClass, Class<? extends Annotation> injectAnnotation) {
        Constructor<T>[] constructors = (Constructor<T>[]) loadedClass.getConstructors();

        if (constructors.length == 1) {
            return constructors[0];
        }

        if (constructors.length == 0) {
            throw new NoConstructorsFoundException();
        }

        Constructor<T> annotatedConstructor = null;
        for (Constructor<T> constructor : constructors) {
            if (constructor.isAnnotationPresent(injectAnnotation)) {
                if (annotatedConstructor != null) {
                    throw new MultipleConstructorsFoundException();
                }
                annotatedConstructor = constructor;
            }
        }

        if (annotatedConstructor == null) {
            throw new MultipleConstructorsFoundException();
        }
        return annotatedConstructor;
    }

    public <T> Class<?>[] initArgs(Constructor<T> constructor) {
        return constructor.getParameterTypes();
    }
}
