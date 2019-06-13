package com.licenta.ogm.ORM;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"organisation\"")
@Data
@NoArgsConstructor
public class OrganisationORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String encryptedPassword;
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
    private List<AnnouncementORM> announcements = new ArrayList<>();
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
    private List<SubscriptionORM> subscriptions = new ArrayList<>();


    public OrganisationORM(final String name, final String encryptedPassword) {
        this.name = name;
        this.encryptedPassword = encryptedPassword;
    }
}