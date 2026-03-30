package job3;
public class JobTrois {
    public static void main(String[] args) {
        // 1. Déclaration et création d'un tableau de 10 entiers
        int[] T = new int[10];

        // 2. Remplissage du tableau avec les entiers de 0 à 9
        for (int i = 0; i < T.length; i++) {
            T[i] = i; // On stocke l'indice i à la position i
        }

        // 3. Affichage des valeurs demandées
        System.out.println("T[0] = " + T[0]);
        System.out.println("T[1] = " + T[1]);
        System.out.println("T[5] = " + T[5]);
        System.out.println("T[9] = " + T[9]);
        
        // Tentative pour T[10] (Attention !)
        // System.out.println("T[10] = " + T[10]); 
        // POURQUOI T[10] N'EXISTE PAS :
        // 1. Le tableau a été créé avec une taille de 10 (new int[10]).
        // 2. En Java, les indices commencent à 0. Les cases sont donc : 0, 1, 2, 3, 4, 5, 6, 7, 8 et 9.
        // 3. T[9] est la 10ème et DERNIÈRE case. 
        // 4. T[10] correspondrait à une 11ème case qui n'a pas été réservée en mémoire.
        // 5. Tenter d'y accéder provoque une erreur "ArrayIndexOutOfBoundsException".
    }
}