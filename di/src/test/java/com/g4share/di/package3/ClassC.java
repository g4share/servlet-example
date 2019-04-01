// Copyright (c) 2024 g4share

package com.g4share.di.package3;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Ann2
public class ClassC {
    private final UUID value = UUID.randomUUID();
}
