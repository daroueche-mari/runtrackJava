package job11;
import java.util.Scanner;

public class JobOnze {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        // 1. Demander la valeur de n
        System.out.print("Entrez un nombre entier pour calculer sa factorielle : ");
        int n = lecteur.nextInt();

        // 2. Initialisation du résultat (type long pour éviter les dépassements)
        long resultat = 1;

        // 3. Cas particulier : la factorielle de 0 est 1
        if (n < 0) {
            System.out.println("Erreur : La factorielle n'est pas définie pour les nombres négatifs.");
        } else {
            // Boucle de calcul de 1 à n
            for (int i = 1; i <= n; i++) {
                resultat = resultat * i;
            }

            // 4. Affichage du résultat
            System.out.println(n + "! = " + resultat);
        }

        lecteur.close();
    }
}