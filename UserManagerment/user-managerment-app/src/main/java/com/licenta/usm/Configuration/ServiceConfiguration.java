package com.licenta.usm.Configuration;

import com.licenta.usm.Service.ManagementService;
import com.licenta.usm.Service.AuthenticationService;
import com.licenta.usm.Service.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RepositoryConfiguration.class)
public class ServiceConfiguration {
    @Bean
    public ManagementService managermentService() {
        return new ManagementService();
    }

    @Bean
    public AuthenticationService authenticationService() {
        return new AuthenticationService();
    }

    @Bean
    public RegistrationService registrationService() {
        return new RegistrationService();
    }
}
