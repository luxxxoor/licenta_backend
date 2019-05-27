package com.licenta.usm.Authentification;

import com.licenta.usm.Entities.AuthUser;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.licenta.usm.Authentification.Constants.UserAuthenticationConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

public interface IUserAuthentication {
    @RequestMapping(value = PATH + "/users", method = GET)
    ResponseEntity<List<AuthUser>> getAll();

    @RequestMapping(value = PATH + "/user", method = GET)
    ResponseEntity<AuthUser> getUser(@RequestParam("nickName") final String nickName) throws UserNotFoundException;
}
