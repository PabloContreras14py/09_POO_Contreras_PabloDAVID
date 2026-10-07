package vallegrande.edu.pe.misistema.model;

public class Usuario {

    private int id;
    private String nombreEmpresa;
    private String correo;
    private String telefono;
    private String productoInteres;
    private String tipoComprador;
    private String mensaje;

    public Usuario() {}

    public Usuario(int id, String nombreEmpresa, String correo, String telefono, String productoInteres, String tipoComprador, String mensaje) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.correo = correo;
        this.telefono = telefono;
        this.productoInteres = productoInteres;
        this.tipoComprador = tipoComprador;
        this.mensaje = mensaje;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getProductoInteres() { return productoInteres; }
    public void setProductoInteres(String productoInteres) { this.productoInteres = productoInteres; }

    public String getTipoComprador() { return tipoComprador; }
    public void setTipoComprador(String tipoComprador) { this.tipoComprador = tipoComprador; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}