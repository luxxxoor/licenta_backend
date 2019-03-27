package com.licenta.usm.Authentification;

import com.licenta.usm.Entities.AuthUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.licenta.usm.Authentification.Constants.UserAuthenticationConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

public interface IUserAuthentication {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = PATH + "/users", method = GET)
    ResponseEntity<List<AuthUser>> getAll();
}
