package com.licenta.ath.Service;

import com.licenta.usm.Entities.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserVo findUserByPhoneNumber(final String phoneNumber);

    long createUser(final UserVo userVo, final String firebaseToken);

}
