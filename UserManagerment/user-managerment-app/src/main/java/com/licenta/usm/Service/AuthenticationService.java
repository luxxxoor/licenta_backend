package com.licenta.usm.Service;


import com.licenta.usm.Entities.AuthUser;
import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.ORM.User;
import com.licenta.usm.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
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

    public AuthUser findUserByNickName(final String nickName) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByNickName(nickName);
        if (optionalUser.isPresent()) {
            var authUser = new AuthUser(optionalUser.get().getNickName(), optionalUser.get().getPassword());
            return authUser;
        } else {
            throw new UserNotFoundException();
        }
    }
}
