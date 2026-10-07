package vallegrande.edu.pe.misistema.controller;

import javafx.scene.control.Alert;
import vallegrande.edu.pe.misistema.model.Usuario;
import vallegrande.edu.pe.misistema.model.UsuarioDAO;
import vallegrande.edu.pe.misistema.view.MainView;

import java.util.List;

public class MainController {

    private MainView view;
    private UsuarioDAO usuarioDAO;

    public MainController(MainView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        configurarEventos();
    }

    public void configurarEventos() {
        view.getBtnInicio().setOnAction(e -> view.mostrarInicio());
        view.getBtnUsuarios().setOnAction(e -> cargarUsuarios());
        view.getBtnEnviar().setOnAction(e -> registrarUsuario());
    }

    private void cargarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listar();
        view.mostrarDatosUsuarios(usuarios);
    }

    private void registrarUsuario() {
        if (view.getTxtNombre().getText().trim().isEmpty() || view.getTxtCorreo().getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, ingresa al menos el Nombre y el Correo.");
            alert.show();
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombreEmpresa(view.getTxtNombre().getText());
        usuario.setCorreo(view.getTxtCorreo().getText());
        usuario.setTelefono(view.getTxtTelefono().getText());
        usuario.setProductoInteres(view.getCbProducto().getValue());
        usuario.setTipoComprador(view.getCbTipoComprador().getValue());
        usuario.setMensaje(view.getTxtMensaje().getText());

        usuarioDAO.insertar(usuario);

        limpiarFormulario();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "¡Consulta guardada con éxito en MySQL!");
        alert.showAndWait();

        cargarUsuarios(); // Cambia inmediatamente a la tabla para ver el registro
    }

    private void limpiarFormulario() {
        view.getTxtNombre().clear();
        view.getTxtCorreo().clear();
        view.getTxtTelefono().clear();
        view.getCbProducto().setValue(null);
        view.getCbTipoComprador().setValue(null);
        view.getTxtMensaje().clear();
    }
}