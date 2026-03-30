package job4;
import java.util.Scanner; // Importation de l'outil de saisie

public class JobQuatre {
    public static void main(String[] args) {
        // 1. Création du scanner pour lire le clavier
        Scanner lecteur = new Scanner(System.in);

        // 2. Demander un nombre à l'utilisateur
        System.out.println("Entrez un nombre entier pour calculer son carré :");
        
        // 3. Récupérer l'entier saisi
        int nombre = lecteur.nextInt();

        // 4. Calculer le carré (un nombre multiplié par lui-même)
        int resultat = nombre * nombre;

        // 5. Afficher le résultat
        System.out.println("Le carré de " + nombre + " est : " + resultat);

        // 6. Fermer le scanner
        lecteur.close();
    }
}