package com.licenta.usm.Authentification;

import com.licenta.usm.Entities.LoginUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.licenta.usm.Authentification.Constants.UserDataConstants.PATH;

public interface IUserData {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/users")
    ResponseEntity<List<LoginUser>> findAllUsers();
}
