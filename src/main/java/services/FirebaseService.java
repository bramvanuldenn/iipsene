package services;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseService {
    private static Firestore db;

    public static void addPlayer(String playerName) throws ExecutionException, InterruptedException, NullPointerException, IOException {
        FileInputStream refreshToken = new FileInputStream("risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();

        User newUser = new User();

        Integer idNum = 0;
        DocumentReference snapshot = db.collection("players").document("info");
        ApiFuture<DocumentSnapshot> future = snapshot.get();
        Map<String, Object> map = future.get().getData();
        idNum = map.size();
        System.out.println(idNum);

        newUser.setPlayerId(idNum+1);
        newUser.setPlayerName(playerName);


        ApiFuture<WriteResult> docRef = db
                .collection("players")
                .document("info")
                .update(newUser.getPlayerName(), newUser.getPlayerId());

        System.out.println("Added user: " + docRef.get());
    }
}