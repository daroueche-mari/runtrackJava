package job4;
import java.util.Scanner;

public class ComptageSequentiel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Demande du nombre maximum
        System.out.print("Entrez le nombre maximum à compter : ");
        long max = sc.nextLong(); // Utilisation de long pour pouvoir compter très haut

        // 2. Début de la mesure du temps (en millisecondes)
        long tempsDebut = System.currentTimeMillis();

        // 3. Boucle de comptage
        long compteur = 0;
        for (long i = 1; i <= max; i++) {
            compteur++;
        }

        // 4. Fin de la mesure
        long tempsFin = System.currentTimeMillis();
        long tempsTotal = tempsFin - tempsDebut;

        // 5. Affichage des résultats
        System.out.println("Comptage terminé.");
        System.out.println("Total compté : " + compteur);
        System.out.println("Temps d'exécution : " + tempsTotal + " ms");

        sc.close();
    }
}