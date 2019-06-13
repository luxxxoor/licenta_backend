package com.licenta.ogm.Repository;

import com.licenta.ogm.ORM.AnnouncementORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<AnnouncementORM, Integer> {

}
