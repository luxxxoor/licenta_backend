package com.licenta.emm.Domain.ORM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"emails\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private String email;
    @Column
    private Boolean confirmed;

    public Email(final Integer userId, final String email) {
        this.userId = userId;
        this.email = email;
        this.confirmed = false;
    }
}
