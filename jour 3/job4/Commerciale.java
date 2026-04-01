package job4;
import java.util.Vector;
import java.util.Scanner;

public class Commerciale {
    private Vector<Article> articles = new Vector<>();
    private Vector<Client> clients = new Vector<>();
    private Vector<Commande> commandes = new Vector<>();
    private Vector<Ligne> lignes = new Vector<>();

    // --- MÉTHODES DE GESTION ---
    public void ajouterArticle(Article a) { articles.add(a); }
    public void ajouterClient(Client c) { clients.add(c); }
    public void passerCommande(Commande c) { commandes.add(c); }

    // Utilisation du vecteur Ligne pour lier articles et commandes
    public void ajouterLigne(Ligne l) { lignes.add(l); }

    public void supprimerArticle(Article a) { articles.remove(a); }
    public void supprimerClient(Client c) { clients.remove(c); }
    
    public void annulerCommande(Commande c) { 
        // Supprimer d'abord les lignes associées à cette commande
        lignes.removeIf(l -> l.getCommande().equals(c));
        commandes.remove(c); 
    }

    // --- MÉTHODES DE RECHERCHE ---
    public Article trouverArticle(String ref) {
        for (Article a : articles) {
            if (a.getReference().equals(ref)) return a;
        }
        return null;
    }

    public Client trouverClient(int id) {
        for (Client c : clients) {
            if (c.getIdentite() == id) return c;
        }
        return null;
    }

    public Commande trouverCommande(int num) {
        for (Commande c : commandes) {
            if (c.getNumeroCommande() == num) return c;
        }
        return null;
    }

    // --- PROGRAMME PRINCIPAL ---
    public static void main(String[] args) {
        Commerciale gestion = new Commerciale();
        Scanner sc = new Scanner(System.in);
        int choix = -1;

        while (choix != 0) {
            System.out.println("\n----------Gestion commerciale----------");
            System.out.println("1) Ajouter un article");
            System.out.println("2) Supprimer un article");
            System.out.println("3) Ajouter un client");
            System.out.println("4) Supprimer un client");
            System.out.println("5) Passer une commande");
            System.out.println("6) Annuler une commande");
            System.out.println("0) Quitter");
            System.out.print("\nEntrer un choix : ");
            
            choix = sc.nextInt();
            sc.nextLine(); 

            switch (choix) {
                case 1:
                    System.out.print("Référence : "); String ref = sc.nextLine();
                    System.out.print("Désignation : "); String des = sc.nextLine();
                    System.out.print("Prix : "); double pu = sc.nextDouble();
                    System.out.print("Stock : "); int qteS = sc.nextInt();
                    gestion.ajouterArticle(new Article(ref, des, pu, qteS));
                    System.out.println("Article ajouté.");
                    break;

                case 2:
                    System.out.print("Référence de l'article à supprimer : ");
                    Article artSup = gestion.trouverArticle(sc.nextLine());
                    if (artSup != null) {
                        gestion.supprimerArticle(artSup);
                        System.out.println("Article supprimé.");
                    } else System.out.println("Article introuvable.");
                    break;

                case 3:
                    System.out.print("ID Client : "); int idC = sc.nextInt(); sc.nextLine();
                    System.out.print("Nom Social : "); String nom = sc.nextLine();
                    System.out.print("Adresse : "); String adr = sc.nextLine();
                    System.out.print("Chiffre d'affaire : "); double ca = sc.nextDouble();
                    gestion.ajouterClient(new Client(idC, nom, adr, ca));
                    System.out.println("Client ajouté.");
                    break;

                case 4:
                    System.out.print("ID du client à supprimer : ");
                    Client cliSup = gestion.trouverClient(sc.nextInt());
                    if (cliSup != null) {
                        gestion.supprimerClient(cliSup);
                        System.out.println("Client supprimé.");
                    } else System.out.println("Client introuvable.");
                    break;

                case 5:
                    System.out.print("N° Commande : "); int numCmd = sc.nextInt(); sc.nextLine();
                    System.out.print("Date (JJ/MM/AAAA) : "); String date = sc.nextLine();
                    System.out.print("ID Client : "); 
                    Client cliCmd = gestion.trouverClient(sc.nextInt());
                    
                    if (cliCmd != null) {
                        Commande nouvelleCmd = new Commande(numCmd, date, cliCmd);
                        gestion.passerCommande(nouvelleCmd);
                        
                        // Ajout d'une ligne de commande pour utiliser le vecteur Ligne
                        sc.nextLine();
                        System.out.print("Référence article à commander : ");
                        Article artCmd = gestion.trouverArticle(sc.nextLine());
                        if (artCmd != null) {
                            System.out.print("Quantité : ");
                            int qteC = sc.nextInt();
                            gestion.ajouterLigne(new Ligne(nouvelleCmd, artCmd, qteC));
                            System.out.println("Commande et ligne enregistrées.");
                        }
                    } else System.out.println("Client inexistant.");
                    break;

                case 6:
                    System.out.print("N° de commande à annuler : ");
                    Commande cmdSup = gestion.trouverCommande(sc.nextInt());
                    if (cmdSup != null) {
                        gestion.annulerCommande(cmdSup);
                        System.out.println("Commande annulée.");
                    } else System.out.println("Commande introuvable.");
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
        sc.close();
    }
}

// --- CLASSES DE DONNÉES ---

abstract class Personne {
    protected int identite;
    protected String nomSocial;
    protected String adresse;

    public Personne(int ident, String nomSocial, String adresse) {
        this.identite = ident;
        this.nomSocial = nomSocial;
        this.adresse = adresse;
    }

    public int getIdentite() { return identite; }
    public void setIdentite(int id) { this.identite = id; }
    public String getNomSocial() { return nomSocial; }
    public void setNomSocial(String n) { this.nomSocial = n; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String a) { this.adresse = a; }

    public void affiche() {
        System.out.print("ID: " + identite + ", Nom: " + nomSocial + ", Adresse: " + adresse);
    }
}

class Client extends Personne {
    private double chiffreAffaire;

    public Client(int ident, String nomSocial, String adresse, double chiffreAffaire) {
        super(ident, nomSocial, adresse);
        this.chiffreAffaire = chiffreAffaire;
    }

    public double getChiffreAffaire() { return chiffreAffaire; }
    public void setChiffreAffaire(double ca) { this.chiffreAffaire = ca; }

    @Override
    public void affiche() {
        super.affiche();
        System.out.println(", CA: " + chiffreAffaire);
    }
}

class Article {
    private String reference;
    private String designation;
    private double prixUnitaire;
    private int quantiteStock;

    public Article(String ref, String des, double pu, int qte) {
        this.reference = ref;
        this.designation = des;
        this.prixUnitaire = pu;
        this.quantiteStock = qte;
    }

    public Article(Article a) {
        this.reference = a.reference;
        this.designation = a.designation;
        this.prixUnitaire = a.prixUnitaire;
        this.quantiteStock = a.quantiteStock;
    }

    public String getReference() { return reference; }
    public String getDesignation() { return designation; }

    public void affiche() {
        System.out.println("Ref: " + reference + " | " + designation + " | Prix: " + prixUnitaire + " | Stock: " + quantiteStock);
    }
}

class Commande {
    private int numeroCommande;
    private String dateCommande;
    private Client client;

    public Commande(int numeroCommande, String dateCommande, Client client) {
        this.numeroCommande = numeroCommande;
        this.dateCommande = dateCommande;
        this.client = client;
    }

    public int getNumeroCommande() { return numeroCommande; }
    public void affiche() {
        System.out.println("Commande N° : " + numeroCommande + " du " + dateCommande);
        client.affiche();
    }
}

class Ligne {
    private Commande commande;
    private Article article;
    private int quantiteCommande;

    public Ligne(Commande commande, Article article, int quantiteCommande) {
        this.commande = commande;
        this.article = article;
        this.quantiteCommande = quantiteCommande;
    }

    public Commande getCommande() { return commande; }

    public void affiche() {
        System.out.print("Qte: " + quantiteCommande + " | ");
        article.affiche();
    }
}