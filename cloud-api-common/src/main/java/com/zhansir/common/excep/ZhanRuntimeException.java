package com.zhansir.common.excep;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ZhanRuntimeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private ZhanExceptionType type;

    public ZhanRuntimeException(ZhanExceptionType type) {
        this.type = type;
    }

}
