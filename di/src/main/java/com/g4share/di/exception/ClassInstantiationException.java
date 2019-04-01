//Copyright (c) 2023 g4share

package com.g4share.di.exception;

import com.g4share.common.exception.NestedRuntimeException;

public class ClassInstantiationException extends NestedRuntimeException {

    public ClassInstantiationException(final Throwable cause) {
        super(null, cause);
    }
}