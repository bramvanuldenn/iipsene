package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import models.Country;
import models.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FirebaseService {


    public static boolean addPlayer(String playerName) throws ExecutionException, InterruptedException, NullPointerException, IOException {
        User newUser = new User();
        Integer idNum = 0;
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
        if (map.containsKey(String.valueOf(newUser.getPlayerId()))) {
            return true;
        }
        return false;
    }


    public static void addCountries(ArrayList<Country> countries) throws IOException, ExecutionException, InterruptedException {
        for (int i = 0; i < countries.size(); i++) {

            Map<String, Object> countryData = new HashMap<>();
            countryData.put("countryTroops", countries.get(i).getCountryTroops());
            countryData.put("countryOwner", countries.get(i).getCountryOwner());

            ApiFuture<WriteResult> future = InitFirebase.getDbInstance()
                    .collection("countries")
                    .document(countries.get(i).getCountryName())
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
}