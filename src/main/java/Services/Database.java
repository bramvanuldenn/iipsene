package Services;

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


public class Database {
    private static Firestore db;
    static String esm = "";
    static User user = new User();

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        delPlayer();
    }

    public static void initPlayer() throws IOException, ExecutionException, InterruptedException, NullPointerException {
        FileInputStream refreshToken = new FileInputStream(System.getProperty("user.dir") + "/risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();

        // Object dat je gaat schrijven naar de database
        Scanner input = new Scanner(System.in);
        System.out.println("give a new name without spaces");
        esm = input.nextLine();
        String name = user.setPlayerName(esm);
        Integer idNum = db.collection("players").document("info").get().get().getData().size();
        user.setPlayerId(idNum+1);

        ApiFuture<WriteResult> docRef = db
                .collection("players")
                .document("info")
                .update(name, user.getPlayerId());
        System.out.println("Update time : " + docRef.get().getUpdateTime());
    }

    public static void delPlayer() throws ExecutionException, InterruptedException, NullPointerException {
        User user = new User();
        String name = user.setPlayerName(esm);
        DocumentReference docRef = db.collection("players").document("info");
// Update and delete the "capital" field in the document
        ApiFuture<WriteResult> writeResult = docRef.update(name, FieldValue.delete());
        System.out.println("Update time : " + writeResult.get());
    }
}