package com.licenta.ogm.Comment;

import com.licenta.ogm.Entities.Announcement;
import com.licenta.ogm.Entities.Comment;
import com.licenta.ogm.Exceptions.AnnouncementNotFoundException;
import com.licenta.ogm.Exceptions.InvalidPasswordException;
import com.licenta.ogm.Exceptions.OrganisationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.licenta.ogm.Comment.Constants.AnnouncementCommentConstants.PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface IAnnoucementComment {
    @RequestMapping(value = PATH + "/submitComment", method = POST)
    ResponseEntity<Void> submitComment(@RequestHeader("userId") final Integer userId,
                                             @RequestParam("announcementId") final Integer announcementId,
                                             @RequestParam("text") final String text)
            throws AnnouncementNotFoundException;

    @RequestMapping(value = PATH + "/getComments", method = GET)
    ResponseEntity<List<Comment>> getAnnouncementForOrganisationId(
            @RequestParam("announcementId") final Integer announcementId)
            throws AnnouncementNotFoundException;
}
