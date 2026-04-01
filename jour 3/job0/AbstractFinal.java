package job0;
public class AbstractFinal {
    public static void main(String[] args) {
        // Déclaration d'un tableau de type A (Classe abstraite)
        // C'est possible car on crée un tableau, pas un objet de type A.
        A[] tab = new A[3];

        // --- ERREUR 1 : Instanciation d'une classe abstraite ---
        // tab[0] = new A(); 
        // EXPLICATION : On ne peut pas faire "new" sur une classe 'abstract'. 
        // C'est un modèle incomplet. On doit utiliser une sous-classe non-abstraite.
        tab[0] = new B(); // Correction possible en utilisant B ou C

        tab[1] = new B();
        tab[2] = new C();

        // --- ERREUR 2 : Accès à un attribut inconnu du parent ---
        // tab[1].b = 2;
        // EXPLICATION : Le tableau est de type A. Java regarde la classe A et 
        // ne voit pas d'attribut 'b'. Il faut faire un "cast" (transtypage).
        ((B)tab[1]).b = 2; // Correction : on dit à Java que tab[1] est bien un B

        // --- ERREUR 3 : Modification d'une variable 'final' ---
        // ((C)tab[2]).c = 3;
        // EXPLICATION : Dans la classe C, 'c' est 'final'. 
        // Une variable 'final' est une constante, sa valeur ne peut plus changer après l'initialisation.
        System.out.println("Valeur de c : " + ((C)tab[2]).c); // On peut seulement la lire
    }
}

// --- HIÉRARCHIE DES CLASSES ---

abstract class A {
    int a;
}

class B extends A {
    int b;
}

class C extends A {
    // 'final' signifie que la valeur est verrouillée à 1.0
    final double c = 1;
}

// Une classe 'abstract' peut contenir des méthodes avec ou sans corps
abstract class D extends A {
    double d;

    // Méthode concrète (avec corps {}) : OK
    int operation(int a) {
        return (a * 2);
    }

    // --- ERREUR 4 (Syntaxe) : Méthode abstraite avec un corps ---
    // abstract int calcul(int b) { } 
    // EXPLICATION : Une méthode 'abstract' définit une signature mais n'a pas de corps.
    // Elle doit obligatoirement se terminer par un ";" et non par des "{ }".
    abstract int calcul(int b); // Correction : point-virgule à la place des accolades

    abstract int machin();
}