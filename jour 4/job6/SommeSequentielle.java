package job6;
import java.util.Scanner;

public class SommeSequentielle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Demande de la taille du tableau
        System.out.print("Entrez le nombre d'éléments dans le tableau : ");
        int n = sc.nextInt();

        // 2. Création et remplissage du tableau
        int[] tableau = new int[n];
        System.out.println("Saisie des nombres :");
        for (int i = 0; i < n; i++) {
            System.out.print("Nombre " + (i + 1) + " : ");
            tableau[i] = sc.nextInt();
        }

        // 3. Début de la mesure du temps
        long tempsDebut = System.nanoTime(); // nanoTime est plus précis pour les petits tableaux

        // 4. Calcul de la somme
        long sommeTotale = 0;
        for (int nombre : tableau) {
            sommeTotale += nombre;
        }

        // 5. Fin de la mesure
        long tempsFin = System.nanoTime();
        long tempsTotalMicros = (tempsFin - tempsDebut) / 1000; // Conversion en microsecondes

        // 6. Affichage des résultats
        System.out.println("\n--- Résultats ---");
        System.out.println("Somme totale : " + sommeTotale);
        System.out.println("Temps d'exécution : " + tempsTotalMicros + " µs (microsecondes)");

        sc.close();
    }
}