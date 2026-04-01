package job2;
// --- CLASSE DE BASE ABSTRAITE ---
abstract class Carte {
    protected int cout;

    public Carte(int cout) {
        this.cout = cout;
    }

    public abstract void afficher();
}

// --- LES TERRAINS ---
class Terrain extends Carte {
    private char couleur;

    public Terrain(char couleur) {
        super(0); // Le cout d'un terrain est toujours 0
        this.couleur = couleur;
        System.out.println("Une nouvelle carte de terrain a ete creee.");
    }

    @Override
    public void afficher() {
        System.out.println("Terrain (" + couleur + "), cout : " + cout);
    }
}

// --- LES CRÉATURES ---
class Creature extends Carte {
    private String nom;
    private int degats;
    private int pointsDeVie;

    public Creature(int cout, String nom, int degats, int pv) {
        super(cout);
        this.nom = nom;
        this.degats = degats;
        this.pointsDeVie = pv;
        System.out.println("Une nouvelle creature a ete creee.");
    }

    @Override
    public void afficher() {
        System.out.println("Creature " + nom + " [" + degats + "/" + pointsDeVie + "], cout : " + cout);
    }
}

// --- LES SORTILÈGES ---
class Sortilege extends Carte {
    private String nom;
    private String explication;

    public Sortilege(int cout, String nom, String explication) {
        super(cout);
        this.nom = nom;
        this.explication = explication;
        System.out.println("Un nouveau sortilege a ete cree.");
    }

    @Override
    public void afficher() {
        System.out.println("Sortilege " + nom + " (" + explication + "), cout : " + cout);
    }
}

// --- LA CLASSE JEU ---
class Jeu {
    private Carte[] collection;
    private int nbCartes;

    public Jeu() {
        this.collection = new Carte[10]; 
        this.nbCartes = 0;
    }

    public void piocher(Carte c) {
        if (nbCartes < 10) {
            collection[nbCartes] = c;
            nbCartes++;
        } else {
            System.out.println("Le jeu est plein !");
        }
    }

    public void jouer() {
        System.out.println("On joue les cartes :");
        for (int i = 0; i < nbCartes; i++) {
            if (collection[i] != null) {
                collection[i].afficher();
                collection[i] = null; // La carte est jouée (devient null)
            }
        }
    }

    public void afficher() {
        System.out.println("Contenu du jeu actuel :");
        for (int i = 0; i < nbCartes; i++) {
            if (collection[i] != null) {
                collection[i].afficher();
            }
        }
    }
}

// --- PROGRAMME PRINCIPAL ---
public class Magic {
    public static void main(String[] args) {
        Jeu monJeu = new Jeu();

        monJeu.piocher(new Terrain('B'));
        monJeu.piocher(new Creature(3, "Dragon", 5, 4));
        monJeu.piocher(new Sortilege(2, "Boule de feu", "Inflige 3 degats"));
        monJeu.piocher(new Terrain('v'));

        System.out.println("---");
        monJeu.afficher();
        
        System.out.println("---");
        monJeu.jouer();
    }
}