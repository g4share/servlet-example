// Copyright (c) 2024 g4share

package com.g4share.di.exception;

import com.g4share.common.exception.NestedRuntimeException;

public class NoConstructorsFoundException extends NestedRuntimeException {

    public NoConstructorsFoundException() {
        super(null);
    }
}