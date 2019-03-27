package com.licenta.usm.Repository;

import com.licenta.usm.ORM.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNickNameAndPassword(final String nickName, final String password);
    Optional<User> findByNickName(final String nickName);
    Page<User> findAllByNickNameContainingIgnoreCase(final String partialname, final Pageable pageable);
}
