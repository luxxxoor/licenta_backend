package com.licenta.ath.Service;

import com.google.firebase.auth.FirebaseToken;
import com.licenta.ath.Exceptions.HttpException;
import com.licenta.ath.Feign.UserManagementProxy;
import com.licenta.ath.Feign.UserRegistrationProxy;
import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Entities.UserVo;
import com.licenta.usm.Exceptions.AlreadyExistingUserException;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.licenta.ath.Utils.FirebaseUtils.validateFirebaseToken;
import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service("MyUserDetailService")
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND_MESSAGE = "No user found.";

    private UserRegistrationProxy userRegistrationProxy;
    private UserManagementProxy userManagementProxy;

    @Autowired
    public UserServiceImpl(UserRegistrationProxy userRegistrationProxy,
                           UserManagementProxy userManagementProxy) {
        this.userRegistrationProxy = userRegistrationProxy;
        this.userManagementProxy = userManagementProxy;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<UserVo> userVo = Optional.empty();
        try {
            userVo = Optional.ofNullable(userManagementProxy.findUsersByPhone(phoneNumber).getBody());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (userVo.isEmpty()) {
            throw new UsernameNotFoundException(phoneNumber);
        }

        return new org.springframework.security.core.userdetails.User(phoneNumber, "", emptyList());
    }

    @Override
    @Transactional
    public UserVo findUserByPhoneNumber(final String phoneNumber) {
        Optional<UserVo> userVo = Optional.empty();
        try {
            userVo = Optional.ofNullable(userManagementProxy.findUsersByPhone(phoneNumber).getBody());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (userVo.isEmpty()) {
            throw new HttpException(USER_NOT_FOUND_MESSAGE, NOT_FOUND);
        }

        return userVo.get();
    }

    @Override
    @Transactional
    public long createUser(final UserVo userVo, final String firebaseToken) {
        final FirebaseToken decodedToken;
        try {
            decodedToken = validateFirebaseToken(firebaseToken);
        } catch (BadCredentialsException e) {
            throw new HttpException(e.getMessage(), UNAUTHORIZED);
        }

        final String phoneNumber = valueOf(decodedToken.getClaims().get("phone_number"));
        UserVo newUser = new UserVo(userVo.getId(), phoneNumber, userVo.getName());
        try {
            userRegistrationProxy.register(newUser, firebaseToken);
        } catch (AlreadyExistingUserException | EmailAlreadyInUse e) {
            e.printStackTrace();
        }
        return newUser.getId();
    }
}