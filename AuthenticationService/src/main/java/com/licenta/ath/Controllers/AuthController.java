package com.licenta.ath.Controllers;

import com.licenta.ath.Model.RefreshTokenVo;
import com.licenta.ath.Service.AuthTokensPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/tokens")
public class AuthController {

    private final AuthTokensPairService authTokensPairService;

    @Autowired
    public AuthController(AuthTokensPairService authTokensPairService) {
        this.authTokensPairService = authTokensPairService;
    }

    @PostMapping
    public ResponseEntity<RefreshTokenVo> refreshToken(@Valid @RequestBody RefreshTokenVo refreshTokenVo) {
        RefreshTokenVo newTokens = authTokensPairService.refreshToken(refreshTokenVo);
        return new ResponseEntity<>(newTokens, CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authToken) {
        authTokensPairService.logout(authToken);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
