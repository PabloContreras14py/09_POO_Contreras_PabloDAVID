package desktop.inventario.controller;

import desktop.inventario.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventarioController {

    // Campos del formulario
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtStockMinimo;

    // Tabla
    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, Integer> colId;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<Producto, String> colEstado;

    @FXML
    private Label lblTotalProductos;

    @FXML
    private Label lblStockBajo;

    @FXML
    private Label lblAgotados;

    @FXML
    private Label lblMensaje;

    private final ObservableList<Producto> productos =
            FXCollections.observableArrayList();

    private int siguienteId = 1;

    @FXML
    public void initialize() {

        configurarTabla();

        cargarProductosIniciales();

        actualizarResumen();
    }

    private void configurarTabla() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );

        tablaProductos.setItems(productos);
    }

    private void cargarProductosIniciales() {

        productos.add(
                new Producto(
                        siguienteId++,
                        "Laptop Lenovo",
                        "Tecnología",
                        2500.00,
                        15,
                        5
                )
        );

        productos.add(
                new Producto(
                        siguienteId++,
                        "Mouse Logitech",
                        "Accesorios",
                        85.00,
                        4,
                        5
                )
        );

        productos.add(
                new Producto(
                        siguienteId++,
                        "Teclado Mecánico",
                        "Accesorios",
                        180.00,
                        0,
                        3
                )
        );
    }

    @FXML
    private void agregarProducto() {

        String nombre = txtNombre.getText().trim();
        String categoria = txtCategoria.getText().trim();

        if (nombre.isEmpty() || categoria.isEmpty()) {

            mostrarMensaje("Completa los campos obligatorios.");

            return;
        }

        try {

            double precio = Double.parseDouble(
                    txtPrecio.getText()
            );

            int stock = Integer.parseInt(
                    txtStock.getText()
            );

            int stockMinimo = Integer.parseInt(
                    txtStockMinimo.getText()
            );

            if (precio < 0 || stock < 0 || stockMinimo < 0) {

                mostrarMensaje(
                        "Los valores no pueden ser negativos."
                );

                return;
            }

            Producto producto = new Producto(
                    siguienteId++,
                    nombre,
                    categoria,
                    precio,
                    stock,
                    stockMinimo
            );

            productos.add(producto);

            limpiarFormulario();

            actualizarResumen();

            mostrarMensaje(
                    "Producto agregado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Precio, stock y stock mínimo deben ser números."
            );
        }
    }

    @FXML
    private void eliminarProducto() {

        Producto seleccionado =
                tablaProductos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            mostrarMensaje(
                    "Selecciona un producto para eliminar."
            );

            return;
        }

        productos.remove(seleccionado);

        actualizarResumen();

        mostrarMensaje(
                "Producto eliminado correctamente."
        );
    }

    @FXML
    private void limpiarFormulario() {

        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtStock.clear();
        txtStockMinimo.clear();
    }

    private void actualizarResumen() {

        int total = productos.size();

        long stockBajo = productos.stream()
                .filter(p -> p.getStock() > 0 &&
                        p.getStock() <= p.getStockMinimo())
                .count();

        long agotados = productos.stream()
                .filter(p -> p.getStock() == 0)
                .count();

        lblTotalProductos.setText(String.valueOf(total));
        lblStockBajo.setText(String.valueOf(stockBajo));
        lblAgotados.setText(String.valueOf(agotados));
    }

    private void mostrarMensaje(String mensaje) {

        lblMensaje.setText(mensaje);
    }
}
