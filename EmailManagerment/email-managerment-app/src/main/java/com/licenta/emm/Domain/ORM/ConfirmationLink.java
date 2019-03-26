package com.licenta.emm.Domain.ORM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"email_confirmation\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne()
    private Email email;
    @Column
    private String link;

    public ConfirmationLink(final Email email, final String link) {
        this.email = email;
        this.link = link;
    }
}
