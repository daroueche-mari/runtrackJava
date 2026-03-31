package job4;
import java.time.LocalDate;

public class GestionVehicules {
    protected String marque;
    protected LocalDate dateAchat;
    protected int prixAchat;
    protected double prixCourant; // Pour stocker le résultat du calcul

    public GestionVehicules(String marque, LocalDate dateAchat, int prixAchat) {
        this.marque = marque;
        this.dateAchat = dateAchat;
        this.prixAchat = prixAchat;
        this.prixCourant = prixAchat; // Initialisé au prix d'achat
    }

    // Méthode de base : -1% par an
    public void calculePrix(int anneeActuelle) {
        int age = anneeActuelle - dateAchat.getYear();
        double decote = prixAchat * (0.01 * age);
        this.prixCourant = Math.max(0, prixAchat - decote);
    }

    public void affiche() {
        System.out.println("Marque : " + marque);
        System.out.println("Date d'achat : " + dateAchat);
        System.out.println("Prix d'achat : " + prixAchat + "€");
        System.out.printf("Prix courant : %.2f€%n", prixCourant);
    }

    public static void main(String[] args) {
        int anneeRef = 2024;

        System.out.println("=== TEST VOITURE (Renault) ===");
        Voiture v = new Voiture("Renault", LocalDate.of(2020, 5, 20), 20000, 1.6, 5, 110, 25000);
        v.calculePrix(anneeRef);
        v.affiche();

        System.out.println("\n=== TEST AVION (Hélices) ===");
        Avion a = new Avion("Cessna", LocalDate.of(2015, 10, 12), 100000, "HELICES", 250);
        a.calculePrix(anneeRef);
        a.affiche();
    }
}

// --- CLASSE VOITURE ---
class Voiture extends GestionVehicules {
    private double cylindree;
    private int nbPortes;
    private int puissance;
    private double kilometrage;

    public Voiture(String marque, LocalDate dateAchat, int prixAchat, 
                   double cylindree, int nbPortes, int puissance, double kilometrage) {
        super(marque, dateAchat, prixAchat);
        this.cylindree = cylindree;
        this.nbPortes = nbPortes;
        this.puissance = puissance;
        this.kilometrage = kilometrage;
    }
    public void affiche() {
        super.affiche(); // Affiche marque, date, prix
        // En utilisant ces variables ici, les warnings disparaissent :
        System.out.println("Cylindrée : " + cylindree);
        System.out.println("Nombre de portes : " + nbPortes);
        System.out.println("Puissance : " + puissance);
        System.out.println("Kilométrage : " + kilometrage);
}

    public void calculePrix(int anneeActuelle) {
        double decoteTotal = 0;
        
        // 1. 2% par année depuis l'achat
        int age = anneeActuelle - dateAchat.getYear();
        decoteTotal += (0.02 * age);

        // 2. 5% par tranche de 10 000 km (arrondi)
        int tranchesKm = (int) Math.round(kilometrage / 10000.0);
        decoteTotal += (0.05 * tranchesKm);

        // 3. Malus marques (-10% donc +10% de décote)
        if (marque.equalsIgnoreCase("Renault") || marque.equalsIgnoreCase("Fiat")) {
            decoteTotal += 0.10;
        }

        // 4. Bonus marques (+20% donc -20% de décote)
        if (marque.equalsIgnoreCase("Ferrari") || marque.equalsIgnoreCase("Porsche")) {
            decoteTotal -= 0.20;
        }

        this.prixCourant = prixAchat * (1 - decoteTotal);
        if (this.prixCourant < 0) this.prixCourant = 0;
    }
}

// --- CLASSE AVION ---
class Avion extends GestionVehicules {
    private String typeMoteur;
    private int heuresVol;

    public Avion(String marque, LocalDate dateAchat, int prixAchat, 
                 String typeMoteur, int heuresVol) {
        super(marque, dateAchat, prixAchat);
        this.typeMoteur = typeMoteur;
        this.heuresVol = heuresVol;
    }

    public void calculePrix(int anneeActuelle) {
        double decote = 0;
        
        if (typeMoteur.equalsIgnoreCase("HELICES")) {
            // 10% par tranche de 100h
            decote = 0.10 * (heuresVol / 100);
        } else {
            // 10% par tranche de 1000h pour les autres
            decote = 0.10 * (heuresVol / 1000);
        }

        this.prixCourant = Math.max(0, prixAchat * (1 - decote));
    }
}