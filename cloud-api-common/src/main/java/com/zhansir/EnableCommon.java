package com.zhansir;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CommonAutoConfiguration.class)
public @interface EnableCommon {
}
