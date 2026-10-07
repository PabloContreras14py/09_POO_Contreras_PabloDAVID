package vallegrande.edu.pe.misistema.view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;
import vallegrande.edu.pe.misistema.model.Usuario;
import vallegrande.edu.pe.misistema.model.UsuarioDAO;

public class MainView extends BorderPane {
    private Button btnInicio;
    private Button btnUsuarios;
    private Button btnProductos;
    private Button btnReportes;
    private Button btnConfiguracion;
    private Button btnCitas;

    // Elementos del formulario
    private TextField txtNombre;
    private TextField txtCorreo;
    private TextField txtTelefono;
    private ComboBox<String> cbProducto;
    private ComboBox<String> cbTipoComprador;
    private TextArea txtMensaje;
    private Button btnEnviar;

    public MainView(){
        inicializarComponentesFormulario();
        crearMenu();
        mostrarInicio();
    }

    private void inicializarComponentesFormulario() {
        txtNombre = new TextField();
        txtNombre.setPromptText("Ej. Juan Pérez / Distribuidora del Sur");

        txtCorreo = new TextField();
        txtCorreo.setPromptText("correo@ejemplo.com");

        txtTelefono = new TextField();
        txtTelefono.setPromptText("987654321");

        cbProducto = new ComboBox<>();
        cbProducto.setItems(FXCollections.observableArrayList("Queso Paria", "Queso Andino", "Yogurt Gloria", "Mantequilla Laive"));
        cbProducto.setPromptText("Seleccione un producto");
        cbProducto.setMaxWidth(Double.MAX_VALUE);

        cbTipoComprador = new ComboBox<>();
        cbTipoComprador.setItems(FXCollections.observableArrayList("Comerciante Mayorista", "Distribuidor", "Cliente Final"));
        cbTipoComprador.setPromptText("Seleccione tipo");
        cbTipoComprador.setMaxWidth(Double.MAX_VALUE);

        txtMensaje = new TextArea();
        txtMensaje.setPromptText("Indícanos la cantidad estimada o consulta técnica...");
        txtMensaje.setPrefRowCount(3);

        btnEnviar = new Button("Enviar Consulta");
        btnEnviar.setStyle("-fx-background-color: #C5A059; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20;");
        btnEnviar.setMaxWidth(Double.MAX_VALUE);
    }

    private void crearMenu(){
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(25));
        menu.setPrefWidth(220);
        Label titulo = new Label("💻 MI SISTEMA");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        btnInicio = crearBoton("Inicio");
        btnUsuarios = crearBoton("Formulario / Registros");
        btnProductos = crearBoton("Productos");
        btnReportes = crearBoton("Reportes");
        btnConfiguracion = crearBoton("Configuración");
        btnCitas = crearBoton("Citas");

        menu.getChildren().addAll(titulo, btnInicio, btnUsuarios, btnProductos, btnReportes, btnConfiguracion, btnCitas);
        menu.setStyle("-fx-background-color: #A290B7;");
        setLeft(menu);
    }

    private Button crearBoton(String texto){
        Button boton = new Button(texto);
        boton.setPrefWidth(170);
        boton.setPrefHeight(40);
        boton.setStyle("-fx-background-color: white; -fx-text-fill: #1E3A8A; -fx-font-size: 14px; -fx-background-radius: 8;");
        return boton;
    }

    public void mostrarInicio() {
        VBox contenido = new VBox(15);
        contenido.setPadding(new Insets(30));
        contenido.setAlignment(Pos.TOP_LEFT);

        Label titulo = new Label("Envíanos un mensaje");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label subtitulo = new Label("Completa tus datos para enviarte nuestro catálogo de precios al por mayor.");
        subtitulo.setStyle("-fx-font-size: 13px; -fx-text-fill: #555555;");

        VBox form = new VBox(10);
        form.setMaxWidth(500);
        form.getChildren().addAll(
                new Label("NOMBRE COMPLETO O EMPRESA"), txtNombre,
                new HBox(10, new VBox(5, new Label("CORREO ELECTRÓNICO"), txtCorreo), new VBox(5, new Label("TELÉFONO / WHATSAPP"), txtTelefono)),
                new HBox(10, new VBox(5, new Label("PRODUCTO DE INTERÉS"), cbProducto), new VBox(5, new Label("TIPO DE COMPRADOR"), cbTipoComprador)),
                new Label("MENSAJE O DETALLE DEL PEDIDO"), txtMensaje,
                btnEnviar
        );

        contenido.getChildren().addAll(titulo, subtitulo, form);
        ScrollPane scroll = new ScrollPane(contenido);
        scroll.setFitToWidth(true);
        setCenter(scroll);
    }

    public void mostrarDatosUsuarios(List<Usuario> usuarios) {
        VBox contenido = new VBox(15);
        contenido.setPadding(new Insets(30));

        Label titulo = new Label("CONSULTAS RECIBIDAS (MYSQL)");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TableView<Usuario> tableUsuarios = new TableView<>();

        TableColumn<Usuario, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Usuario, String> colEmpresa = new TableColumn<>("Empresa / Nombre");
        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));

        TableColumn<Usuario, String> colCorreo = new TableColumn<>("Correo");
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        TableColumn<Usuario, String> colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<Usuario, String> colProducto = new TableColumn<>("Producto");
        colProducto.setCellValueFactory(new PropertyValueFactory<>("productoInteres"));

        TableColumn<Usuario, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoComprador"));

        TableColumn<Usuario, String> colMensaje = new TableColumn<>("Mensaje");
        colMensaje.setCellValueFactory(new PropertyValueFactory<>("mensaje"));

        tableUsuarios.getColumns().addAll(colId, colEmpresa, colCorreo, colTelefono, colProducto, colTipo, colMensaje);
        tableUsuarios.setItems(FXCollections.observableArrayList(usuarios));

        // BOTÓN ELIMINAR
        Button btnEliminar = new Button("🗑️ Eliminar Seleccionado");
        btnEliminar.setStyle("-fx-background-color: #E53E3E; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 15;");

        btnEliminar.setOnAction(e -> {
            Usuario seleccionado = tableUsuarios.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                UsuarioDAO dao = new UsuarioDAO();
                dao.eliminar(seleccionado.getId());
                mostrarDatosUsuarios(dao.listar());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona una fila primero.");
                alert.show();
            }
        });

        contenido.getChildren().addAll(titulo, tableUsuarios, btnEliminar);
        setCenter(contenido);
    }

    public void mostrarProductos() { setCenter(new Label("Sección Productos")); }
    public void mostrarReportes() { setCenter(new Label("Sección Reportes")); }
    public void mostrarConfiguracion() { setCenter(new Label("Sección Configuración")); }
    public void mostrarCitas() { setCenter(new Label("Sección Citas")); }

    // GETTERS
    public Button getBtnInicio() { return btnInicio; }
    public Button getBtnUsuarios() { return btnUsuarios; }
    public Button getBtnProductos() { return btnProductos; }
    public Button getBtnReportes() { return btnReportes; }
    public Button getBtnConfiguracion() { return btnConfiguracion; }
    public Button getBtnCitas() { return btnCitas; }

    public TextField getTxtNombre() { return txtNombre; }
    public TextField getTxtCorreo() { return txtCorreo; }
    public TextField getTxtTelefono() { return txtTelefono; }
    public ComboBox<String> getCbProducto() { return cbProducto; }
    public ComboBox<String> getCbTipoComprador() { return cbTipoComprador; }
    public TextArea getTxtMensaje() { return txtMensaje; }
    public Button getBtnEnviar() { return btnEnviar; }
}