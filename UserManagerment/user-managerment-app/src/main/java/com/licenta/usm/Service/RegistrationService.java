package com.licenta.usm.Service;

import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Entities.RegisterUser;
import com.licenta.usm.Entities.UserVo;
import com.licenta.usm.Exceptions.AlreadyExistingUserException;
import com.licenta.usm.Exceptions.PasswordTooShortException;
import com.licenta.usm.Feign.EmailProxy;
import com.licenta.usm.ORM.User;
import com.licenta.usm.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Slf4j
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailProxy emailProxy;

    public void register(final UserVo userVo) throws AlreadyExistingUserException, EmailAlreadyInUse {
        final Optional<User> existingUser = userRepository.findByName(userVo.getName());
        if (existingUser.isPresent()) {
            log.info("Already existing user: " + "\"" + existingUser.get().getName() + "\"");
            throw new AlreadyExistingUserException();
        }

        //final String email = userVo.getEmail();
        //emailProxy.addEmailToUser(new EmailDTO(email));

        final var user = new User(userVo.getName(), userVo.getPhoneNumber(), "");
        userRepository.save(user);
        log.info("Registered user: " + "\"" + user.getName() + "\"");
    }
}
