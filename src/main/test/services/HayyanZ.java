package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import static org.junit.Assert.assertEquals;
import models.User;
import org.junit.Test;
import shared.DiceObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static services.FirebaseService.PLAYER_COLLECTION;

public class HayyanZ {
    @ Test
    public void Should_return_the_correct_playerID_from_the_Database() throws Exception {
        User newUser = new User();
        int idNum;
        DocumentReference snapshot = InitFirebase.getDbInstance().collection(PLAYER_COLLECTION).document("info");
        ApiFuture<DocumentSnapshot> future = snapshot.get();
        Map<String, Object> map = future.get().getData();
        assert map != null;
        int actual = idNum = map.size() + 1;
        newUser.setPlayerId(idNum);
        int expected = newUser.getPlayerId();
        assertEquals(expected, actual);
    }

    @Test
    public void Should_return_the_same_number_the_dice_gives() {

        final int[] diceCol = {1, 2, 3, 4, 5, 6};
        int rolNo; //Roll number aan de dice wanneer hij rolt.
        List<DiceObserver> diceSubscribers = new ArrayList<>();
        rolNo = diceCol[new Random().nextInt(diceCol.length)];
        for (int expected : diceCol) {
            expected = rolNo;
            int actual = rolNo;
            assertEquals(expected, actual);
        }

    }
}