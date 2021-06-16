package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/* Alle services die wij met de firestore kunnen uitvoeren.
 * Hierbij is gebruikt gemaakt van de "user.playerName" als een parameter zodat wij een user aan de database kunnen toevoegen.
 * met zelf gegenereerd Id voor elke speler.
 * @author Hayyan Mezher.
*/
public class FirebaseService {


    public static boolean addPlayer(String playerName) throws ExecutionException, InterruptedException, NullPointerException, IOException {

        User newUser = new User();

        Integer idNum;
        DocumentReference snapshot = InitFirebase.getDbInstance().collection("players").document("info");
        ApiFuture<DocumentSnapshot> future = snapshot.get();
        Map<String, Object> map = future.get().getData();
        idNum = Objects.requireNonNull(map).size();
        System.out.println(idNum);

        if (idNum <=5) {
            return false;
        }

        newUser.setPlayerId(idNum+1);
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
        if (map != null) {
            return map.containsKey(String.valueOf(newUser.getPlayerId()));
        }
        return false;
    }
}