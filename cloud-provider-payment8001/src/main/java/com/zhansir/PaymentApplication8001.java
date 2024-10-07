package com.zhansir;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaymentApplication8001
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(PaymentApplication8001.class, args);
    }
}
