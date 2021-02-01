package com.licenta.ogm.Service;

import com.licenta.ogm.Entities.Organisation;
import com.licenta.ogm.ORM.OrganisationORM;
import com.licenta.ogm.ORM.SubscriptionORM;
import com.licenta.ogm.Repository.OrganisationRepository;
import com.licenta.ogm.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrganisationsService {
    private final OrganisationRepository organisationRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public OrganisationsService(final OrganisationRepository organisationRepository,
                                final SubscriptionRepository subscriptionRepository) {
        this.organisationRepository = organisationRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Organisation> getAllSortedByPopularity(final Integer userId) {
        List<Organisation> organisations = organisationRepository.findAll()
                .stream()
                .map(o -> new Organisation(o.getId(), o.getName(),
                        subscriptionRepository.findByUserIdAndOrganisation_Id(userId, o.getId()).isPresent()))
                .sorted(Comparator.comparing(o -> subscriptionRepository.countAllByOrganisation_Id(o.getId())))
                .collect(Collectors.toList());
        return organisations;
    }

    public List<Organisation> findOrganisationsForUserId(final Integer userId) {
        final List<SubscriptionORM> subscriptions = subscriptionRepository.findAllByUserId(userId);
        final List<OrganisationORM> organisationORMList =
                subscriptions.stream().map(SubscriptionORM::getOrganisation).collect(Collectors.toList());
        final List<Organisation> organisations = organisationORMList.stream()
                .map(o -> new Organisation(o.getId(), o.getName(), true)).collect(Collectors.toList());
        return organisations;
    }
}
