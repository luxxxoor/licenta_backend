package com.licenta.ath.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AUTH_TOKEN_PAIRS")
@SequenceGenerator(name = "AUTH_TOKEN_PAIRS_ID_GEN", sequenceName = "AUTH_TOKEN_PAIRS_SEQ",
        allocationSize = 1)
public class AuthTokensPair {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "AUTH_TOKEN_PAIRS_ID_GEN")
    private Long id;

    @Column(name = "AUTH_TOKEN", nullable = false)
    private String authToken;

    @Column(name = "REFRESH_TOKEN", nullable = false)
    private String refreshToken;

    @Column(name = "REFRESH_TOKEN_EXPIRE_DATE", nullable = false)
    private long refreshTokenExpireDate;

    public AuthTokensPair(String authToken, String refreshToken, long refreshTokenExpireDate) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireDate = refreshTokenExpireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthTokensPair that = (AuthTokensPair) o;
        return refreshTokenExpireDate == that.refreshTokenExpireDate &&
                id.equals(that.id) &&
                authToken.equals(that.authToken) &&
                refreshToken.equals(that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authToken, refreshToken, refreshTokenExpireDate);
    }
}