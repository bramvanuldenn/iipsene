package models;

import java.util.Hashtable;
import java.util.Scanner;

public class Troops {
    private Hashtable<String, Integer> troopsCollection = new Hashtable<String, Integer>();

    public Hashtable<String, Integer> getTroopsCollection() {
        return troopsCollection;
    }

    public void setTroopsCollection(Hashtable<String, Integer> troopsCollection) {
        this.troopsCollection = troopsCollection;
    }
}
