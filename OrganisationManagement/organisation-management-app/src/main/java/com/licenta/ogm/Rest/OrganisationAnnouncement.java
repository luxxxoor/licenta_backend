package com.licenta.ogm.Rest;

import com.licenta.ogm.Announcement.IOrganisationAnnouncement;
import com.licenta.ogm.Entities.Announcement;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import com.licenta.ogm.Service.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrganisationAnnouncement implements IOrganisationAnnouncement {
    private final AnnouncementsService announcementsService;

    @Autowired
    public OrganisationAnnouncement(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    @Override
    public ResponseEntity<Void> publishAnnouncement(@RequestParam("organisationId") final Integer organisationId,
                                                    @RequestParam("password") final String password,
                                                    @RequestBody final Announcement announcement)
            throws OrganisationNotFoundException, InvalidPasswordException {
        announcementsService.publishAnnouncement(organisationId, password, announcement);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<List<Announcement>> getAnnouncementForOrganisationId(
            @RequestParam("organisationId") final Integer organisationId)
            throws OrganisationNotFoundException {
        final List<Announcement> announcements = announcementsService.getAnnouncementForOrganisationId(organisationId);
        return new ResponseEntity<>(announcements, OK);
    }

    @Override
    public ResponseEntity<List<Announcement>> getLastAnnouncementForUserId(
            @RequestParam("userId") final Integer userId) {
        final List<Announcement> announcements = announcementsService.getLastAnnouncementForUserId(userId);
        return new ResponseEntity<>(announcements, OK);
    }
}
