package com.licenta.ogm.Repository;

import com.licenta.ogm.ORM.SubscriptionORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionORM, Integer> {
    List<SubscriptionORM> findAllByUserId(final Integer userId);

    Optional<SubscriptionORM> findByUserIdAndOrganisation_Id(final Integer userId, final Integer organisationId);
}
