package vallegrande.edu.pe.sistemaproductores.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import vallegrande.edu.pe.sistemaproductores.model.Productor;
import vallegrande.edu.pe.sistemaproductores.model.ProductorDAO;
import vallegrande.edu.pe.sistemaproductores.view.MainView;

import java.util.List;

public class MainController {

    private MainView view;
    private ProductorDAO dao;
    private ObservableList<Productor> listaObservables;

    public MainController(MainView view, ProductorDAO dao) {
        this.view = view;
        this.dao = dao;
        initController();
    }

    private void initController() {
        listarProductores();

        // Eventos
        view.getBtnRegistrar().setOnAction(e -> registrar());
        view.getBtnActualizar().setOnAction(e -> actualizar());
        view.getBtnEliminar().setOnAction(e -> eliminar());

        // Listener de selección en TableView
        view.getTabla().getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        view.cargarProductorEnFormulario(newSelection);
                    }
                }
        );
    }

    public void listarProductores() {
        List<Productor> lista = dao.listar();
        listaObservables = FXCollections.observableArrayList(lista);
        view.getTabla().setItems(listaObservables);
    }

    private void registrar() {
        String nombre = view.getTxtNombre().getText().trim();
        String dni = view.getTxtDni().getText().trim();
        String telefono = view.getTxtTelefono().getText().trim();
        String comunidad = view.getTxtComunidad().getText().trim();

        if (nombre.isEmpty() || dni.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "El nombre y el DNI son obligatorios.");
            return;
        }

        Productor p = new Productor(0, nombre, dni, telefono, comunidad);
        boolean exito = dao.registrar(p);

        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Productor registrado correctamente.");
            listarProductores();
            view.limpiarFormulario();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el productor.");
        }
    }

    private void actualizar() {
        String idText = view.getTxtId().getText().trim();

        if (idText.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Debe seleccionar un productor de la tabla.");
            return;
        }

        int id = Integer.parseInt(idText);
        String nombre = view.getTxtNombre().getText().trim();
        String dni = view.getTxtDni().getText().trim();
        String telefono = view.getTxtTelefono().getText().trim();
        String comunidad = view.getTxtComunidad().getText().trim();

        Productor p = new Productor(id, nombre, dni, telefono, comunidad);
        boolean exito = dao.actualizar(p);

        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Productor actualizado correctamente.");
            listarProductores();
            view.limpiarFormulario();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el productor.");
        }
    }

    private void eliminar() {
        String idText = view.getTxtId().getText().trim();

        if (idText.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Debe seleccionar un productor para eliminar.");
            return;
        }

        int id = Integer.parseInt(idText);
        boolean exito = dao.eliminar(id);

        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Productor eliminado correctamente.");
            listarProductores();
            view.limpiarFormulario();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el productor.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}