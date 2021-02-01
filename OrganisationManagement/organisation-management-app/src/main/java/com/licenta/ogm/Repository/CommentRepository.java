package com.licenta.ogm.Repository;

import com.licenta.ogm.ORM.CommentORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentORM, Integer> {
    List<CommentORM> findAllByAnnouncement_Id(final Integer announcementId);
}