package com.licenta.ath.Service;

import com.licenta.ath.Model.RefreshTokenVo;

public interface AuthTokensPairService {

    void saveToken(RefreshTokenVo refreshTokenVo);

    RefreshTokenVo refreshToken(RefreshTokenVo oldToken);

    void logout(final String authToken);
}
