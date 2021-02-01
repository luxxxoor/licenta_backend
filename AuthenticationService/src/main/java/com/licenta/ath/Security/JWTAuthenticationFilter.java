package com.licenta.ath.Security;

import com.google.firebase.auth.FirebaseToken;
import com.licenta.ath.Service.AuthTokensPairService;
import com.licenta.ath.Service.UserService;
import com.licenta.ath.Utils.TokenGenerator;
import com.licenta.usm.Entities.UserVo;
import com.licenta.ath.Model.RefreshTokenVo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.licenta.ath.Utils.FirebaseUtils.validateFirebaseToken;
import static com.licenta.ath.Utils.TokenGenerator.TokenType.AUTH;
import static com.licenta.ath.Utils.TokenGenerator.TokenType.REFRESH;
import static java.lang.String.valueOf;

import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenGenerator tokenGenerator;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final AuthTokensPairService authTokensPairService;

    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager,
            UserService userService,
            TokenGenerator tokenGenerator,
            AuthTokensPairService authTokensPairService) {

        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authTokensPairService = authTokensPairService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String firebaseToken = request.getHeader("Firebase-Token");
        FirebaseToken decodedToken = validateFirebaseToken(firebaseToken);
        String phoneNumber = valueOf(decodedToken.getClaims().get("phone_number"));

        if (phoneNumber == null) {
            throw new BadCredentialsException("Invalid authentication token.");
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        phoneNumber,
                        "",
                        new ArrayList<>())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {
        org.springframework.security.core.userdetails.User authenticatedUser =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        //	Note that the username is the phone number
        UserVo user = userService.findUserByPhoneNumber(authenticatedUser.getUsername());

        final String authToken = tokenGenerator.generateToken(AUTH,
                authenticatedUser.getUsername(),
                valueOf(user.getId()));
        final String refreshToken = tokenGenerator.generateToken(REFRESH,
                authenticatedUser.getUsername(),
                valueOf(user.getId()));

        authTokensPairService.saveToken(new RefreshTokenVo(authToken, refreshToken));

        res.setCharacterEncoding("UTF-8");
        try {
            res.getWriter().write("{\"" +
                    "Authorization" + "\":\"" + "Bearer " + authToken + "\",\"" +
                    "Refresh_Token" + "\":\"" + "Bearer " + refreshToken + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}