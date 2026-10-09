package desktop.inventario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader =
                new FXMLLoader(
                        HelloApplication.class.getResource(
                                "hello-view.fxml"
                        )
                );

        Scene scene = new Scene(
                fxmlLoader.load(),
                1200,
                750
        );

        stage.setTitle("StockFlow - Inventario");

        stage.setScene(scene);

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
