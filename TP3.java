import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class TP3 {
    /*
    Input : 
        fichier txt
        par ligne : rue0 a b : rue, site1, site2
        sites forment un graphe connexe étiqueté
            Noeuds : sites
            arêtes : segments de rue
     
    But : créer un ARM des sites d'un quartier en utilisant Kruskal
        Si deux arêtes de même poids : traitées dans l'ordre alphanumérique des noeuds de départ
        Si l'ordre est aussi égal, ordre alphanumérique des noeuds d'arrivée
    
    
    
    */

    public ArrayList<Arete> Kruskal(ArrayList<String> V, ArrayList<Arete> E) {
        disjointSet C = new disjointSet();
        PriorityQueue<Arete> Q = new PriorityQueue<>();
        ArrayList<Arete> F = new ArrayList<>();
        
        C.creer_ensemble(V);
        for(Arete i : E) {
            Q.add(i);
        }
        
        while(F.size()<V.size()-1) {
            Arete e = Q.poll();
            if(!C.trouver(e.getA()).equals(C.trouver(e.getB()))) {
                F.add(e);
                C.union(e.getA(), e.getB());
            }
        }
        
        return F;
    }
    
    public void main(String[] args) {
        try {
            BufferedReader carte = new BufferedReader(new FileReader(args[0]));
            BufferedWriter arm = new BufferedWriter(new FileWriter(args[1]));

            ArrayList<String> noeuds = new ArrayList<>();
            ArrayList<Arete> aretes = new ArrayList<>();
            
            
            String ligne = carte.readLine();

            if(ligne==null) { // si fichier vide. 
                carte.close();
                arm.close();
                return;
            }

            while(ligne!=null) {
                if(ligne.trim().isEmpty()) { // saut des lignes vides du fichier
                    ligne = carte.readLine();
                    continue;
                }                
                
                while(!ligne.equals("---")) { // Initialisation de la liste des noeuds
                    String[] infos = ligne.trim().split("\\s+"); // découpage des info de la ligne courante
                    noeuds.add(infos[0]);
                    ligne = carte.readLine();
                }
                ligne = carte.readLine();

                while(!ligne.equals("---")) { // Initialisation de la liste des arêtes
                    String[] infos = ligne.trim().split("\\s+"); // découpage des info de la ligne courante
                    aretes.add(new Arete(infos[0],infos[2],infos[3],Integer.parseInt(infos[4])));
                    ligne = carte.readLine();
                }
                
                ligne = carte.readLine();
            }
            carte.close();

            // Algorithme de Kruskal
            ArrayList<Arete> ARM = Kruskal(noeuds, aretes);
            ARM.sort(Comparator.comparing(Arete::getA).thenComparing(Arete::getB));
            int poidsTotal = 0;

            for(String i : noeuds) { // Affichage des noeuds
                arm.write(i);
                arm.newLine();
            }

            for(Arete e : ARM) {
                poidsTotal+=e.getPoids();

                arm.write(e.getNom() + " " + e.getA() + " " + e.getB() + " " + Integer.toString(e.getPoids()));
                arm.newLine();
            }

            arm.write("---");
            arm.newLine();
            arm.write(Integer.toString(poidsTotal));

            arm.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur (FileNotFoundException): " + e.getMessage());
         } catch (IOException f) {
            System.out.println("Erreur (IOException): " + f.getMessage());
         }
    }
}
