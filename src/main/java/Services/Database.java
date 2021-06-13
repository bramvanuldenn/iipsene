package Services;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class Database {
    private static Firestore db;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        initPlayer();
    }

    public static void initPlayer() throws IOException, ExecutionException, InterruptedException {
        FileInputStream refreshToken = new FileInputStream(System.getProperty("user.dir") + "/risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();

        Scanner input = new Scanner(System.in);
        System.out.println("give a new name without spaces");
        String name = input.next();
        System.out.println("give a new id");
        int id = input.nextInt();
        // Object dat je gaat schrijven naar de database
        Map<String, Integer> map = new HashMap<>();
        map.put(name,id);

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> docRef = db
                .collection("players")
                .document("info")
                .update(name, id);
        System.out.println("Update time : " + docRef.get().getUpdateTime());
    }

    public void
}