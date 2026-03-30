package job14;
import java.util.Scanner;

public class JobQuatorze {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        // 1. Demander le nombre
        System.out.print("Entrez un nombre entier à inverser : ");
        int nombre = lecteur.nextInt();
        
        int nombreOrigine = nombre; // On garde une copie pour l'affichage final
        int inverse = 0;

        // 2. Boucle d'inversion
        while (nombre != 0) {
            int chiffre = nombre % 10;      // On récupère le dernier chiffre
            inverse = inverse * 10 + chiffre; // On le place dans le résultat
            nombre = nombre / 10;           // On retire le dernier chiffre du nombre
        }

        // 3. Affichage
        System.out.println("Le nombre " + nombreOrigine + " inversé donne : " + inverse);

        lecteur.close();
    }
}