package desktop.inventario.model;

public class Producto {

    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
    private int stockMinimo;

    public Producto(int id, String nombre, String categoria,
                    double precio, int stock, int stockMinimo) {

        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getEstado() {

        if (stock == 0) {
            return "Agotado";
        }

        if (stock <= stockMinimo) {
            return "Stock bajo";
        }

        return "Disponible";
    }
}
