package com.licenta.ogm.Configuration;

import com.licenta.ogm.Repository.AnnouncementRepository;
import com.licenta.ogm.Repository.OrganisationRepository;
import com.licenta.ogm.Repository.SubscriptionRepository;
import com.licenta.ogm.Service.AnnouncementsService;
import com.licenta.ogm.Service.OrganisationAdminService;
import com.licenta.ogm.Service.OrganisationsService;
import com.licenta.ogm.Service.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RepositoryConfiguration.class)
public class ServiceConfiguration {
    @Bean
    @Autowired
    public OrganisationAdminService organisationAdminService(final OrganisationRepository organisationRepository) {
        return new OrganisationAdminService(organisationRepository);
    }

    @Bean
    @Autowired
    public OrganisationsService organisationsService(final OrganisationRepository organisationRepository,
                                                     final SubscriptionRepository subscriptionRepository) {
        return new OrganisationsService(organisationRepository, subscriptionRepository);
    }

    @Bean
    @Autowired
    public AnnouncementsService announcementsService(final AnnouncementRepository announcementRepository,
                                                     final OrganisationRepository organisationRepository,
                                                     final SubscriptionRepository subscriptionRepository) {
        return new AnnouncementsService(announcementRepository, organisationRepository, subscriptionRepository);
    }

    @Bean
    @Autowired
    public UserSubscriptionService userSubscriptionService(final OrganisationRepository organisationRepository,
                                                           final SubscriptionRepository subscriptionRepository) {
        return new UserSubscriptionService(organisationRepository, subscriptionRepository);
    }
}
