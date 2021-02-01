package com.licenta.ogm.Rest;

import com.licenta.ogm.Comment.IAnnoucementComment;
import com.licenta.ogm.Entities.Comment;
import com.licenta.ogm.Exceptions.AnnouncementNotFoundException;
import com.licenta.ogm.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class AnnouncementComment implements IAnnoucementComment {
    private final CommentsService commentsService;

    @Autowired
    public AnnouncementComment(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @Override
    public ResponseEntity<Void> submitComment(@RequestHeader("userId") final Integer userId,
                                              @RequestParam("announcementId") final Integer announcementId,
                                              @RequestParam("text") final String text)
            throws AnnouncementNotFoundException {
        commentsService.submitComment(userId, announcementId, text);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<List<Comment>> getAnnouncementForOrganisationId(
            @RequestParam("announcementId") final Integer announcementId)
            throws AnnouncementNotFoundException {
        final List<Comment> comments = commentsService.getComments(announcementId);
        return new ResponseEntity<>(comments, OK);
    }
}
