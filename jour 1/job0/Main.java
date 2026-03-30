package job0;
public class Main {
    public static void main(String[] args) {
        // 1. Déclaration des variables
        char monChar = 'A';                 // Un seul caractère entre simples quotes
        String monString = "Java 8";        // Texte entre doubles quotes
        int monInt = 42;                    // Nombre entier
        long monLong = 10000000000L;        // Entier long (nécessite le 'L' à la fin)
        float monFloat = 3.14f;             // Nombre à virgule (nécessite le 'f' à la fin)
        boolean monBoolean = true;          // Vrai ou Faux

        // 2. Affichage des valeurs
        System.out.println("Char: " + monChar);
        System.out.println("String: " + monString);
        System.out.println("Int: " + monInt);
        System.out.println("Long: " + monLong);
        System.out.println("Float: " + monFloat);
        System.out.println("Boolean: " + monBoolean);

        // 3. Le cas de TOTO (Casting)
        // On ne peut pas mettre 3.817 directement dans un int, 
        // il faut "caster" (forcer) la valeur.
        int TOTO = (int) 3.817; 
        
        System.out.println("Valeur de TOTO : " + TOTO);
    }
}