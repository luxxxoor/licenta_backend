package com.licenta.ogm.Service;

import com.licenta.ogm.Entities.Announcement;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.ORM.AnnouncementORM;
import com.licenta.ogm.ORM.OrganisationORM;
import com.licenta.ogm.ORM.SubscriptionORM;
import com.licenta.ogm.Repository.AnnouncementRepository;
import com.licenta.ogm.Repository.OrganisationRepository;
import com.licenta.ogm.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnnouncementsService {
    private final AnnouncementRepository announcementRepository;
    private final OrganisationRepository organisationRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public AnnouncementsService(final AnnouncementRepository announcementRepository,
                                final OrganisationRepository organisationRepository,
                                final SubscriptionRepository subscriptionRepository) {
        this.announcementRepository = announcementRepository;
        this.organisationRepository = organisationRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void publishAnnouncement(final Integer organisationId, final String password,
                                    final Announcement announcement)
            throws OrganisationNotFoundException, InvalidPasswordException {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final Optional<OrganisationORM> optionalOrganisationORM =
                organisationRepository.findById(organisationId);

        if (optionalOrganisationORM.isEmpty()) {
            throw new OrganisationNotFoundException();
        }

        final var organisationORM = optionalOrganisationORM.get();

        if (!bCryptPasswordEncoder.matches(password, organisationORM.getEncryptedPassword())) {
            throw new InvalidPasswordException("Password does not match.");
        }

        final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final var currentDate = new Date();

        final var announcementORM = new AnnouncementORM(announcement.getTitle(), announcement.getImageUrl(),
                announcement.getDescription(), dateFormat.format(currentDate), organisationORM);

        announcementRepository.save(announcementORM);
    }

    @Transactional
    public List<Announcement> getAnnouncementForOrganisationId(final Integer organisationId)
            throws OrganisationNotFoundException {
        final Optional<OrganisationORM> optionalOrganisationORM = organisationRepository.findById(organisationId);

        if (optionalOrganisationORM.isEmpty()) {
            throw new OrganisationNotFoundException();
        }

        final var organisationORM = optionalOrganisationORM.get();

        final List<Announcement> announcements = organisationORM.getAnnouncements().stream().map(
                a -> new Announcement(a.getId(), a.getTitle(), a.getImageUrl(), a.getDescription(),
                        a.getDate(), a.getOrganisation().getName()))
                .collect(Collectors.toList());

        return announcements;
    }

    @Transactional
    public List<Announcement> getLastAnnouncementForUserId(final Integer userId) {
        final List<SubscriptionORM> subscriptionORMList = subscriptionRepository.findAllByUserId(userId);
        final List<OrganisationORM> organisationORMList =
                subscriptionORMList.stream().map(SubscriptionORM::getOrganisation).collect(Collectors.toList());

        final List<AnnouncementORM> announcementORMList =
                organisationORMList.stream().map(OrganisationORM::getAnnouncements).flatMap(List::stream)
                        .collect(Collectors.toList());
        final List<Announcement> announcements = announcementORMList.stream().map(a -> new Announcement(a.getId(),
                a.getTitle(), a.getImageUrl(), a.getDescription(), a.getDate(),
                a.getOrganisation().getName())).sorted(Comparator.comparing(Announcement::getDate))
                .collect(Collectors.toList());

        return announcements;
    }
}
