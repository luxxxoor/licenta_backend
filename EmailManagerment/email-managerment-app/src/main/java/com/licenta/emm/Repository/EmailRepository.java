package com.licenta.emm.Repository;

import com.licenta.emm.Domain.ORM.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    Optional<Email> findByEmail(final String email);
}
