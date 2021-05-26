import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MyApp {

    public static void main(String[] args) {

    }

    {
        try {
            credentials = GoogleCredentials.getApplicationDefault();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    FileInputStream serviceAccount = new FileInputStream("fir-94ba8-firebase-adminsdk-1spqt-c295230fb1.json");
    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
    FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://fir-94ba8-default-rtdb.europe-west1.firebasedatabase.app")
            .build();
    FirebaseApp.initializeApp(options);
    Firestore db = FirestoreClient.getFirestore();

    //serviceAccount = new FileInputStream("C:HSLeiden\\IIPSENE\\fir-94ba8-firebase-adminsdk-1spqt-c295230fb1.json");

}
