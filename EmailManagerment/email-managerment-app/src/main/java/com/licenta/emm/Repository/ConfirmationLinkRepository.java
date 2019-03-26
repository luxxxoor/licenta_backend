package com.licenta.emm.Repository;

import com.licenta.emm.Domain.ORM.ConfirmationLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationLinkRepository extends JpaRepository<ConfirmationLink, Integer> {
    Optional<ConfirmationLink> findByLink(final String link);
}