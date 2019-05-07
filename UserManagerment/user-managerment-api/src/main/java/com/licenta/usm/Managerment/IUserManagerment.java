package com.licenta.usm.Managerment;

import com.licenta.usm.Entities.UserPublicDetails;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.licenta.usm.Managerment.Constants.UserManagermentConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IUserManagerment {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/find", method = POST)
    ResponseEntity<List<UserPublicDetails>> findUsersByName(@RequestParam final String nickName);

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/get-name", method = POST)
    ResponseEntity<UserPublicDetails> findNameById(@RequestParam final Integer id) throws UserNotFoundException;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/name-availability", method = POST)
    ResponseEntity<Void> checkIfUserNameExists(@RequestParam final String nickName);
}
