package job2;
import java.util.Scanner; // On importe l'outil pour lire le clavier

public class JobDeux {
    public static void main(String[] args) {
        // 1. Création du scanner (on l'appelle 'lecteur' pour que ce soit clair)
        Scanner lecteur = new Scanner(System.in);

        // 2. On pose la question
        System.out.println("Quel est ton prénom ?");

        // 3. On récupère ce que l'utilisateur écrit
        String prenom = lecteur.nextLine();

        // 4. On affiche la réponse personnalisée
        System.out.println("Bonjour " + prenom);

        // 5. On ferme le lecteur
        lecteur.close();
    }
}