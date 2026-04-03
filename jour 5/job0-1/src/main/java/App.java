import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Button btn = new Button("Hello JavaFX + Maven !");
        btn.setOnAction(e -> System.out.println("Bouton cliqué !"));

        StackPane root = new StackPane(btn);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Mon Projet Maven");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}