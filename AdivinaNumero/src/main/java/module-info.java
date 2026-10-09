module desktop.adivinanumero {
    requires javafx.controls;
    requires javafx.fxml;


    opens desktop.adivinanumero to javafx.fxml;
    exports desktop.adivinanumero;
}