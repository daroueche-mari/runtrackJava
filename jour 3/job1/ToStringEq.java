package job1;

public class ToStringEq {

    public static void main(String[] args) {
        System.out.println("Test 1 :");
        Rectangle rect = new Rectangle(12.5, 4.0);
        System.out.println(rect);
        System.out.println();

        System.out.println("Test 2 :");
        // le type de rect1 est RectangleColore
        // l'objet contenu dans rect1 est de type RectangleColore
        RectangleColore rect1 = new RectangleColore(12.5, 4.0, "rouge");
        System.out.println(rect1);
        System.out.println();

        System.out.println("Test 3 :");
        // le type de rect2 est Rectangle
        // l'objet contenu dans rect2 est de type RectangleColore
        Rectangle rect2 = new RectangleColore(25.0/2, 8.0/2, new String("rouge"));
        System.out.println(rect2);
        System.out.println();

        // Comparaisons et résultats attendus
        System.out.println(rect1.equals(rect2)); // 1. 
        System.out.println(rect2.equals(rect1)); // 2. 
        System.out.println(rect1.equals(null));  // 3
        System.out.println(rect.equals(rect1));  // 4. 
        System.out.println(rect1.equals(rect)); // 5.
    }
}
// --- CLASSE PARENTE ---
class Rectangle {
    // Attributs protégés pour être accessibles par la sous-classe RectangleColore
    protected double largeur;
    protected double hauteur;

    // Constructeur de base
    public Rectangle(double largeur, double hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public String toString() {
        // Retourne une chaîne multi-lignes avec des indentations (espaces)
        // pour correspondre exactement à l'affichage console attendu.
        return "Rectangle :\n" +
               "  largeur = " + largeur + "\n" +
               "  hauteur = " + hauteur;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Vérification réflexive : est-ce le même emplacement mémoire ?
        if (this == obj) return true;
        
        // 2. Vérification de type STRICTE : 
        // Si l'objet est nul ou s'il n'est pas EXACTEMENT de la classe Rectangle, on renvoie false.
        // C'est ce qui permet d'avoir "false" quand on compare un Rectangle et un RectangleColore.
        if (obj == null || this.getClass() != obj.getClass()) return false;
        
        // 3. Conversion (Cast) et comparaison des attributs numériques
        Rectangle other = (Rectangle) obj;
        return Double.compare(largeur, other.largeur) == 0 && 
               Double.compare(hauteur, other.hauteur) == 0;
    }
}

// --- CLASSE ENFANT ---
class RectangleColore extends Rectangle {
    private String couleur;

    public RectangleColore(double largeur, double hauteur, String couleur) {
        // Appel au constructeur parent : évite de réinitialiser largeur et hauteur ici.
        super(largeur, hauteur);
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        // Réutilise la méthode toString() du parent pour obtenir les dimensions,
        // puis ajoute simplement la ligne spécifique à la couleur.
        // Cela respecte la consigne "pas de duplication de code".
        return super.toString() + "\n" +
               "  couleur = " + couleur;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Appelle le equals() de Rectangle. 
        // S'il renvoie false (classes différentes ou dimensions différentes), on s'arrête là.
        if (!super.equals(obj)) return false; 
        
        // 2. Si on arrive ici, on sait que obj est un RectangleColore (grâce au getClass du parent)
        // On compare donc l'attribut spécifique : la couleur.
        RectangleColore other = (RectangleColore) obj;
        
        // Utilisation de .equals pour les chaînes de caractères (évite les erreurs de pointeur)
        if (this.couleur == null) return other.couleur == null;
        return this.couleur.equals(other.couleur);
    }
}