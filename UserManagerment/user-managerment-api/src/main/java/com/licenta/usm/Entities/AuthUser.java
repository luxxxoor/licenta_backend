package com.licenta.usm.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    private String nickName;
    private String BCryptedPassword;
}
