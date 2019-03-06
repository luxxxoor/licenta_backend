package com.licenta.usm.Managerment;

import com.licenta.usm.Entities.UserDTO;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.licenta.usm.Managerment.Constants.AdminUserManagermentConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

public interface IAdminUserManagerment {
    @RequestMapping(value = PATH + "/{userid}", method = GET)
    ResponseEntity<UserDTO> getUser(@PathVariable(value = "userid") int id) throws UserNotFoundException;

    @RequestMapping(value = PATH, method = POST)
    ResponseEntity<UserDTO> getUserByUsernameAndPassword(@RequestBody UserDTO userDTO)
            throws UserNotFoundException, NoSuchAlgorithmException;

    @RequestMapping(value = PATH + "/all", method = GET)
    ResponseEntity<List<UserDTO>> findAllUsers();

    @RequestMapping(value = PATH, method = PUT)
    ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException;

    @RequestMapping(value = PATH + "/{userid}", method = DELETE)
    ResponseEntity<Void> deleteUser(@PathVariable(value = "userid") int id) throws UserNotFoundException;
}
