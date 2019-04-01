//Copyright (c) 2023 g4share

package com.g4share.rest.exception;

import com.g4share.common.exception.NestedRuntimeException;

public class MethodNotSupportedException extends NestedRuntimeException {

    public MethodNotSupportedException(final String method) {
        super("Method not supported: " + method);
    }
}