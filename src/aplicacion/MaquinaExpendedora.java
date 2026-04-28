// MaquinaExpendedora.java
package aplicacion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MaquinaExpendedora {
    private String ID;
    private int capacidad;
    private Instant ultimaActualizacion;
    private List<Producto> productos;

    // Constructor sin productos — la lista queda vacía
    public MaquinaExpendedora(String ID, int capacidad) {
        this.ID = ID;
        this.capacidad = capacidad;
        this.ultimaActualizacion = null;
        this.productos = new ArrayList<>();
    }

    // Constructor con lista de productos — el stock se inicializa a 0 en Stock
    public MaquinaExpendedora(String ID, int capacidad, List<Producto> productos) {
        this.ID = ID;
        this.capacidad = capacidad;
        this.ultimaActualizacion = null;
        this.productos = new ArrayList<>();
        Stock.asignarProductos(this, productos); // delega en Stock para crear los registros
    }

    // Visibilidad de paquete: solo Stock debe modificar la lista directamente
    void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    void registrarActualizacion() {
        this.ultimaActualizacion = Instant.now();
    }

    public List<Producto> getProductos()        { return productos; }
    public String getID()                        { return ID; }
    public int getCapacidad()                    { return capacidad; }
    public Instant getUltimaActualizacion()      { return ultimaActualizacion; }
}
