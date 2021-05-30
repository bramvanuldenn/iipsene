import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.IOException;

public class MavenDatabase {

    public static void main(String[] args) throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://firebase-adminsdk-w2fgi@risk14.iam.gserviceaccount.com/")
                //.setProjectId(JVdjohjEM9i80EO4iATR)
                .build();

//        FileInputStream refreshToken = new FileInputStream("risk14-firebase-adminsdk-w2fgi-89c33e4d8e.json");
        FirebaseApp.initializeApp(options);

//        // Initialize the default app
//        FirebaseApp defaultApp = FirebaseApp.initializeApp(defaultOptions);
//s
//        System.out.println(defaultApp.getName());  // "[DEFAULT]"
//
//        // Retrieve services by passing the defaultApp variable...
//        FirebaseAuth defaultAuth = FirebaseAuth.getInstance(defaultApp);
//        FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(defaultApp);

    }
}
