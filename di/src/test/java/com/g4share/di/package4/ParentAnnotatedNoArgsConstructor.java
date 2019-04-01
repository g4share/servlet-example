// Copyright (c) 2024 g4share

package com.g4share.di.package4;

import com.g4share.di.package3.ConstructorAnnotation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(onConstructor = @__(@ConstructorAnnotation))
@AllArgsConstructor
@Ann
public class ParentAnnotatedNoArgsConstructor {
    private Child child;
}
