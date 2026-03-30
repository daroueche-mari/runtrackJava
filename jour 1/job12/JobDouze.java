package job12;
import java.util.Scanner;

public class JobDouze {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        // 1. Demander la valeur de n
        System.out.print("Entrez un nombre entier n : ");
        int n = lecteur.nextInt();

        System.out.println("Les nombres pairs inférieurs ou égaux à " + n + " sont :");

        // 2. Boucle de 0 jusqu'à n
        for (int i = 0; i <= n; i++) {
            // 3. Vérifier si le nombre est pair
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }

        lecteur.close();
    }
}