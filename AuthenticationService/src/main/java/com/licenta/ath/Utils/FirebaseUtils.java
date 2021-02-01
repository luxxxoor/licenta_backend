package com.licenta.ath.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.security.authentication.BadCredentialsException;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public abstract class FirebaseUtils {
    public static FirebaseToken validateFirebaseToken(final String firebaseToken) {
        if (isEmpty(firebaseToken)) {
            throw new BadCredentialsException("The firebase authentication token is missing.");
        }

        try {
            return FirebaseAuth.getInstance().verifyIdToken(firebaseToken, true);
        }
        catch (FirebaseAuthException e) {
            throw new BadCredentialsException("The firebase authentication token is not valid.");
        }
    }
}