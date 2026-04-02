package job7;
import java.util.Scanner;

public class SommeParallele {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Saisie des données
        System.out.print("Entrez la taille du tableau : ");
        int n = sc.nextInt();
        int[] tableau = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Élément " + (i + 1) + " : ");
            tableau[i] = sc.nextInt();
        }

        // 2. Découpage du travail
        int milieu = n / 2;
        long tempsDebut = System.nanoTime();

        // 3. Création des objets "Calculateur"
        SommeThread calculateur1 = new SommeThread(tableau, 0, milieu);
        SommeThread calculateur2 = new SommeThread(tableau, milieu, n);

        // 4. Création et lancement des Threads
        Thread t1 = new Thread(calculateur1);
        Thread t2 = new Thread(calculateur2);

        t1.start();
        t2.start();

        try {
            // 5. Attente de la fin des calculs
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 6. Fusion des résultats
        long sommeTotale = calculateur1.getSommePartielle() + calculateur2.getSommePartielle();
        long tempsFin = System.nanoTime();

        // 7. Affichage
        System.out.println("\nSomme totale : " + sommeTotale);
        System.out.println("Temps d'exécution : " + (tempsFin - tempsDebut) / 1000 + " µs");

        sc.close();
    }
}

class SommeThread implements Runnable {
    private int[] tableau;
    private int debut, fin;
    private long sommePartielle = 0;

    public SommeThread(int[] tableau, int debut, int fin) {
        this.tableau = tableau;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {
        for (int i = debut; i < fin; i++) {
            sommePartielle += tableau[i];
        }
    }

    public long getSommePartielle() {
        return sommePartielle;
    }
}