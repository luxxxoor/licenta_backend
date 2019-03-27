package com.licenta.usm.Managerment;

import com.licenta.usm.Entities.UserName;
import com.licenta.usm.Entities.UserPublicDetails;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping(value = PATH + "/name-availability", method = POST)
    ResponseEntity<Void> checkIfUserNameExists(@RequestBody final UserName userName);
}
