package com.licenta.usm.ORM;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "\"USERS\"")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    private String phoneNumber;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    public User(final String name, final String phoneNumber, final String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
