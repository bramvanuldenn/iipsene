package controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.WriteResult;
import models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class StartMenuController extends services.FirebaseService {
    static String esm = "";
    static User user = new User();

    public static void initPlayer() throws IOException, ExecutionException, InterruptedException, NullPointerException {

        // Object dat je gaat schrijven naar de database
        Scanner input = new Scanner(System.in);
        System.out.println("give a new name without spaces");
        esm = input.nextLine();
        String name = user.setPlayerName(esm);
        Integer idNum = Objects.requireNonNull(db.collection("players").document("info").get().get().getData()).size();
        user.setPlayerId(idNum+1);
        System.out.println(user.getPlayerId());


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
        // Update and delete a field in the document
        ApiFuture<WriteResult> writeResult = docRef.update(name, FieldValue.delete());
        System.out.println("Update time : " + writeResult.get());
    }
}
