package com.zhansir.common.excep;

import com.zhansir.common.resp.ResultData;
import com.zhansir.common.resp.ReturnCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 相比与ControllerAdvice,RestControllerAdvice可以返回json数据体，两者都可以处理全局Controller的异常
// 这里统一把服务端的代码发生的错误都抛出500
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> runtimeExceptionHandler(RuntimeException e) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }

}
