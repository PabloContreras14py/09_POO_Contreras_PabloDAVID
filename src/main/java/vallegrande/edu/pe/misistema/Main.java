package vallegrande.edu.pe.misistema;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vallegrande.edu.pe.misistema.controller.MainController;
import vallegrande.edu.pe.misistema.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Instanciamos la vista y el controlador
        MainView view = new MainView();
        new MainController(view);

        // 2. Creamos la escena de JavaFX
        Scene scene = new Scene(view, 900, 600);

        // 3. Configuramos y mostramos la ventana
        primaryStage.setTitle("Mi Sistema - Lácteos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}