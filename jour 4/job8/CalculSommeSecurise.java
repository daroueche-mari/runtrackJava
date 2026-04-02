package job8;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CalculSommeSecurise {
    public static void main(String[] args) {
        int nbTotal = 10_000_000;
        int nbTaches = 100; 
        int tailleTranche = nbTotal / nbTaches;
        
        // Utilisation d'un AtomicLong pour éviter les conflits d'accès entre threads
        AtomicLong sommeTotale = new AtomicLong(0);

        // PROTECTION : On limite le nombre de threads réels à 8
        ExecutorService executor = Executors.newFixedThreadPool(8);

        long debut = System.currentTimeMillis();

        for (int i = 0; i < nbTaches; i++) {
            final int start = (i * tailleTranche) + 1;
            final int end = (i + 1) * tailleTranche;

            executor.execute(() -> {
                long sousTotal = 0;
                for (int j = start; j <= end; j++) {
                    sousTotal += j;
                }
                sommeTotale.addAndGet(sousTotal);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long fin = System.currentTimeMillis();

        System.out.println("--- Résultat du calcul ---");
        System.out.println("Somme de 1 à 10M : " + sommeTotale.get());
        System.out.println("Temps d'exécution : " + (fin - debut) + " ms");
    }
}

/* * ============================================================================
 * RÉPONSE AUX RECHERCHES : LA FORK BOMB
 * ============================================================================
 * * QU'EST-CE QU'UNE FORK BOMB ?
 * Une "Fork Bomb" est une forme d'attaque par déni de service (DoS) où un 
 * programme crée des processus (ou des threads) de manière récursive et 
 * infinie. En Java, faire "new Thread().start()" à l'intérieur d'une boucle 
 * sans limite peut saturer la mémoire (RAM) et le processeur (CPU). 
 * L'ordinateur finit par geler car il n'a plus de ressources pour le système.
 *
 * COMMENT S'EN PRÉMUNIR DANS CE PROGRAMME ?
 * 1. Utilisation d'un Thread Pool (ExecutorService) : Au lieu de créer 100 
 * ou 1000 threads, on définit une limite fixe (ici 8). Les tâches sont 
 * mises en file d'attente et traitées par les threads disponibles.
 * * 2. Découpage logique : On divise le travail en tranches (nbTaches) plutôt 
 * que de créer un thread par nombre à additionner.
 * * 3. Atomicité : L'utilisation de 'AtomicLong' permet d'additionner les 
 * résultats en toute sécurité sans bloquer tous les threads avec des 
 * verrous (synchronized) trop lourds.
 * ============================================================================
 */