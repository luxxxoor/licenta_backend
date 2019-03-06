package com.licenta.usm.Service;


import com.licenta.emm.Exceptions.EmailAlreadyInUse;
import com.licenta.usm.Domain.ORM.User;
import com.licenta.usm.Entities.LoginUser;
import com.licenta.usm.Entities.RegisterUser;
import com.licenta.usm.Entities.UserDTO;
import com.licenta.usm.Exceptions.ExistingUserException;
import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.Feign.EmailProxy;
import com.licenta.usm.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

@Slf4j
public class ManagermentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailProxy emailProxy;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(final UserDTO userDTO) {
        userRepository.save(new User(userDTO.getNickName(), userDTO.getPassword(), userDTO.getEmail()));
    }

    public void deleteUser(final int id) throws UserNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return;
        }
        throw new UserNotFoundException();
    }

    public User findUser(final int id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException();
    }

    public User findUser(final UserDTO userDTO) throws UserNotFoundException {
        //final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        Optional<User> optionalUser = userRepository.findByNickNameAndPassword(userDTO.getNickName(),
                userDTO.getPassword());
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException();
    }

    public int login(final LoginUser loginUser) throws UserNotFoundException, NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] encryptedPassword = messageDigest.digest(loginUser.getPassword().getBytes(UTF_8));
        final Optional<User> existingUser = userRepository.findByNickNameAndPassword(loginUser.getNickName(), encryptedPassword);
        if (existingUser.isPresent()) {
            log.info("User \"" + existingUser.get().getNickName() + "\" logged in");
            return existingUser.get().getId();
        }
        throw new UserNotFoundException();
    }

    public void register(final RegisterUser registerUser) throws ExistingUserException, NoSuchAlgorithmException, EmailAlreadyInUse {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] encryptedPassword = messageDigest.digest(registerUser.getPassword().getBytes(UTF_8));

        final Optional<User> existingUser = userRepository.findByNickName(registerUser.getNickName());
        if (existingUser.isPresent()) {
            throw new ExistingUserException();
        }

        final String email = registerUser.getEmail();
        //emailProxy.addEmailToUser(new EmailDTO(email));

        final User user = new User(registerUser.getNickName(), encryptedPassword, email);
        userRepository.save(user);
    }

    public boolean isNameAvailable(final String nickName) {
        final Optional<User> existingUser = userRepository.findByNickName(nickName);

        return !existingUser.isPresent();
    }

    public List<User> findUsersWithPartialName(final String partialName) {
        final Integer pageNumber = 0;
        final Integer itemsPerPage = 10;

        final Page<User> page =
                userRepository.findAllByNickNameContainingIgnoreCase(partialName, PageRequest.of(pageNumber, itemsPerPage));

        log.info("Find users with partial name for: \"" + partialName + "\"");
        log.info("Found " + page.stream().count() + " matches");
        return page.stream().collect(toList());
    }
}
