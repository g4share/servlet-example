// Copyright (c) 2024 g4share

package com.g4share.di;

import com.g4share.di.exception.MultipleConstructorsFoundException;
import com.g4share.di.exception.NoConstructorsFoundException;
import com.g4share.di.package3.Ann1;
import com.g4share.di.package3.Ann2;
import com.g4share.di.package3.ClassA;
import com.g4share.di.package3.ConstructorAnnotation;
import com.g4share.di.package4.Ann;
import com.g4share.di.package4.ParentAllArsConstructor;
import com.g4share.di.package4.ParentAnnotatedAllArgsConstructor;
import com.g4share.di.package4.ParentAnnotatedNoArgsConstructor;
import com.g4share.di.package4.ParentMultipleAnnotatedConstructor;
import com.g4share.di.package4.ParentMultipleConstructor;
import com.g4share.di.package4.ParentNoArgsConstructor;
import com.g4share.di.package4.ParentNoConstructor;
import com.g4share.di.reflection.ReflectionHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContextTest {
    private final ReflectionHelper reflectionHelper = new ReflectionHelper(Thread
            .currentThread().getContextClassLoader());

    @Test
    void load() {
        Context context = new Context(List.of("com.g4share.di"),
                List.of(Ann1.class, Ann2.class),
                ConstructorAnnotation.class,
                reflectionHelper);

        assertThat(context.load(ClassA.class)).isNotNull();
    }

    @Test
    void loadClass() {
        Context context = new Context(List.of("com.g4share.di"),
                List.of(Ann.class),
                ConstructorAnnotation.class,
                reflectionHelper);

        assertThat(context.load(ParentAllArsConstructor.class))
                .asString().startsWith("ParentAllArsConstructor(child=Child(");

        assertThat(context.load(ParentAnnotatedAllArgsConstructor.class))
                .asString().startsWith("ParentAnnotatedAllArgsConstructor(child=Child(");

        assertThat(context.load(ParentAnnotatedNoArgsConstructor.class))
                .asString().startsWith("ParentAnnotatedNoArgsConstructor(child=null");

        assertThat(context.load(ParentNoArgsConstructor.class))
                .asString().startsWith("ParentNoArgsConstructor(child=null");

    }


    @Test
    void loadClassWithExceptions() {
        Context context = new Context(List.of("com.g4share.di"),
                List.of(Ann.class),
                ConstructorAnnotation.class,
                reflectionHelper);

        assertThrows(NoConstructorsFoundException.class,
                ()-> context.load(ParentNoConstructor.class)
        );
        assertThrows(MultipleConstructorsFoundException.class,
                ()-> context.load(ParentMultipleConstructor.class)
        );

        assertThrows(MultipleConstructorsFoundException.class,
                ()-> context.load(ParentMultipleAnnotatedConstructor.class)
        );
    }
}