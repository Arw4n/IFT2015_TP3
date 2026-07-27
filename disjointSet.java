// Nom: Ryan Ramaherison Mac Way Kit - Matricule: 2030 6738
// Nom : Arnaud Mehrabi - Matricule : 20302443

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class disjointSet {
    private Map<String, String> parent = new HashMap<>();
    private Map<String, Integer> rank = new HashMap<>();

    // Complexité temporelle : O(|V|)
    public void creer_ensemble(ArrayList<String> x) {
        for(String i : x) {
            parent.put(i,i);
            rank.put(i,0);
        }
    }

    // Complexité temporelle : O(1)
    public void lier(String x, String y) {
        if(rank.get(x)>rank.get(y)) {
            parent.put(y,x);
        } else {
            parent.put(x,y);
        }

        if(rank.get(x)==rank.get(y)) {
            rank.put(y,rank.get(y)+1);
        }
    }

    // Complexité temporelle : O(1)
    public String trouver(String x) {
        if(x!=parent.get(x)) {
            parent.put(x,trouver(parent.get(x)));
        }
        return parent.get(x);
    }

    // Complexité temporelle : O(1)
    public void union(String x, String y) {
        if(trouver(x)!=trouver(y)) {
            lier(trouver(x),trouver(y));
        }
    }
}
