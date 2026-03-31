package job5;
public class ABCDEF {

    public static void main(String[] args) {
        // Initialisation des objets
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();
        E e = new E();
        F f = new F();

        // --- ANALYSE DES AFFECTATIONS ---
        a = b;      
        a = (A) b;  
        a = null;   
        a = d;      
        a = e;      
        d = e;      

        // --- REMPLISSAGE DU TABLEAU ---
        A[] as = new A[10];
        as[0] = new A();
        as[1] = new B();
        as[2] = new D(2); 
        as[3] = new E();    
        as[4] = new C();    
        as[5] = new D(4); 
        as[6] = new B();

        rechercher(as);
        additionner(as);
        System.out.println("Variables de test : " + a + ", " + c + ", " + f);
    }

    private static void rechercher(A[] as) {
        int compteur = 0;
        for (A objet : as) {
            if (objet != null && objet instanceof B) {
                compteur++;
            }
        }
        System.out.println("Il y a " + compteur + " instances de la classe B");
    }

    private static void additionner(A[] as) {
        int somme = 0;
        for (A objet : as) {
            if (objet instanceof D) {
                D objetD = (D) objet;
                somme += objetD.d;
            }
        }
        System.out.println("Somme des variables d : " + somme);
    }
} // Fin de la classe ABCDEF

// --- DÉFINITIONS DES CLASSES DE LA HIÉRARCHIE ---
// Ces classes ne doivent pas être "public" car elles sont dans le même fichier

class A {
    public A() {}
}

class B extends A {
    public B() { super(); }
}

class C extends B {
    public C() { super(); }
}

class D extends A {
    protected int d = 1;
    public D(int x) { this.d = x; }
    public D() {}
}

class E extends D {
    public E() { super(); }
}

class F extends D {
    public F() { super(); }
}