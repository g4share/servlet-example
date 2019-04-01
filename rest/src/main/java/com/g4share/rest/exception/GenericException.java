//Copyright (c) 2023 g4share

package com.g4share.rest.exception;

import com.g4share.common.exception.NestedRuntimeException;

public class GenericException extends NestedRuntimeException {

    public GenericException(final Throwable cause) {
        super(null, cause);
    }
}