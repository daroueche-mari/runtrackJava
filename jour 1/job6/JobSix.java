package job6;
import java.util.Scanner;

public class JobSix {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);
        
        // Variable 1 : Pour stocker la somme totale
        double somme = 0; 
        
        // Variable 2 : Pour stocker temporairement le nombre saisi
        double nombreSaisi;

        System.out.println("Entrez 5 nombres successifs :");

        // On répète l'opération 5 fois
        for (int i = 0; i < 5; i++) {
            System.out.print("Nombre " + (i + 1) + " : ");
            nombreSaisi = lecteur.nextDouble(); // On utilise la variable 2
            somme = somme + nombreSaisi;        // On ajoute à la variable 1
        }

        // On affiche la moyenne (somme divisée par 5)
        System.out.println("La moyenne est : " + (somme / 5));

        lecteur.close();
    }
}