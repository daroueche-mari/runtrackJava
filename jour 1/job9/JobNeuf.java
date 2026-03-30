package job9;
import java.util.Scanner;

public class JobNeuf {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        // 1. Demander l'âge
        System.out.print("Entrez votre âge : ");
        int age = lecteur.nextInt();

        // 2. Structure de décision
        if (age < 18) {
            // Si la condition (age < 18) est VRAIE
            System.out.println("Vous êtes mineur.");
        } else {
            // Dans TOUS les autres cas (si la condition est FAUSSE)
            System.out.println("Vous êtes majeur.");
        }

        // 3. Fermer le scanner
        lecteur.close();
    }
}