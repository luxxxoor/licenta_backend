package com.licenta.usm.Rest;

import com.licenta.usm.Entities.UserPublicDetails;
import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.Managerment.IUserManagerment;
import com.licenta.usm.ORM.User;
import com.licenta.usm.Service.ManagermentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserManagerment implements IUserManagerment {
    private final ManagermentService managermentService;

    @Autowired
    public UserManagerment(ManagermentService managermentService) {
        this.managermentService = managermentService;
    }

    @Override
    public ResponseEntity<List<UserPublicDetails>> findUsersByName(@RequestParam final String nickName) {
        final List<User> users = managermentService.findUsersWithPartialName(nickName);
        final List<UserPublicDetails> userPublicDetailsList = users.stream().map(u -> new UserPublicDetails(u.getId(), u.getNickName())).collect(Collectors.toList());
        return new ResponseEntity<>(userPublicDetailsList, OK);
    }

    @Override
    public ResponseEntity<UserPublicDetails> findNameById(@RequestParam final Integer id) throws UserNotFoundException {
        final User user = managermentService.findUser(id);
        final var userPublicDetails = new UserPublicDetails(user.getId(), user.getNickName());
        return new ResponseEntity<>(userPublicDetails, OK);
    }

    @Override
    public ResponseEntity<Void> checkIfUserNameExists(@RequestParam final String nickName) {
        if (managermentService.isNameAvailable(nickName)) {
            return new ResponseEntity<>(OK);
        }

        return new ResponseEntity<>(FORBIDDEN);
    }
}
