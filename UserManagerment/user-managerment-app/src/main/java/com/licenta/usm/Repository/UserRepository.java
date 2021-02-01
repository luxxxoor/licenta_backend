package com.licenta.usm.Repository;

import com.licenta.usm.ORM.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(final String nickName);
    Optional<User> findByPhoneNumber(final String phoneNumber);
    Page<User> findAllByNameContainingIgnoreCase(final String partialname, final Pageable pageable);
}
