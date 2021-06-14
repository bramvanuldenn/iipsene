package services;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class InitFirebase {
    private static Firestore db;

    public static Firestore getDbInstance() throws IOException, ExecutionException, InterruptedException {
        if (db == null){
            initDatabase();
        }
        return db;
    }

    private static void initDatabase() throws IOException, ExecutionException, InterruptedException, NullPointerException {
        FileInputStream refreshToken = new FileInputStream("risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }
}