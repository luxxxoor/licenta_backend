package com.licenta.ath.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.licenta.ath.Exceptions.HttpException;
import com.licenta.ath.Model.AuthTokensPair;
import com.licenta.ath.Model.RefreshTokenVo;
import com.licenta.ath.Repository.AuthTokensPairRepository;
import com.licenta.ath.Utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

import static com.auth0.jwt.JWT.decode;
import static com.licenta.ath.Utils.TokenGenerator.TokenType.AUTH;
import static com.licenta.ath.Utils.TokenGenerator.TokenType.REFRESH;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@Service
public class AuthTokensPairServiceImpl implements AuthTokensPairService {

    private final AuthTokensPairRepository authTokensPairRepository;

    private final TokenGenerator tokenGenerator;


    @Autowired
    public AuthTokensPairServiceImpl(AuthTokensPairRepository authTokensPairRepository,
                                     TokenGenerator tokenGenerator) {
        this.authTokensPairRepository = authTokensPairRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    @Transactional
    public void saveToken(RefreshTokenVo refreshTokenVo) {
        final DecodedJWT decodedRefreshToken = decode(refreshTokenVo.getRefreshToken());
        final AuthTokensPair authTokensPair = new AuthTokensPair(
                refreshTokenVo.getAuthToken(),
                refreshTokenVo.getRefreshToken(),
                decodedRefreshToken.getExpiresAt().getTime());
        authTokensPairRepository.save(authTokensPair);
    }

    @Override
    @Transactional
    public RefreshTokenVo refreshToken(RefreshTokenVo oldToken) {
        Optional<AuthTokensPair> optionalToken = authTokensPairRepository.findOneByAuthTokenAndRefreshToken(
                oldToken.getAuthToken(),
                oldToken.getRefreshToken());

        if (optionalToken.isEmpty() ||
                !isTokenExpired(oldToken.getAuthToken()) ||
                isTokenExpired(oldToken.getRefreshToken())) {
            throw new HttpException(UNAUTHORIZED);
        }

        AuthTokensPair oldAuthTokensPair = optionalToken.get();
        String oldAuthToken = oldAuthTokensPair.getAuthToken();
        DecodedJWT decodedOldAuthToken = decode(oldAuthToken);
        String userId = decodedOldAuthToken.getClaim("userId").asString();

        final String newAuthToken = tokenGenerator.generateToken(AUTH, decodedOldAuthToken.getSubject(),
                userId);
        final String newRefreshToken = tokenGenerator.generateToken(REFRESH, decodedOldAuthToken.getSubject(),
                userId);
        final DecodedJWT decodedNewRefreshToken = decode(newRefreshToken);
        authTokensPairRepository.save(new AuthTokensPair(newAuthToken, newRefreshToken,
                decodedNewRefreshToken.getExpiresAt().getTime()));
        authTokensPairRepository.delete(oldAuthTokensPair);

        return new RefreshTokenVo(newAuthToken, newRefreshToken);
    }

    private boolean isTokenExpired(String token) {
        DecodedJWT decodedToken = decode(token);
        return decodedToken.getExpiresAt().before(new Date());
    }

    @Override
    @Transactional
    public void logout(String authToken) {
        authToken = authToken.replace("Bearer ", "");
        long remainingSeconds = getRemainingSecondsForToken(authToken);

        authTokensPairRepository.deleteByAuthToken(authToken);
    }

    private long getRemainingSecondsForToken(String token) {
        DecodedJWT decodedToken = decode(token);
        long expireTimestamp = decodedToken.getExpiresAt().getTime();
        long remainingMilliseconds = expireTimestamp - new Date().getTime();
        return MINUTES.toSeconds(MILLISECONDS.toMinutes(remainingMilliseconds));
    }
}