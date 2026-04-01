package job3;
class Polymorph {
    public static void main(String[] args) {
        // La déclaration de tabFormes reste inchangée comme demandé
        Forme[] tabFormes = {
            new Cercle("rouge"),
            new Triangle("jaune")
        };

        Collect formes = new Collect(10);

        // Remplissage de la collection
        for (int i = 0; i < tabFormes.length; ++i) {
            /* CORRECTION : On appelle la méthode copie(). 
               Grâce au polymorphisme, si l'objet est un Cercle, c'est la méthode 
               copie() de Cercle qui est appelée (Lien Dynamique). 
               Cela évite le "tranchage" (object slicing). */
            formes.add(tabFormes[i].copie()); 
        }

        formes.dessine();
    }
}

class Forme {
    private String couleur;

    public Forme(String uneCouleur) {
        couleur = uneCouleur;
    }

    // Constructeur de copie pour la classe de base
    public Forme(Forme other) {
        this.couleur = other.couleur;
    }

    /* Cette méthode est la clé : elle permet de dupliquer un objet
       sans connaître son type exact à l'avance (Cercle ou Triangle). */
    public Forme copie() {
        return new Forme(this);
    }

    public void dessine() {
        System.out.println("Une forme " + couleur);
    }
}

class Triangle extends Forme {
    public Triangle(String uneCouleur) { super(uneCouleur); }
    // Constructeur de copie spécifique au Triangle
    public Triangle(Triangle autre) { super(autre); }

    @Override
    public void dessine() {
        super.dessine(); // Affiche "Une forme ..." via le parent
        System.out.println("toute pointue");
    }

    @Override
    public Forme copie() {
        /* Redéfinition : Renvoie un NOUVEAU Triangle. 
           C'est ce qui permet de garder les propriétés du triangle 
           lors de la copie dans la collection. */
        return new Triangle(this); 
    }
}

class Cercle extends Forme {
    public Cercle(String uneCouleur) { super(uneCouleur); }
    // Constructeur de copie spécifique au Cercle
    public Cercle(Cercle autre) { super(autre); }

    @Override
    public void dessine() {
        super.dessine();
        System.out.println("toute ronde");
    }

    @Override
    public Forme copie() {
        /* Redéfinition : Renvoie un NOUVEAU Cercle. 
           L'objet ajouté à la collection sera bien de type Cercle. */
        return new Cercle(this); 
    }
}

class Collect {
    private Forme collect[];
    private int index;

    public Collect(int indexMax) {
        collect = new Forme[indexMax];
        index = -1;
    }

    public void add(Forme a) {
        if (index < collect.length - 1) {
            ++index;
            collect[index] = a;
        }
    }

    public void dessine() {
        // Parcourt la collection et appelle dessine() sur chaque forme
        for (int i = 0; i <= index; ++i) {
            /* Grâce au polymorphisme, Java appelle le dessine() de l'objet RÉEL
               (celui du Cercle ou du Triangle) et non celui de Forme. */
            collect[i].dessine();
        }
    }
}