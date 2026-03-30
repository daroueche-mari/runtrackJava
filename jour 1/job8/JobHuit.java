package job8;
import java.util.Scanner;

public class JobHuit {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        // 1. Demander la valeur de n
        System.out.print("Entrez un nombre entier n : ");
        int n = lecteur.nextInt();

        // 2. On utilise 'long' pour la somme car les cubes montent très vite !
        long somme = 0;

        // 3. Boucle de 1 à n
        for (int i = 1; i <= n; i++) {
            // Calcul du cube : i * i * i
            somme = somme + (long) i * i * i;
        }

        // 4. Affichage du résultat
        System.out.println("La somme des " + n + " premiers cubes est : " + somme);

        lecteur.close();
    }
}