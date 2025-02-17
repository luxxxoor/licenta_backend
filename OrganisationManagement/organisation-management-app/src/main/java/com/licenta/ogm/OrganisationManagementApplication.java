package com.licenta.ogm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class OrganisationManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganisationManagementApplication.class, args);
    }

}
