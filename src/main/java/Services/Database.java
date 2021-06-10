package Services;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class Database {
    private static Firestore db;

    public static void firestore() throws IOException, ExecutionException, InterruptedException {
        FileInputStream refreshToken = new FileInputStream(System.getProperty("user.dir") + "/risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();

        FirebaseApp.initializeApp(options);

        Database.db = FirestoreClient.getFirestore();

//        //asynchronously retrieve all documents
//        ApiFuture<QuerySnapshot> future = db
//                .collection("players").get();
//
//        // future.get() blocks on response
//        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//        for (QueryDocumentSnapshot document : documents) {
//            System.out.println(document.getData());
//        }
        db.collection("cities")
                .whereEqualTo("state", "CA")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirestoreException e) {
                        if (e != null) {
                            System.err.println("Listen failed:" + e);
                            return;
                        }

                        List<String> cities = new ArrayList<>();
                        for (DocumentSnapshot doc : snapshots) {
                            if (doc.get("name") != null) {
                                cities.add(doc.getString("name"));
                            }
                        }
                        System.out.println("Current cites in CA: " + cities);
//                        dfasdhfasdhfjasdhvuocvuj
                    }
                });

        // Object dat je gaat schrijven naar de database
        Map<String, String> map = new HashMap<>();
        map.put("name", "Koen");
        map.put("age", "20");
        map.put("city", "Woerden");

        System.out.println(map);

        // Data schrijven
        db.collection("players").document("Hayyan").set(map);

        // Specifieke data lezen
        System.out.println(db.collection("players").document("info").get().get().getData());
    }
}