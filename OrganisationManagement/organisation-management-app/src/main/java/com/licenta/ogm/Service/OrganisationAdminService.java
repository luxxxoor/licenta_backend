package com.licenta.ogm.Service;

import com.licenta.ogm.Entities.Organisation;
import com.licenta.ogm.Exceptions.AlreadyExistingOrganisationException;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.ORM.OrganisationORM;
import com.licenta.ogm.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class OrganisationAdminService {
    private final OrganisationRepository organisationRepository;

    @Autowired
    public OrganisationAdminService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public Organisation findOrganisationById(final Integer id) throws OrganisationNotFoundException {
        Optional<OrganisationORM> optionalOrganisationORM = organisationRepository.findById(id);
        if (optionalOrganisationORM.isPresent()) {
            var organisation = new Organisation(optionalOrganisationORM.get().getId(),
                    optionalOrganisationORM.get().getName());
            return organisation;
        } else {
            throw new OrganisationNotFoundException();
        }
    }

    public void addOrganisation(final String name, final String password)
            throws AlreadyExistingOrganisationException, InvalidPasswordException {
        if (password.length() < 5) {
            throw new InvalidPasswordException("Provided password has only " + password.length() + " characters");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        final Optional<OrganisationORM> optionalOrganisationORM = organisationRepository.findByName(name);
        if (optionalOrganisationORM.isPresent()) {
            throw new AlreadyExistingOrganisationException();
        }

        final var user = new OrganisationORM(name, encryptedPassword);
        organisationRepository.save(user);
    }
}
