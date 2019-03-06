package com.licenta.usm.Rest;

import com.licenta.usm.Authentification.IUserData;
import com.licenta.usm.Entities.LoginUser;
import com.licenta.usm.Service.ManagermentService;
import com.licenta.usm.Service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserData implements IUserData {
    private final UserDataService userDataService;

    @Autowired
    public UserData(UserDataService userDataService) {
        this.userDataService = userDataService;
    }
    @Override
    public ResponseEntity<List<LoginUser>> findAllUsers() {
        return null;
    }
}
