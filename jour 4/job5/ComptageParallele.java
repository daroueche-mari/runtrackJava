package job5;
import java.util.Scanner;

public class ComptageParallele {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le nombre maximum à compter : ");
        long max = sc.nextLong();

        long tempsDebut = System.currentTimeMillis();

        long milieu = max / 2;

        // Création des deux compteurs
        CompteurThread c1 = new CompteurThread(1, milieu);
        CompteurThread c2 = new CompteurThread(milieu + 1, max);

        // Création des threads
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        // Démarrage des threads
        t1.start();
        t2.start();

        try {
            // On attend que les deux threads aient fini leur boucle
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Combinaison des résultats
        long compteTotal = c1.getSousTotal() + c2.getSousTotal();

        long tempsFin = System.currentTimeMillis();

        System.out.println("Comptage terminé.");
        System.out.println("Total compte : " + compteTotal);
        System.out.println("Temps d'exécution : " + (tempsFin - tempsDebut) + " ms");

        sc.close();
    }
}

class CompteurThread implements Runnable {
    private long debut, fin;
    private long sousTotal = 0;

    public CompteurThread(long debut, long fin) {
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {
        for (long i = debut; i <= fin; i++) {
            sousTotal++;
        }
    }

    public long getSousTotal() {
        return sousTotal;
    }
}