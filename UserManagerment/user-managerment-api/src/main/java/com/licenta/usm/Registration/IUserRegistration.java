package com.licenta.usm.Registration;

import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Entities.RegisterUser;
import com.licenta.usm.Entities.UserVo;
import com.licenta.usm.Exceptions.AlreadyExistingUserException;
import com.licenta.usm.Exceptions.PasswordTooShortException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.licenta.usm.Registration.Constants.UserRegistrationConstants.PATH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IUserRegistration {
    @PostMapping(value = PATH + "/register")
    ResponseEntity<?> register(@Valid @RequestBody UserVo userVo, @RequestHeader(value = "Firebase-Token") String firebaseToken)
        throws AlreadyExistingUserException, EmailAlreadyInUse;
}
