package com.lh.ec.common.exception;

import com.lh.ec.common.enums.ExceptionEnum;

public class EcException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

    public EcException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
