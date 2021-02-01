package com.licenta.ogm.ORM;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "\"comment\"")
@Data
@NoArgsConstructor
public class CommentORM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private Integer userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    private AnnouncementORM announcement;
    @Lob
    @Column
    private String text;


    public CommentORM(final Integer userId, final AnnouncementORM announcement, final String text) {
        this.userId = userId;
        this.announcement = announcement;
        this.text = text;
    }
}