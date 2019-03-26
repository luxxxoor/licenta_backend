package com.licenta.emm.Configuration;

import com.licenta.emm.Repository.ConfirmationLinkRepository;
import com.licenta.emm.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"Repository"})
public class RepositoryConfiguration {
    @Autowired
    public EmailRepository emailRepository;
    @Autowired
    public ConfirmationLinkRepository confirmationLinkRepository;
}

