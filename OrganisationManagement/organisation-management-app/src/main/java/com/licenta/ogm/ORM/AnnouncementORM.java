package com.licenta.ogm.ORM;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "\"announcement\"")
@Data
@NoArgsConstructor
public class AnnouncementORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String title;
    @Column
    private String imageUrl;
    @Lob
    @Column
    private String description;
    @Column
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organisation_id")
    private OrganisationORM organisation;

    public AnnouncementORM(final String title, final String imageUrl, final String description, final String date,
                           final OrganisationORM organisation) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.date = date;
        this.organisation = organisation;
    }
}
