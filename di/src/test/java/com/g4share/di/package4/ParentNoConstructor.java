// Copyright (c) 2024 g4share

package com.g4share.di.package4;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Ann
public class ParentNoConstructor {
    private Child child;
}
