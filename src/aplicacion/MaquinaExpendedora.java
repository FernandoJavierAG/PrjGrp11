// MaquinaExpendedora.java
package aplicacion;

import java.time.Instant;

public class MaquinaExpendedora {
    private String ID;
    private int capacidad;
    private Instant ultimaActualizacion;

    public MaquinaExpendedora(String ID, int capacidad) {
        this.ID = ID;
        this.capacidad = capacidad;
        this.ultimaActualizacion = null;
    }

    public String getID() { return ID; }
    public int getCapacidad() { return capacidad; }

    // Método 4: consultado desde Stock, pero la fecha vive aquí
    public Instant getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    // Solo Stock debería llamar a esto al modificar su inventario
    void registrarActualizacion() {
        this.ultimaActualizacion = Instant.now();
    }
}
