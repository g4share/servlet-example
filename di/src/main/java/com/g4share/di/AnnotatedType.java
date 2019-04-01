// Copyright (c) 2024 g4share

package com.g4share.di;

import java.lang.annotation.Annotation;
import java.util.List;

public record AnnotatedType (Class<?> clazz, List<Class<? extends Annotation>> annotations) {
}
