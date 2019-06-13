package com.licenta.usm.Rest;

import com.licenta.usm.Authentification.IUserAuthentication;
import com.licenta.usm.Entities.AuthUser;
import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserAuthentication implements IUserAuthentication {
    private final AuthenticationService authenticationService;

    @Autowired
    public UserAuthentication(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<List<AuthUser>> getAll() {
        final List<AuthUser> authUsers = authenticationService.findAllUsers();
        return new ResponseEntity<>(authUsers, OK);
    }

    @Override
    public ResponseEntity<AuthUser> getUser(@RequestParam("nickName") final String nickName) throws UserNotFoundException {
        final AuthUser authUser = authenticationService.findUserByNickName(nickName);
        return new ResponseEntity<>(authUser, OK);
    }
}
