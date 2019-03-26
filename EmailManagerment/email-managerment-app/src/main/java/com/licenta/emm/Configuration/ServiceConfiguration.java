package com.licenta.emm.Configuration;

import com.licenta.emm.Service.ManagermentService;
import com.licenta.emm.Service.SmtpService;
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
    public SmtpService smtpService() {return new SmtpService();}
}
