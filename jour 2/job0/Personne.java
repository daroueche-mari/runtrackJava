package job0;
public class Personne {
    // Attributs PUBLICS : Visibles partout
    public String nom;
    public String prenom;

    // Attributs PROTÉGÉS : Visibles dans le même package et par les héritiers
    protected String dateNaissance;
    protected String lieuNaissance;

    // Attributs PRIVÉS : Visibles UNIQUEMENT dans cette classe
    private String adresse;
    private String telephone;

    // Constructeur
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // --- ACCESSEURS (Getters et Setters) ---
    // Obligatoires pour les attributs privés si on veut y accéder de l'extérieur
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}