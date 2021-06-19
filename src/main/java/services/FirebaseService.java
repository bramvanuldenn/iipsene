package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import models.Country;
import models.MainMenu;
import models.User;
import views.MainMenuView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseService {

    public static void addPlayer(String playerName) throws ExecutionException, InterruptedException, NullPointerException, IOException {
        User newUser = new User();
        Integer idNum;
        DocumentReference snapshot = InitFirebase.getDbInstance().collection("players").document("info");
        ApiFuture<DocumentSnapshot> future = snapshot.get();
        Map<String, Object> map = future.get().getData();
        idNum = map.size();
        System.out.println(idNum);

        newUser.setPlayerId(idNum + 1);
        newUser.setPlayerName(playerName);

        Map<String, Object> userData = new HashMap<>();
        userData.put("playerName", newUser.getPlayerName());
        userData.put("cards", newUser.getCardArray());
        userData.put("countries", newUser.getCountryArray());

        ApiFuture<WriteResult> docRef = InitFirebase.getDbInstance()
                .collection("players")
                .document("info")
                .update(String.valueOf(newUser.getPlayerId()), userData);
        System.out.println("Added user: " + docRef.get());
    }


    public static void addCountries(ArrayList<Country> countries) throws IOException, ExecutionException, InterruptedException {
        WriteBatch batch = InitFirebase.getDbInstance().batch();
        for (Country country : countries) {
            DocumentReference docRef = InitFirebase.getDbInstance().collection("countries").document(country.getCountryName());
            batch.update(docRef, "countryTroops", 0);
            batch.update(docRef, "countryOwner", null);
        }
        ApiFuture<List<WriteResult>> future = batch.commit();
        for (WriteResult result : future.get()) {
            System.out.println("Update time: " + result.getUpdateTime());
        }
    }

    public static void updateCountry (Country country) throws IOException, ExecutionException, InterruptedException
    {
        WriteBatch batch = InitFirebase.getDbInstance().batch();
        DocumentReference docRef = InitFirebase.getDbInstance().collection("countries").document(country.getCountryName());
        batch.update(docRef, "countryTroops", country.getCountryTroops());
        batch.update(docRef, "countryOwner", country.getCountryOwner().getPlayerId());
        ApiFuture<List<WriteResult>> future = batch.commit();
        for (WriteResult result : future.get()) {
            System.out.println("Update time: " + result.getUpdateTime());
        }
    }


    public static void addUsersListener(MainMenuView.firebaseCallback callback) throws IOException {
        DocumentReference docRef = InitFirebase.getDbInstance().collection("players").document("info");
        docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }
            if (snapshot != null && snapshot.exists()) {
                callback.onCallback(snapshot.getData());
            } else {
                System.out.print("Current data: null");
            }
        });
    }
}