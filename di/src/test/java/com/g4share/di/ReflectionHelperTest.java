// Copyright (c) 2024 g4share

package com.g4share.di;

import com.g4share.di.annotations.Annotation1;
import com.g4share.di.annotations.Annotation2;
import com.g4share.di.annotations.Annotation3;
import com.g4share.di.package1.AnnotatedClass1;
import com.g4share.di.package2.AnnotatedClass2;
import com.g4share.di.package2.AnnotatedClass3;
import com.g4share.di.package2.AnnotatedClass4;
import com.g4share.di.package2.AnnotatedClass5;
import com.g4share.di.reflection.ReflectionHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionHelperTest {

    private final ReflectionHelper reflectionHelper = new ReflectionHelper(Thread
            .currentThread().getContextClassLoader());

    @ParameterizedTest
    @MethodSource("paramsMethod")
    void load(String[] basePackages,
              Class<? extends Annotation>[] annotations,
              Class<?>[] expectedClasses) {
        List<AnnotatedType> types = reflectionHelper.load(basePackages, annotations);
        assertThat(reflectionHelper.transform(types)).containsAll(Set.of(expectedClasses));
    }

    private static Stream<Arguments> paramsMethod() {
        return Stream.of(
                Arguments.of(new String[] { "com.g4share.di" },
                        new Class[] { Annotation1.class, Annotation2.class },
                        new Class[] { AnnotatedClass1.class,
                                AnnotatedClass2.class,
                                AnnotatedClass3.class,
                                AnnotatedClass4.class,
                                AnnotatedClass5.class} ),

                Arguments.of(new String[] { "com.g4share.di.package1", "com.g4share.di.package2" },
                        new Class[] { Annotation1.class, Annotation2.class },
                        new Class[] { AnnotatedClass1.class,
                                AnnotatedClass2.class,
                                AnnotatedClass3.class,
                                AnnotatedClass4.class,
                                AnnotatedClass5.class} ),

                Arguments.of(new String[] { "com.g4share.di" },
                        new Class[] { Annotation1.class },
                        new Class[] { AnnotatedClass1.class,
                                AnnotatedClass2.class,
                                AnnotatedClass3.class,
                                AnnotatedClass4.class, } ),

                Arguments.of(new String[] { "com.g4share.di" },
                        new Class[] { Annotation2.class },
                        new Class[] { AnnotatedClass2.class,
                                AnnotatedClass5.class} ),

                Arguments.of(new String[] { "com.g4share.di" },
                        new Class[] { Annotation3.class },
                        new Class[] { } )
        );
    }
}