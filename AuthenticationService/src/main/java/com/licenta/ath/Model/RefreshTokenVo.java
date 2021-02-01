package com.licenta.ath.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenVo {
    @NotEmpty(message = "Authentication token must not be null.")
    private String authToken;

    @NotEmpty(message = "Refresh token must not be null.")
    private String refreshToken;
}
