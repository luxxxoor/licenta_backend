package com.licenta.ogm.Announcement;

import com.licenta.ogm.Entities.Announcement;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.licenta.ogm.Announcement.Constants.OrganisationAnnouncementConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IOrganisationAnnouncement {
    @RequestMapping(value = PATH + "/publish", method = POST)
    ResponseEntity<Void> publishAnnouncement(@RequestParam("organisationId") final Integer organisationId,
                                             @RequestParam("password") final String password,
                                             @RequestBody final Announcement announcement)
            throws OrganisationNotFoundException, InvalidPasswordException;

    @RequestMapping(value = PATH + "/getByOrganisationId", method = GET)
    ResponseEntity<List<Announcement>> getAnnouncementForOrganisationId(
            @RequestParam("organisationId") final Integer organisationId)
            throws OrganisationNotFoundException;

    @RequestMapping(value = PATH + "/getByUserId", method = GET)
    ResponseEntity<List<Announcement>> getLastAnnouncementForUserId(@RequestHeader("userId") final Integer userId);
}