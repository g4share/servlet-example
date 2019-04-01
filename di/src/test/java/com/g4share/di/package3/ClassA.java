// Copyright (c) 2024 g4share

package com.g4share.di.package3;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Ann1
public class ClassA {
    private final ClassB classB;
    private final ClassC classC;
    private final ClassD classD;
}
