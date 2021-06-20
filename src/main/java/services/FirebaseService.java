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
    private static List<Color> availableColors = List.of(Color.BLUEVIOLET, Color.YELLOW, Color.DARKOLIVEGREEN, Color.RED);

    public static void addPlayer(String playerName) throws Exception {
        User newUser = new User();
        Integer idNum;
        DocumentReference snapshot = InitFirebase.getDbInstance().collection(PLAYER_COLLECTION).document("info");
        ApiFuture<DocumentSnapshot> future = snapshot.get();
        Map<String, Object> map = future.get().getData();
        idNum = map.size();
        System.out.println(idNum);

        newUser.setPlayerId(idNum + 1);
        newUser.setPlayerName(playerName);
        newUser.setColor(availableColors.get(newUser.getPlayerId() % 4));

        Map<String, Object> userData = new HashMap<>();
        userData.put("playerName", newUser.getPlayerName());
        userData.put("color", newUser.getColor().toString());
        userData.put(CARD_COLLECTION, newUser.getCardArray());
        userData.put(COUNTRY_COLLECTION, newUser.getCountryArray());

        ApiFuture<WriteResult> docRef = InitFirebase.getDbInstance()
            .collection(PLAYER_COLLECTION)
            .document("info")
            .update(String.valueOf(newUser.getPlayerId()), userData);
        System.out.println("Added user: " + docRef.get());
    }
//give countries to users
    public static void assignCountries() throws Exception {
        //get last 4 players out of database
        List<User> players = selectLastFourPlayers(fetchPlayers());
        Map<Integer, User> playerMap = new HashMap<>();
        //list filled with user id's
        List<Integer> keys = new ArrayList<>();
        for (User player : players) {
            System.out.println(player.getPlayerId() + ": " + player.getPlayerName());
            playerMap.put(player.getPlayerId(), player);
            keys.add(player.getPlayerId());
        }
        List<Country> countries = fetchCountries(players, true);
        int currentAssignmentPosition = 0;
        WriteBatch batch = InitFirebase.getDbInstance().batch();
        while (!countries.isEmpty()) {
            Integer currentKey = keys.get(currentAssignmentPosition % 4);
            int randomRoll = countries.size() == 1 ?
                0 :
                new Random().nextInt(countries.size() - 1);
            Country selectedCountry = countries.remove(randomRoll);
            //give the country an owner
            selectedCountry.setCountryOwner(playerMap.get(currentKey));
            System.out.println(currentKey + ": " + selectedCountry.getCountryName());
            DocumentReference docRef = InitFirebase.getDbInstance().collection(COUNTRY_COLLECTION).document(selectedCountry.getCountryName());
            batch.update(docRef, "countryOwner", currentKey);
            currentAssignmentPosition++;
        }
        ApiFuture<List<WriteResult>> future = batch.commit();
    }

    public static List<User> selectLastFourPlayers(List<User> fetchPlayers) {
        List<User> selectedPlayers = new ArrayList<>();
        Collections.sort(fetchPlayers, Comparator.comparing(User::getPlayerId));
        Collections.reverse(fetchPlayers);
        for (User player : fetchPlayers) {
            player.setColor(null);
        }
        for (int playerNumber = 0; playerNumber < 4; playerNumber++) {
            selectedPlayers.add(fetchPlayers.get(playerNumber));
        }
        return selectedPlayers;
    }

    public static List<Country> fetchCountries(List<User> players) throws Exception {
        return fetchCountries(players, false);
    }

    private static List<Country> fetchCountries(List<User> players, boolean startNewGame) throws Exception {
        CollectionReference countriesReference = InitFirebase.getDbInstance()
                .collection(COUNTRY_COLLECTION);
        List<Country> countries = new ArrayList<>();
        for (DocumentReference countryRef : countriesReference.listDocuments()) {
            ApiFuture<DocumentSnapshot> countryFuture = countryRef.get();
            DocumentSnapshot countryDocument = countryFuture.get();
            Map<String,Object> countryMap = countryDocument.getData();
            Country country = new Country();
            country.setCountryName(countryRef.getPath().substring(10));
            if (!startNewGame) {
                Integer ownerId = Math.toIntExact((Long)countryMap.get("countryOwner"));
                country.setCountryOwner(players.stream().filter(player -> player.getPlayerId().equals(ownerId)).findFirst().get());
            }
            // TODO 1. uitlezen en opslaan, zie onder
            country.setCountryTroops(Math.toIntExact((Long) countryMap.get("countryTroops")));
            country.setCountryX(Math.toIntExact((Long) countryMap.get("countryX")));
            country.setCountryY(Math.toIntExact((Long) countryMap.get("countryY")));
            country.setHeight(Math.toIntExact((Long) countryMap.get("height")));
            country.setWidth(Math.toIntExact((Long) countryMap.get("width")));
//            countries.add(countryRef.getPath());

            // TODO 2. toevoegen country aan de lijst countries
            countries.add(country);
        }
        return countries;
    }

    public static List<User> fetchPlayers() throws Exception {
        List<User> players = new ArrayList<>();
        DocumentReference docRef = InitFirebase.getDbInstance().collection(PLAYER_COLLECTION).document("info");
        ApiFuture<DocumentSnapshot> playerFuture = docRef.get();
        DocumentSnapshot playerDocument = playerFuture.get();
        Map<String,Object> playerMap = playerDocument.getData();
        for (String key : playerMap.keySet()) {
            User user = new User();
            Map<String, Object> value = (Map<String,Object>)playerMap.get(key);
            user.setPlayerId(Integer.valueOf(key));
            user.setPlayerName((String)value.get("playerName"));
            String colorText = (String)value.get("color");
            if (colorText == null) {
                user.setColor(Color.GRAY);
            } else {
                user.setColor(Color.valueOf(colorText));
            }
            players.add(user);
        }
        return players;
    }

    public static void addCountries(List<Country> countries) throws IOException, ExecutionException, InterruptedException {
        WriteBatch batch = InitFirebase.getDbInstance().batch();
        for (Country country : countries) {
            DocumentReference docRef = InitFirebase.getDbInstance().collection(COUNTRY_COLLECTION).document(country.getCountryName());
            batch.update(docRef, "countryTroops", 0);
            batch.update(docRef, "countryOwner", null);
            batch.update(docRef, "countryX", country.getCountryX());
            batch.update(docRef, "countryY", country.getCountryY());
            batch.update(docRef, "width", country.getWidth());
            batch.update(docRef, "height", country.getHeight());
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