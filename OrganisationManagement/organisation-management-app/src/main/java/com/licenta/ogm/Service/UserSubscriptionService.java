package com.licenta.ogm.Service;

import com.licenta.ogm.Exceptions.AlreadyExistingSubscriptionException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.Exceptions.SubscriptionNotFoundException;
import com.licenta.ogm.ORM.OrganisationORM;
import com.licenta.ogm.ORM.SubscriptionORM;
import com.licenta.ogm.Repository.OrganisationRepository;
import com.licenta.ogm.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserSubscriptionService {
    private final OrganisationRepository organisationRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public UserSubscriptionService(final OrganisationRepository organisationRepository,
                                   final SubscriptionRepository subscriptionRepository) {
        this.organisationRepository = organisationRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void subscribe(final Integer userId, final Integer organisationId)
            throws AlreadyExistingSubscriptionException, OrganisationNotFoundException {
        final Optional<SubscriptionORM> optionalSubscriptionORM =
                subscriptionRepository.findByUserIdAndOrganisation_Id(userId,
                        organisationId);

        if (optionalSubscriptionORM.isPresent()) {
            throw new AlreadyExistingSubscriptionException();
        }

        final Optional<OrganisationORM> optionalOrganisationORM = organisationRepository.findById(organisationId);

        if (optionalOrganisationORM.isEmpty()) {
            throw new OrganisationNotFoundException();
        }

        final var organisationORM = optionalOrganisationORM.get();
        final SubscriptionORM subscriptionORM = new SubscriptionORM(userId, organisationORM);

        subscriptionRepository.save(subscriptionORM);
    }

    public void unsubscribe(final Integer userId, final Integer organisationId)
            throws SubscriptionNotFoundException {
        final Optional<SubscriptionORM> optionalSubscriptionORM =
                subscriptionRepository.findByUserIdAndOrganisation_Id(userId,
                        organisationId);

        if (optionalSubscriptionORM.isEmpty()) {
            throw new SubscriptionNotFoundException();
        }

        final var subscriptionORM = optionalSubscriptionORM.get();

        subscriptionRepository.delete(subscriptionORM);
    }
}
