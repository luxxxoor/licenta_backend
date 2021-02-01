package com.licenta.ath.Utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

import static com.auth0.jwt.JWT.create;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.licenta.ath.Utils.TokenGenerator.TokenType.AUTH;

@Getter
@Component
public class TokenGenerator {

    private String authKeySecret;

    private String refreshKeySecret;

    public String generateToken(final TokenType tokenType,
                                final String subject,
                                final String userId) {
        return create()
                .withSubject(subject)
                .withClaim("userId", userId)
                .withExpiresAt((tokenType == AUTH) ?
                        new Date(System.currentTimeMillis() + 1200000) : // 20 minutes in ms
                        new Date(System.currentTimeMillis() + 1209600000)) // 14 days in ms
                .sign(HMAC512((tokenType == AUTH) ?
                        authKeySecret.getBytes() :
                        refreshKeySecret.getBytes()));
    }

    @PostConstruct
    public void init() {
        authKeySecret = "SECRET_KEY";
        refreshKeySecret = "REFRESH_TOKEN_SECRET_KEY";
    }

    public enum TokenType {
        AUTH,
        REFRESH
    }
}
