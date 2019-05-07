package com.licenta.usm.Service;


import com.licenta.usm.Exceptions.UserNotFoundException;
import com.licenta.usm.ORM.User;
import com.licenta.usm.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
public class ManagermentService {
    @Autowired
    private UserRepository userRepository;

    public User findUser(final int id) throws UserNotFoundException {
        final Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException();
    }

    public boolean isNameAvailable(final String nickName) {
        final Optional<User> existingUser = userRepository.findByNickName(nickName);

        return existingUser.isEmpty();
    }

    public List<User> findUsersWithPartialName(final String partialName) {
        final var pageNumber = 0;
        final var itemsPerPage = 10;

        final Page<User> page =
                userRepository.findAllByNickNameContainingIgnoreCase(partialName, PageRequest.of(pageNumber, itemsPerPage));

        log.info("Find users with partial name for: \"" + partialName + "\"");
        log.info("Found " + page.stream().count() + " matches");
        return page.stream().collect(toList());
    }
}
