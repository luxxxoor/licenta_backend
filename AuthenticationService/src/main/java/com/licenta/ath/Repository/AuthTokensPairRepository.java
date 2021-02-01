package com.licenta.ath.Repository;

import com.licenta.ath.Model.AuthTokensPair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokensPairRepository extends JpaRepository<AuthTokensPair, Long> {

    Optional<AuthTokensPair> findOneByAuthTokenAndRefreshToken(String authToken,
                                                               String refreshToken);

    void deleteByAuthToken(String authToken);
}