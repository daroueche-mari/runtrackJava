package job7;
public class JobSept {
    public static void main(String[] args) {
        // 1. On prépare une variable pour stocker le cumul
        int somme = 0;

        // 2. On fait défiler les nombres de 1 à 100
        for (int i = 1; i <= 100; i++) {
            somme = somme + i; // On ajoute le nombre actuel à la somme
        }

        // 3. On affiche le résultat final
        System.out.println("La somme des 100 premiers entiers est : " + somme);
    }
}