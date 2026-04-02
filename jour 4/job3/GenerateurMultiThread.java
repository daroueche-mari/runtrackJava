package job3;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class GenerateurMultiThread {
    
    // 1. On définit la source de vérité ICI
    public static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez la longueur de la chaîne : ");
        int longueur = sc.nextInt();

        long tempsDebut = System.currentTimeMillis();
        int milieu = longueur / 2;

        Thread t1 = new Thread(new EcrivainFichier(0, milieu, "output.txt"));
        Thread t2 = new Thread(new EcrivainFichier(milieu, longueur, "output.txt"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long tempsFin = System.currentTimeMillis();
        System.out.println("Fichier généré avec succès via 2 threads.");
        System.out.println("Temps d'exécution global : " + (tempsFin - tempsDebut) + " ms");
        
        sc.close();
    }
}

class EcrivainFichier implements Runnable {
    private int debut, fin;
    private String nomFichier;

    public EcrivainFichier(int debut, int fin, String nomFichier) {
        this.debut = debut;
        this.fin = fin;
        this.nomFichier = nomFichier;
    }

    @Override
    public void run() {
        Random random = new Random();
        try (RandomAccessFile raf = new RandomAccessFile(nomFichier, "rw")) {
            raf.seek(debut);
            
            StringBuilder sb = new StringBuilder();
            for (int i = debut; i < fin; i++) {
                // 2. ON UTILISE LA CONSTANTE DE LA CLASSE PRINCIPALE
                // On y accède via NomDeLaClasse.NomDeLaConstante
                int index = random.nextInt(GenerateurMultiThread.CHARSET.length());
                sb.append(GenerateurMultiThread.CHARSET.charAt(index));
            }
            raf.writeBytes(sb.toString());
            
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}