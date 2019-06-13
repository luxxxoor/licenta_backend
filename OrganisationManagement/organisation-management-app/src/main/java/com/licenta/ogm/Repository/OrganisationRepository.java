package com.licenta.ogm.Repository;

import com.licenta.ogm.ORM.OrganisationORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<OrganisationORM, Integer> {
    Optional<OrganisationORM> findByName(final String name);

    Optional<OrganisationORM> findByIdAndEncryptedPassword(final Integer id, final String encryptedPassword);
}
