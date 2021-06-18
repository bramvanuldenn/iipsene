package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import models.Country;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseService {

    public static boolean addPlayer(String playerName) throws ExecutionException, InterruptedException, NullPointerException, IOException {
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
        future = snapshot.get();
        map = future.get().getData();
        return map.containsKey(String.valueOf(newUser.getPlayerId()));
    }


    public static void addCountries(ArrayList<Country> countries) throws IOException, ExecutionException, InterruptedException {
        for (Country country : countries) {
            Map<String, Object> countryData = new HashMap<>();
            countryData.put("countryTroops", country.getCountryTroops());
            countryData.put("countryOwner", country.getCountryOwner());

            ApiFuture<WriteResult> future = InitFirebase.getDbInstance()
                    .collection("countries")
                    .document(country.getCountryName())
                    .set(countryData);
            System.out.println(future.get().getUpdateTime());
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

    public static void addListener(String collection, String document) throws IOException, ExecutionException, InterruptedException {
        DocumentReference docRef = InitFirebase.getDbInstance().collection(collection).document(document);
        docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }
            if (snapshot != null && snapshot.exists()) {
                System.out.println("Current data: " + snapshot.getData());

            } else {
                System.out.print("Current data: null");
            }
        });
    }
}