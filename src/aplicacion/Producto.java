package aplicacion;


public class Producto {

    private String nombre;
    private String ID;
    private float precioUnidad;
    private String proveedor;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(String nombre, String ID, float precioUnidad, String proveedor) {
        this.nombre = nombre;
        this.ID = ID;
        this.precioUnidad = precioUnidad;
        this.proveedor = proveedor;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public float getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(float precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
