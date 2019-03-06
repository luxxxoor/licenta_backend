package com.licenta.usm.Configuration;

import com.licenta.usm.Rest.UserData;
import com.licenta.usm.Service.ManagermentService;
import com.licenta.usm.Service.UserDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RepositoryConfiguration.class)
public class ServiceConfiguration {
    @Bean
    public ManagermentService managermentService() {
        return new ManagermentService();
    }

    @Bean
    public UserDataService userDataService() {
        return new UserDataService();
    }
}
