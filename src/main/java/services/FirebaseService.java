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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseService {

// hayyan branch
    public static void addPlayer(String playerName) throws ExecutionException, InterruptedException, NullPointerException, IOException {

        User newUser = new User();

        Integer idNum = 0;
        DocumentReference snapshot = InitFirebase.getDbInstance().collection("players").document("info");
        ApiFuture<DocumentSnapshot> future = snapshot.get();
        Map<String, Object> map = future.get().getData();
        idNum = map.size();
        System.out.println(idNum);

        newUser.setPlayerId(idNum+1);
        newUser.setPlayerName(playerName);

        ApiFuture<WriteResult> docRef = InitFirebase.getDbInstance()
                .collection("players")
                .document("info")
                .update(newUser.getPlayerName(), newUser.getPlayerId());

        System.out.println("Added user: " + docRef.get());
    }
}