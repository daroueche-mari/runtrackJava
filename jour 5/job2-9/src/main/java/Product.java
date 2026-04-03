public class Product {
    private String name;
    private String category;
    private String color;

    // Constructeur pour initialiser un nouveau produit
    public Product(String name, String category, String color) {
        this.name = name;
        this.category = category;
        this.color = color;
    }

    // Getters (Indispensables pour que le TableView puisse lire les données via PropertyValueFactory)
    public String getName() { 
        return name; 
    }

    public String getCategory() { 
        return category; 
    }

    public String getColor() { 
        return color; 
    }
}