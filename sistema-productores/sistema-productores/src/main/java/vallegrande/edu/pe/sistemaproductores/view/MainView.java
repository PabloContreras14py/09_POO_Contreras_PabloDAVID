package vallegrande.edu.pe.sistemaproductores.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import vallegrande.edu.pe.sistemaproductores.model.Productor;

public class MainView extends BorderPane {

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtDni;
    private TextField txtTelefono;
    private TextField txtComunidad;

    private Button btnRegistrar;
    private Button btnActualizar;
    private Button btnEliminar;

    private TableView<Productor> tabla;
    private TableColumn<Productor, Integer> colId;
    private TableColumn<Productor, String> colNombre;
    private TableColumn<Productor, String> colDni;
    private TableColumn<Productor, String> colTelefono;
    private TableColumn<Productor, String> colComunidad;

    public MainView() {
        initComponents();
    }

    private void initComponents() {
        this.setPadding(new Insets(20));

        // Header / Encabezado
        Label lblTitulo = new Label("GESTIÓN DE PRODUCTORES AGRÍCOLAS");
        lblTitulo.getStyleClass().add("header-label");

        HBox headerBox = new HBox(lblTitulo);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(0, 0, 20, 0));
        this.setTop(headerBox);

        // Formulario (Izquierda)
        VBox formBox = new VBox(12);
        formBox.getStyleClass().add("card-panel");
        formBox.setPrefWidth(300);

        Label lblFormTitle = new Label("Datos del Productor");
        lblFormTitle.getStyleClass().add("section-title");

        txtId = new TextField();
        txtId.setPromptText("ID (Auto)");
        txtId.setEditable(false);

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");

        txtDni = new TextField();
        txtDni.setPromptText("DNI");

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono");

        txtComunidad = new TextField();
        txtComunidad.setPromptText("Comunidad");

        // Botones con sus clases del CSS
        btnRegistrar = new Button("Registrar");
        btnRegistrar.getStyleClass().add("btn-primary");
        btnRegistrar.setMaxWidth(Double.MAX_VALUE);

        btnActualizar = new Button("Actualizar");
        btnActualizar.getStyleClass().add("btn-secondary");
        btnActualizar.setMaxWidth(Double.MAX_VALUE);

        btnEliminar = new Button("Eliminar");
        btnEliminar.getStyleClass().add("btn-danger");
        btnEliminar.setMaxWidth(Double.MAX_VALUE);

        VBox buttonBox = new VBox(8, btnRegistrar, btnActualizar, btnEliminar);

        formBox.getChildren().addAll(
                lblFormTitle,
                new Label("ID:"), txtId,
                new Label("Nombre:"), txtNombre,
                new Label("DNI:"), txtDni,
                new Label("Teléfono:"), txtTelefono,
                new Label("Comunidad:"), txtComunidad,
                new Separator(),
                buttonBox
        );

        this.setLeft(formBox);

        // Tabla (Centro)
        tabla = new TableView<>();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colDni = new TableColumn<>("DNI");
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));

        colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        colComunidad = new TableColumn<>("Comunidad");
        colComunidad.setCellValueFactory(new PropertyValueFactory<>("comunidad"));

        tabla.getColumns().addAll(colId, colNombre, colDni, colTelefono, colComunidad);

        VBox centerBox = new VBox(tabla);
        centerBox.setPadding(new Insets(0, 0, 0, 20));
        HBox.setHgrow(tabla, Priority.ALWAYS);
        VBox.setVgrow(tabla, Priority.ALWAYS);

        this.setCenter(centerBox);

        // Barra de estado inferior (opcional para estilo completo)
        Label lblStatus = new Label("✔ Conectado a MySQL");
        lblStatus.getStyleClass().add("status-label");
        HBox statusBar = new HBox(lblStatus);
        statusBar.getStyleClass().add("status-bar");
        BorderPane.setMargin(statusBar, new Insets(15, 0, 0, 0));
        this.setBottom(statusBar);
    }

    public void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtDni.clear();
        txtTelefono.clear();
        txtComunidad.clear();
        tabla.getSelectionModel().clearSelection();
    }

    public void cargarProductorEnFormulario(Productor p) {
        if (p != null) {
            txtId.setText(String.valueOf(p.getId()));
            txtNombre.setText(p.getNombre());
            txtDni.setText(p.getDni());
            txtTelefono.setText(p.getTelefono() != null ? p.getTelefono() : "");
            txtComunidad.setText(p.getComunidad() != null ? p.getComunidad() : "");
        }
    }

    public TextField getTxtId() { return txtId; }
    public TextField getTxtNombre() { return txtNombre; }
    public TextField getTxtDni() { return txtDni; }
    public TextField getTxtTelefono() { return txtTelefono; }
    public TextField getTxtComunidad() { return txtComunidad; }
    public Button getBtnRegistrar() { return btnRegistrar; }
    public Button getBtnActualizar() { return btnActualizar; }
    public Button getBtnEliminar() { return btnEliminar; }
    public TableView<Productor> getTabla() { return tabla; }
}