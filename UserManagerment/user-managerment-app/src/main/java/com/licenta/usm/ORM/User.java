package com.licenta.usm.Domain.ORM;


import com.licenta.usm.Entities.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private byte[] password;
    @Column
    private String email;

    public User(final String nickName, final byte[] password, final String email) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNickName(nickName);
        userDTO.setPassword(password);
        userDTO.setEmail(email);
        return userDTO;
    }
}
