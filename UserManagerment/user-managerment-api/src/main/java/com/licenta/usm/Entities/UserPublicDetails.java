package com.licenta.usm.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPublicDetails {
    @NotNull
    private Integer id;
    @NotNull
    private String nickName;
}
