package com.licenta.ogm.Service;

import com.licenta.ogm.Entities.Comment;
import com.licenta.ogm.Exceptions.AnnouncementNotFoundException;
import com.licenta.ogm.Feign.UserProxy;
import com.licenta.ogm.ORM.AnnouncementORM;
import com.licenta.ogm.ORM.CommentORM;
import com.licenta.ogm.Repository.AnnouncementRepository;
import com.licenta.ogm.Repository.CommentRepository;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommentsService {
    private final AnnouncementRepository announcementRepository;
    private final CommentRepository commentRepository;
    private final UserProxy userProxy;

    @Autowired
    public CommentsService(final AnnouncementRepository announcementRepository,
                           final CommentRepository commentRepository,
                           final UserProxy userProxy) {
        this.announcementRepository = announcementRepository;
        this.commentRepository = commentRepository;
        this.userProxy = userProxy;
    }

    public void submitComment(final Integer userId, final Integer announcementId,
                              final String text) throws AnnouncementNotFoundException {
        final Optional<AnnouncementORM> optionalAnnouncementORM =
                announcementRepository.findById(announcementId);

        if (optionalAnnouncementORM.isEmpty()) {
            throw new AnnouncementNotFoundException();
        }

        final var announcementORM = optionalAnnouncementORM.get();

        final var commentORM = new CommentORM(userId, announcementORM, text);

        commentRepository.save(commentORM);
    }

    @Transactional
    public List<Comment> getComments(final Integer announcementId)
            throws AnnouncementNotFoundException {
        final Optional<AnnouncementORM> optionalAnnouncementORM =
                announcementRepository.findById(announcementId);

        if (optionalAnnouncementORM.isEmpty()) {
            throw new AnnouncementNotFoundException();
        }

        final List<CommentORM> commentORMs = commentRepository.findAllByAnnouncement_Id(announcementId);

        final List<Comment> comments = commentORMs.stream().map(c-> {
            try {
                System.out.println(userProxy.findNameById(c.getUserId()).getBody().getNickName());
                return new Comment(c.getId(),
                        userProxy.findNameById(c.getUserId()).getBody().getNickName(), c.getText());
            } catch (UserNotFoundException ignored) {
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        return comments;
    }
}
