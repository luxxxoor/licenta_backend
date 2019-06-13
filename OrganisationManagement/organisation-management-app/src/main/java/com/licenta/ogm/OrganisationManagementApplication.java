package com.licenta.ogm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrganisationManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganisationManagementApplication.class, args);
    }

}
