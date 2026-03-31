package job3;
public class FiguresGeometriques {
    public static void main(String[] args) {
        // Test du Rectangle (Centre x=0, y=0, largeur=5, longueur=10)
        Rectangle rect = new Rectangle(0.0, 0.0, 5.0, 10.0);
        rect.affiche();
        System.out.println("Surface du rectangle : " + rect.surface());

        // Test du Cercle (Centre x=2, y=2, rayon=5)
        Cercle monCercle = new Cercle(2.0, 2.0, 5.0);
        monCercle.affiche();
        System.out.println("Surface du cercle : " + monCercle.surface());

        // Test du RectangleColore (Rouge, Centre x=1, y=1, larg=4, long=8)
        RectangleColore myrectcolore = new RectangleColore(0xFF0000, 1.0, 1.0, 4.0, 8.0);
        myrectcolore.affiche();
        System.out.println("Surface du rectangle coloré : " + myrectcolore.surface());
        System.out.println("Couleur : #" + Integer.toHexString(myrectcolore.getcolor()).toUpperCase()); 
    }
}

class Figure {
    protected double x;
    protected double y;

    public Figure(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void affiche() {
        System.out.println("Centre de la figure : (" + x + ", " + y + ")");
    }

    public void setCentre(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Rectangle extends Figure {
    protected double largeur;
    protected double longueur;

    public Rectangle(double x, double y, double largeur, double longueur) {
        super(x, y); 
        this.largeur = largeur;
        this.longueur = longueur;
    }

    public double surface() {
        return this.largeur * this.longueur;
    }

    public void affiche() {
        super.affiche();
        System.out.println("Type : Rectangle, Dimensions : " + largeur + " x " + longueur);
    }
}

class RectangleColore extends Rectangle {
    protected Integer color;
    
    public RectangleColore(Integer color, double x, double y, double largeur, double longueur) {
        super(x, y, largeur, longueur); 
        this.color = color;
    }

    public void setcolor(Integer color) { this.color = color; }
    public Integer getcolor() { return this.color; }

    public void affiche() {
        super.affiche();
        System.out.println("Couleur : #" + Integer.toHexString(color).toUpperCase());
    }
}

class Cercle extends Figure {
    protected double rayon;

    public Cercle(double x, double y, double rayon) {
        super(x, y); 
        this.rayon = rayon;
    }

    public double surface() {
        return Math.PI * (rayon * rayon);
    }

    public boolean estInterieur(double px, double py) {
        double distance = Math.sqrt(Math.pow(px - x, 2) + Math.pow(py - y, 2));
        return distance <= rayon;
    }

    public void affiche() {
        super.affiche();
        System.out.println("Type : Cercle, Rayon : " + rayon);
    }
}