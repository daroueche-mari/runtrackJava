package job5;
import java.util.Scanner;

public class JobCinq {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        // 1. Saisie des trois nombres
        System.out.println("Entrez le premier nombre :");
        int n1 = lecteur.nextInt();

        System.out.println("Entrez le deuxième nombre :");
        int n2 = lecteur.nextInt();

        System.out.println("Entrez le troisième nombre :");
        int n3 = lecteur.nextInt();

        // 2. Recherche du maximum
        int max;

        if (n1 >= n2 && n1 >= n3) {
            max = n1; // n1 est le plus grand
        } else if (n2 >= n3) {
            max = n2; // n2 est le plus grand
        } else {
            max = n3; // n3 est le plus grand
        }

        // 3. Affichage du résultat
        System.out.println("Le nombre maximum parmi les trois est : " + max);

        lecteur.close();
    }
}