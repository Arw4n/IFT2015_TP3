public class Arete implements Comparable<Arete> {
    private String nom;
    String a; // départ
    private String b; // arrivée
    private int poids;

    public Arete(String nom, String a, String b, int poids) {
       this.nom = nom;
       this.a = a;
       this.b = b;
       this.poids = poids;
    }

    public String getNom() {
        return this.nom;
    }
    
    public String getA() {
        return this.a;
    }

    public String getB() {
        return this.b;
    }

    public Integer getPoids() {
        return this.poids;
    }

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

    @Override
    public String toString() {
        return nom + " : " + a + " - " + b + " (poids : " + poids + ")";
    }
    
}
