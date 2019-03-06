package com.licenta.usm.Service;


import com.licenta.usm.Entities.LoginUser;
import com.licenta.usm.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserDataService {

    @Autowired
    private UserRepository userRepository;

    public List<LoginUser> findAllUsers() {
        List<LoginUser> users = userRepository.findAll()
                                                .stream()
                                                .map( u -> new LoginUser(u.getNickName(), u.getPassword()))
                                                .collect(Collectors.toList());
        return users;
    }
}
