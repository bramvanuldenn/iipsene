import com.google.api.client.json.Json;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MavenDatabase {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FileInputStream refreshToken = new FileInputStream(System.getProperty("user.dir") + "/risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                //.setDatabaseUrl("https://firebase-adminsdk-w2fgi@risk14.iam.gserviceaccount.com/")
                //.setProjectId(JVdjohjEM9i80EO4iATR)
                .build();

        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("players").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
//            System.out.println(document.getData());
            //test
        }

        // Object dat je gaat schrijven naar de database
        Map<String, String> map = new HashMap<>();
        map.put("name", "Koen");
        map.put("age", "20");
        map.put("city", "Woerden");

        System.out.println(map);

        // Data schrijven
        db.collection("players").document("Koen").set(map);

        // Specifieke data lezen
        System.out.println(db.collection("players").document("LA").get().get().getData());

//        // Initialize the default app
//        FirebaseApp defaultApp = FirebaseApp.initializeApp(defaultOptions);
//
//        System.out.println(defaultApp.getName());  // "[DEFAULT]"
//
//        // Retrieve services by passing the defaultApp variable...
//        FirebaseAuth defaultAuth = FirebaseAuth.getInstance(defaultApp);
//        FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(defaultApp);

    }
}
