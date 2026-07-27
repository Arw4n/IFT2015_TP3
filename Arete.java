public class Arete implements Comparable<Arete> {
    private String nom;
    String a; // départ
    private String b; // arrivée
    private int poids;

    // Complexité temporelle: O(1)
    public Arete(String nom, String a, String b, int poids) {
       this.nom = nom;
       this.a = a;
       this.b = b;
       this.poids = poids;
    }

    // Complexité temporelle: O(1)
    public String getNom() {
        return this.nom;
    }
    
    // Complexité temporelle: O(1)
    public String getA() {
        return this.a;
    }

    // Complexité temporelle: O(1)
    public String getB() {
        return this.b;
    }

    // Complexité temporelle: O(1)
    public Integer getPoids() {
        return this.poids;
    }

    // Complexité temporelle: O(1)
    @Override
    public int compareTo(Arete e) {
        int poids = Integer.compare(this.poids, e.poids);
        if (poids != 0) {
            return poids;
        }

        int depart = this.a.compareTo(e.a);
        if (depart != 0) {
            return depart;
        }
        
        return this.b.compareTo(e.b);
    }

    // Complexité temporelle: O(1)
    @Override
    public String toString() {
        return nom + " : " + a + " - " + b + " (poids : " + poids + ")";
    }
    
}
