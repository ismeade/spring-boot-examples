package com.ade.exp.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCamelApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootCamelApplication.class, args);

        synchronized (SpringBootCamelApplication.class) {
            SpringBootCamelApplication.class.wait();
        }
    }
}
