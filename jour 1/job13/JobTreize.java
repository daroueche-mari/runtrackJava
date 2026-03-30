package job13;
import java.util.Scanner;

public class JobTreize {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        // 1. Demander la saisie avec la contrainte (n <= 9)
        System.out.print("Entrez un nombre entier (entre 1 et 9) : ");
        int n = lecteur.nextInt();

        // 2. Vérification de la consigne
        if (n > 9 || n < 1) {
            System.out.println("Erreur : Le nombre doit être compris entre 1 et 9.");
        } else {
            System.out.println("--- Table de multiplication de " + n + " ---");
            
            // 3. Boucle pour afficher de 1 à 10
            for (int i = 1; i <= 10; i++) {
                int resultat = n * i;
                System.out.println(n + " x " + i + " = " + resultat);
            }
        }

        lecteur.close();
    }
}