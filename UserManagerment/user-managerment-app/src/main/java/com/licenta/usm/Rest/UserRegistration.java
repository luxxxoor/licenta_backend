package com.licenta.usm.Rest;

import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Entities.RegisterUser;
import com.licenta.usm.Exceptions.AlreadyExistingUserException;
import com.licenta.usm.Exceptions.PasswordTooShortException;
import com.licenta.usm.Registration.IUserRegistration;
import com.licenta.usm.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserRegistration implements IUserRegistration {
    private final RegistrationService registrationService;

    @Autowired
    public UserRegistration(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public ResponseEntity<Void> register(@RequestBody RegisterUser registerUser) throws AlreadyExistingUserException, PasswordTooShortException, EmailAlreadyInUse {
        registrationService.register(registerUser);
        return new ResponseEntity<>(OK);
    }
}
