package com.licenta.usm.ORM;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nickName;
    @Column
    private String encryptedPassword;
    @Column
    private String email;

    public User(final String nickName, final String encryptedPassword, final String email) {
        this.nickName = nickName;
        this.encryptedPassword = encryptedPassword;
        this.email = email;
    }
}
