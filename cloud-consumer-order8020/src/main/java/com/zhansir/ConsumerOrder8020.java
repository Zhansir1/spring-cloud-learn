package com.zhansir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ConsumerOrder8020
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerOrder8020.class, args);

//        Environment env = context.getEnvironment();
//        System.out.println(env);
    }
}
