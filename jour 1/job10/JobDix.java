package job10;
public class JobDix {
    public static void main(String[] args) {
        // 1. On définit le nombre cible
        int n = 8;
        
        // 2. On initialise le résultat à 1 (et non 0, car c'est une multiplication !)
        long resultat = 1;

        // 3. Boucle pour multiplier les nombres de 1 à 8
        for (int i = 1; i <= n; i++) {
            resultat = resultat * i;
        }

        // 4. Affichage du résultat final
        System.out.println("La factorielle de " + n + " est : " + resultat);
    }
}