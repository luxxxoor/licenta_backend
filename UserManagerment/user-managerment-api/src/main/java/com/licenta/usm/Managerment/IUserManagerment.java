package com.licenta.usm.Managerment;

import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Entities.LoginUser;
import com.licenta.usm.Entities.RegisterUser;
import com.licenta.usm.Entities.UID;
import com.licenta.usm.Entities.UserName;
import com.licenta.usm.Entities.UserPublicDetails;
import com.licenta.usm.Exceptions.ExistingUserException;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.licenta.usm.Managerment.Constants.UserManagermentConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IUserManagerment {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/find")
    ResponseEntity<List<UserPublicDetails>> findUsersByName(@RequestBody final UserName userName);

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/get-name")
    ResponseEntity<UserPublicDetails> findNameById(@RequestBody final Integer id) throws UserNotFoundException;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/login", method = POST)
    ResponseEntity<UID> login(@RequestBody final LoginUser loginUser) throws NoSuchAlgorithmException, UserNotFoundException;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/register", method = POST)
    ResponseEntity<Void> register(@RequestBody final RegisterUser registerUser) throws NoSuchAlgorithmException, ExistingUserException, EmailAlreadyInUse;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/name-availability", method = POST)
    ResponseEntity<Void> checkIfUserNameExists(@RequestBody final UserName userName);
}
