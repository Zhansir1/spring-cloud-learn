package com.zhansir.common.config;

import feign.Feign;
import feign.Retryer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Feign.class)
public class OpenFeignConfig {
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, 1, 3);
    }
}
