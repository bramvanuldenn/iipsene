package services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;


public class InitFirebase {
    private static Firestore db;

    public static Firestore getDbInstance() throws IOException {
        if (db == null){
            initDatabase();
        }
        return db;
    }

    private static void initDatabase() throws IOException, NullPointerException {
        FileInputStream refreshToken = new FileInputStream("risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }
}