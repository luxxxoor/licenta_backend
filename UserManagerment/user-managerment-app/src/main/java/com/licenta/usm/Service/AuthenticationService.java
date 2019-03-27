package com.licenta.usm.Service;


import com.licenta.usm.Entities.AuthUser;
import com.licenta.usm.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public List<AuthUser> findAllUsers() {
        List<AuthUser> users = userRepository.findAll()
                                                .stream()
                                                .map( u -> new AuthUser(u.getNickName(), u.getPassword()))
                                                .collect(Collectors.toList());
        log.info("findAllUsers (" + users.size() + " users)");
        return users;
    }
}
