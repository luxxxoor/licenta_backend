package com.licenta.usm.Rest;

import com.licenta.usm.Domain.ORM.User;
import com.licenta.usm.Entities.UserDTO;
import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.Managerment.IAdminUserManagerment;
import com.licenta.usm.Service.ManagermentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class AdminUserManagerment implements IAdminUserManagerment {
    private final ManagermentService managermentService;

    @Autowired
    public AdminUserManagerment(ManagermentService managermentService) {
        this.managermentService = managermentService;
    }

    @Override
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "userid") final int id) throws UserNotFoundException {
        User user = managermentService.findUser(id);
        return new ResponseEntity<>(user.toDTO(), OK);
    }

    @Override
    public ResponseEntity<UserDTO> getUserByUsernameAndPassword(@RequestBody final UserDTO userDTO)
            throws UserNotFoundException, NoSuchAlgorithmException {
        User user = managermentService.findUser(userDTO);
        return new ResponseEntity<>(user.toDTO(), OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> users = managermentService.findAllUsers().stream().map(User::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(users, OK);
    }

    @Override
    public ResponseEntity<Void> addUser(@RequestBody final UserDTO userDTO) throws NoSuchAlgorithmException {
        managermentService.addUser(userDTO);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "userid") final int id) throws UserNotFoundException {
        managermentService.deleteUser(id);
        return new ResponseEntity<>(OK);
    }
}
