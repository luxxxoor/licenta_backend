package com.licenta.usm.Registration;

import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Entities.RegisterUser;
import com.licenta.usm.Exceptions.AlreadyExistingUserException;
import com.licenta.usm.Exceptions.PasswordTooShortException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.licenta.usm.Registration.Constants.UserRegistrationConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IUserRegistration {
    @RequestMapping(value = PATH + "/register", method = POST)
    ResponseEntity<Void> register(@RequestBody final RegisterUser registerUser)
            throws AlreadyExistingUserException, PasswordTooShortException, EmailAlreadyInUse;
}
