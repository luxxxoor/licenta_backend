package com.licenta.ogm.ORM;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "\"subscription\"")
@Data
@NoArgsConstructor
public class SubscriptionORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private Integer userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organisation_id")
    private OrganisationORM organisation;

    public SubscriptionORM(final Integer userId, final OrganisationORM organisation) {
        this.userId = userId;
        this.organisation = organisation;
    }
}
