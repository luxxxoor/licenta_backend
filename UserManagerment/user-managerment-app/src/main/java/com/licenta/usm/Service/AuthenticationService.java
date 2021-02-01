package com.licenta.usm.Service;


import com.licenta.usm.Entities.AuthUser;
import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.ORM.User;
import com.licenta.usm.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
                .map(u -> new AuthUser(u.getId(), u.getName(), ""))
                .collect(Collectors.toList());
        log.info("findAllUsers (" + users.size() + " users)");
        return users;
    }

    public AuthUser findUserByNickName(final String nickName) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(nickName);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        var user = optionalUser.get();
        var authUser = new AuthUser(user.getId(), user.getName(), "");
        return authUser;
    }
}
