package com.licenta.usm.Management;

import com.licenta.usm.Entities.UserPublicDetails;
import com.licenta.usm.Entities.UserVo;
import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.Management.Constants.UserManagementConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IUserManagement {
    @PostMapping(value = UserManagementConstants.PATH + "/findByPhone")
    ResponseEntity<UserVo> findUsersByPhone(@RequestParam("phoneNumber") final String phoneNumber) throws UserNotFoundException;

    @RequestMapping(value = UserManagementConstants.PATH + "/find", method = POST)
    ResponseEntity<List<UserPublicDetails>> findUsersByName(@RequestParam("nickName") final String nickName);

    @RequestMapping(value = UserManagementConstants.PATH + "/get-name", method = POST)
    ResponseEntity<UserPublicDetails> findNameById(@RequestParam("id") final Integer id) throws UserNotFoundException;

    @RequestMapping(value = UserManagementConstants.PATH + "/name-availability", method = POST)
    ResponseEntity<Void> checkIfUserNameExists(@RequestParam("nickName") final String nickName);
}
