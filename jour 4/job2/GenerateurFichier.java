package job2;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateurFichier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Demande de la longueur
        System.out.print("Entrez la longueur de la chaine a generer : ");
        int longueur = sc.nextInt();
        
        // Début de la mesure du temps
        long tempsDebut = System.currentTimeMillis();

        // 2. Génération de la chaîne aléatoire
        // On utilise StringBuilder pour les performances (bien plus rapide que String + String)
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(longueur);
        Random random = new Random();

        for (int i = 0; i < longueur; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }

        // 3. Écriture dans le fichier output.txt
        // Utilisation de BufferedWriter pour optimiser l'écriture sur le disque
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(sb.toString());
            System.out.println("\nFichier 'output.txt' genere avec succes.");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'ecriture du fichier : " + e.getMessage());
        }

        // 4. Fin de la mesure et affichage
        long tempsFin = System.currentTimeMillis();
        long tempsTotal = tempsFin - tempsDebut;

        System.out.println("Temps d'execution global : " + tempsTotal + " ms");
        
        sc.close();
    }
}