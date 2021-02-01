package com.licenta.ath.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class FirebaseAdminSdkConfiguration {
    @PostConstruct
    public void setup() throws IOException {
        final String pathToFirebaseAccountKeyFile =
                getClass().getClassLoader().getResource("firebase.json").getFile();

        FileInputStream serviceAccount =
                new FileInputStream(pathToFirebaseAccountKeyFile);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ontop-64f0b.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

}
