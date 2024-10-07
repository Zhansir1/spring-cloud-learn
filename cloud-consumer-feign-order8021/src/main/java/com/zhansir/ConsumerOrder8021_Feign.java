package com.zhansir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(value = {"com.zhansir.common.apis"})
public class ConsumerOrder8021_Feign
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConsumerOrder8021_Feign.class, args);
    }
}
