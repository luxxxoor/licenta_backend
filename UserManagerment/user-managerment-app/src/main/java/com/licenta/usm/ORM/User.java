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
    private String password;
    @Column
    private String email;

    public User(final String nickName, final String password, final String email) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }
}
