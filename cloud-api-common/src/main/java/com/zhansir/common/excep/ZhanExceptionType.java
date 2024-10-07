package com.zhansir.common.excep;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ZhanExceptionType {
    IdlowerThanZero("id不能为负数");

    private final String message;

}
