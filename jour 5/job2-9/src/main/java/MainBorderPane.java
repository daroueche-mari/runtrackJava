import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Objects;

public class MainBorderPane extends Application {

    // --- MODELE (Job 07) ---
    public static class Product {
        private String name, category, color;
        public Product(String n, String cat, String col) {
            this.name = n; this.category = cat; this.color = col;
        }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public String getColor() { return color; }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        // Job 01 : Disposition BorderPane
        BorderPane root = new BorderPane();
        TabPane tabPane = new TabPane(); // Job 08 : Onglets

        // --- ONGLET 1 : FORMULAIRE (Jobs 03, 05, 06) ---
        Tab inputTab = new Tab("Inputs & Controls");
        inputTab.setClosable(false);
        
        // Job 03 : FlowPane Layout
        FlowPane flowPane = new FlowPane(10, 10);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Button sendButton = new Button("Send");

        // Job 05 : Événement bouton "Send" vers console
        sendButton.setOnAction(e -> System.out.println("Console Output (Job 05): " + nameField.getText()));

        flowPane.getChildren().addAll(nameLabel, nameField, sendButton);

        // Job 06 : ComboBox & Choix
        VBox comboLayout = new VBox(15);
        comboLayout.setAlignment(Pos.CENTER);
        
        ComboBox<String> itemCombo = new ComboBox<>(FXCollections.observableArrayList("Laptop", "Phone", "Tablet"));
        itemCombo.setPromptText("Select Item");
        
        ComboBox<String> colorCombo = new ComboBox<>(FXCollections.observableArrayList("Red", "Blue", "Green", "Black"));
        colorCombo.setPromptText("Select Color");

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            // Affiche les choix dans la console
            System.out.println("Job 06 Selection: " + itemCombo.getValue() + " / Color: " + colorCombo.getValue());
        });

        VBox fullInputLayout = new VBox(20, flowPane, new Label("Job 06 Selection:"), itemCombo, colorCombo, okButton);
        fullInputLayout.setAlignment(Pos.CENTER);
        inputTab.setContent(fullInputLayout);

        // --- ONGLET 2 : DATA TABLE & EXPORT (Job 07 - Layout Corrigé) ---
        Tab dataTab = new Tab("Data & Export");
        dataTab.setClosable(false);

        TableView<Product> tableView = new TableView<>();
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, String> catCol = new TableColumn<>("Category");
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<Product, String> colCol = new TableColumn<>("Color");
        colCol.setCellValueFactory(new PropertyValueFactory<>("color"));

        tableView.getColumns().addAll(nameCol, catCol, colCol);
        
        ObservableList<Product> dataList = FXCollections.observableArrayList(
            new Product("iPhone 15", "Smartphone", "Black"),
            new Product("iPad Air", "Tablet", "Blue"),
            new Product("Xbox Series X", "Console", "Black")
        );
        tableView.setItems(dataList);

        // --- OPTIMISATION DU LAYOUT ---
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN); // Colonnes auto-ajustées
        tableView.setMaxWidth(Double.MAX_VALUE); // Prend toute la largeur
        
        Button exportButton = new Button("Export to CSV");
        exportButton.setOnAction(event -> exportToCSV(tableView));

        // Organisation verticale
        VBox dataLayout = new VBox(15);
        dataLayout.setPadding(new Insets(20));
        dataLayout.setAlignment(Pos.TOP_CENTER); // Aligné en haut, centré horizontalement
        
        // C'EST ICI QUE CA SE JOUE :
        VBox.setVgrow(tableView, Priority.ALWAYS); // Le tableau s'étire verticalement
        
        dataLayout.getChildren().addAll(
            new Label("Inventory Management"), 
            tableView, 
            exportButton
        );
        
        dataTab.setContent(dataLayout);

        // --- ONGLET 3 : SETTINGS (Job 08 & Interaction) ---
        Tab configTab = new Tab("Settings");
        configTab.setClosable(false);

        // Job 08 : Utilisation de HBox pour les options
        HBox configLayout = new HBox(20);
        configLayout.setAlignment(Pos.CENTER);
        configLayout.setPadding(new Insets(30));

        CheckBox darkModeCB = new CheckBox("Dark Mode");
        darkModeCB.setSelected(false);
        darkModeCB.setOnAction(e -> root.setStyle(darkModeCB.isSelected() ? "-fx-base: #2c3e50;" : "-fx-base: #ecf0f1;"));

        ToggleButton autoSaveBtn = new ToggleButton("Auto-save: OFF");
        autoSaveBtn.setOnAction(e -> {
            if (autoSaveBtn.isSelected()) {
                autoSaveBtn.setText("Auto-save: ON");
                autoSaveBtn.setStyle("-fx-background-color: #2ecc71;");
            } else {
                autoSaveBtn.setText("Auto-save: OFF");
                autoSaveBtn.setStyle("");
            }
        });

        configLayout.getChildren().addAll(new Label("Display Preferences:"), darkModeCB, autoSaveBtn);
        configTab.setContent(configLayout);

        tabPane.getTabs().setAll(inputTab, dataTab, configTab);
        root.setCenter(tabPane);

        // --- JOB 01 & 09 : BOUTON QUITTER ET CONFIRMATION ---
        Button quitButton = new Button("Quit");
        quitButton.setId("quit-button");
        quitButton.setOnAction(event -> askConfirmationToQuit(primaryStage));
        
        // Gestion de la fermeture par la fenêtre (Croix)
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            askConfirmationToQuit(primaryStage);
        });

        root.setBottom(quitButton);
        BorderPane.setAlignment(quitButton, Pos.CENTER);
        BorderPane.setMargin(quitButton, new Insets(10));

        // Job 04 : Style et Thème (Fichier CSS)
        Scene scene = new Scene(root, 850, 600);
        try {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        } catch (Exception e) { 
            System.out.println("CSS file not found, loading default."); 
        }

        primaryStage.setTitle("Runtrack Java - Final Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // --- JOB 09 : BOITE DE DIALOGUE CONFIRMATION ---
    private void askConfirmationToQuit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("Confirmation Needed");
        alert.setContentText("Are you sure you want to close the application?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();
        }
    }

    // --- JOB 07 & 09 : EXPORT & ERREURS ---
    private void exportToCSV(TableView<Product> table) {
        // Job 09 : Message d'erreur si données invalides (Table vide)
        if (table.getItems().isEmpty()) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Export Error");
            alertError.setHeaderText("No Data");
            alertError.setContentText("The table is empty. Nothing to export.");
            alertError.showAndWait();
            return;
        }

        try (FileWriter writer = new FileWriter("data_export.csv")) {
            writer.write("Name;Category;Color\n");
            for (Product p : table.getItems()) {
                writer.write(p.getName() + ";" + p.getCategory() + ";" + p.getColor() + "\n");
            }
            
            Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
            alertSuccess.setTitle("Success");
            alertSuccess.setContentText("Data exported to data_export.csv");
            alertSuccess.show();

        } catch (IOException e) {
            // Job 09 : Message d'erreur critique
            Alert alertException = new Alert(Alert.AlertType.ERROR);
            alertException.setTitle("Critical Error");
            alertException.setContentText("Failed to write to file: " + e.getMessage());
            alertException.showAndWait();
        }
    }

    public static void main(String[] args) { launch(args); }
}