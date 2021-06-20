package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import models.Country;
import models.User;
import views.MainMenuView;

import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirebaseService {

    public static final String COUNTRY_COLLECTION = "countries";
    public static final String CARD_COLLECTION = "cards";
    public static final String PLAYER_COLLECTION = "players";
    private static Stack<Color> availableColors;

    public static void addPlayer(String playerName) throws Exception {
        if (availableColors == null) {
            initAvailableColors();
        }

        User newUser = new User();
        Integer idNum;
        DocumentReference snapshot = InitFirebase.getDbInstance().collection(PLAYER_COLLECTION).document("info");
        ApiFuture<DocumentSnapshot> future = snapshot.get();
        Map<String, Object> map = future.get().getData();
        idNum = map.size();
        System.out.println(idNum);

        newUser.setPlayerId(idNum + 1);
        newUser.setPlayerName(playerName);
        newUser.setColor(availableColors.pop());

        Map<String, Object> userData = new HashMap<>();
        userData.put("playerName", newUser.getPlayerName());
        userData.put(CARD_COLLECTION, newUser.getCardArray());
        userData.put(COUNTRY_COLLECTION, newUser.getCountryArray());

        ApiFuture<WriteResult> docRef = InitFirebase.getDbInstance()
            .collection(PLAYER_COLLECTION)
            .document("info")
            .update(String.valueOf(newUser.getPlayerId()), userData);
        System.out.println("Added user: " + docRef.get());
    }

    private static void initAvailableColors() {
        availableColors = new Stack<>();
        availableColors.push(Color.PINK);
        availableColors.push(Color.BLACK);
        availableColors.push(Color.DEEPPINK);
        availableColors.push(Color.WHITE);
        availableColors.push(Color.BLUEVIOLET);
        availableColors.push(Color.YELLOW);
        availableColors.push(Color.DARKOLIVEGREEN);
        availableColors.push(Color.RED); // This one is popped first!
    }

    public static void assignCountries() throws Exception {
        List<String> countries = fetchCountries();
        List<String> players = fetchPlayers();
        for (String player : players) {
            System.out.println(player);
        }
    }

    private static List<String> fetchCountries() throws Exception {
        CollectionReference countriesReference = InitFirebase.getDbInstance()
                .collection(COUNTRY_COLLECTION);
        List<String> countries = new ArrayList<>();
        for (DocumentReference country : countriesReference.listDocuments()) {
            countries.add(country.getPath());
        }
        return countries;
    }

    private static List<String> fetchPlayers() throws Exception {
        List<String> players = new ArrayList<>();
        DocumentReference docRef = InitFirebase.getDbInstance().collection(PLAYER_COLLECTION).document("info");
        ApiFuture<DocumentSnapshot> playerFuture = docRef.get();
        DocumentSnapshot playerDocument = playerFuture.get();
        Map<String,Object> playerMap = playerDocument.getData();
        playerMap.get("info");
        for (String key : playerMap.keySet()) {
            Map<String, Object> value = (Map<String,Object>)playerMap.get(key);
            players.add((String)value.get("playerName"));
        }
        return players;
    }

    public static void addCountries(ArrayList<Country> countries) throws IOException, ExecutionException, InterruptedException {
        WriteBatch batch = InitFirebase.getDbInstance().batch();
        for (Country country : countries) {
            DocumentReference docRef = InitFirebase.getDbInstance().collection(COUNTRY_COLLECTION).document(country.getCountryName());
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
        DocumentReference docRef = InitFirebase.getDbInstance().collection(COUNTRY_COLLECTION).document(country.getCountryName());
        batch.update(docRef, "countryTroops", country.getCountryTroops());
        batch.update(docRef, "countryOwner", country.getCountryOwner().getPlayerId());
        ApiFuture<List<WriteResult>> future = batch.commit();
        for (WriteResult result : future.get()) {
            System.out.println("Update time: " + result.getUpdateTime());
        }
    }


    public static void addUsersListener(MainMenuView.firebaseCallback callback) throws IOException {
        DocumentReference docRef = InitFirebase.getDbInstance().collection(PLAYER_COLLECTION).document("info");
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