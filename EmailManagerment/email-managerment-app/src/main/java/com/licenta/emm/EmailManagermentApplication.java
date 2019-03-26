package com.licenta.emm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EmailManagermentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailManagermentApplication.class, args);
    }
}