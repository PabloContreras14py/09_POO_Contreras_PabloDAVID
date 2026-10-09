package vallegrande.edu.pe.sistemaproductores.model;

public class Productor {

    private int id;
    private String nombre;
    private String dni;
    private String telefono;
    private String comunidad;

    public Productor() {
    }

    public Productor(int id, String nombre, String dni,
                     String telefono, String comunidad) {

        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.comunidad = comunidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }
}