package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main2 {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(Main2.class, args);

        Car2 myCar = context.getBean(Car2.class);

        System.out.println(myCar);
    }
}
