package com.licenta.usm.Rest;

import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Authentification.IUserAuthentication;
import com.licenta.usm.Entities.AuthUser;
import com.licenta.usm.Entities.RegisterUser;
import com.licenta.usm.Exceptions.ExistingUserException;
import com.licenta.usm.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
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
}
