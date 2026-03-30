package job1;
import java.util.Scanner; // 1. On importe l'outil Scanner

public class JobUn {
    public static void main(String[] args) {
        // 2. On crée un objet Scanner qui lit l'entrée standard (System.in)
        Scanner lecteur = new Scanner(System.in);

        System.out.println("Comment t'appelles-tu ?");

        // 3. On récupère la saisie (une ligne de texte)
        String nom = lecteur.nextLine();

        // 4. On affiche le résultat
        System.out.println("Enchanté, " + nom + " !");

        // 5. On ferme le scanner (bonne pratique)
        lecteur.close();
    }
}