package com.licenta.usm.Configuration;

import com.licenta.usm.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"Repository"})
public class RepositoryConfiguration {
    @Autowired
    public UserRepository userRepository;
}
