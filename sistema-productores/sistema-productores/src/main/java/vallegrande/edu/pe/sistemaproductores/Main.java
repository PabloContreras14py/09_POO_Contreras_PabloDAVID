package vallegrande.edu.pe.sistemaproductores;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vallegrande.edu.pe.sistemaproductores.controller.MainController;
import vallegrande.edu.pe.sistemaproductores.model.ProductorDAO;
import vallegrande.edu.pe.sistemaproductores.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainView view = new MainView();
        ProductorDAO dao = new ProductorDAO();
        new MainController(view, dao);

        Scene scene = new Scene(view, 950, 600);

        // Cargar hoja de estilos CSS
        String css = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setTitle("Sistema de Gestión de Productores");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}