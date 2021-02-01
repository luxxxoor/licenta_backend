package com.licenta.ogm.Configuration;

import com.licenta.ogm.Repository.AnnouncementRepository;
import com.licenta.ogm.Repository.CommentRepository;
import com.licenta.ogm.Repository.OrganisationRepository;
import com.licenta.ogm.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"Repository"})
public class RepositoryConfiguration {
    @Autowired
    public OrganisationRepository organisationRepository;

    @Autowired
    public SubscriptionRepository subscriptionRepository;

    @Autowired
    public AnnouncementRepository announcementRepository;

    @Autowired
    public CommentRepository commentRepository;
}
