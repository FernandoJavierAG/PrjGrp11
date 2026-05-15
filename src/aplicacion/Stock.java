// Stock.java
package aplicacion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stock {

    // ── Repositorio interno ──────────────────────────────────────────────────
	//Público para acceder fácilmente a este durante las pruebas
    public static final List<Stock> repositorio = new ArrayList<>();

    // ── Atributos de instancia ───────────────────────────────────────────────
    private Producto producto;
    private MaquinaExpendedora maquina;
    private int cantidadActual;
    private int cantidadMaxima;

    // ── Constructores ────────────────────────────────────────────────────────
    public Stock() {}

    public Stock(Producto producto, MaquinaExpendedora maquina,
                 int cantidadActual, int cantidadMaxima) {
        this.producto      = producto;
        this.maquina       = maquina;
        this.cantidadActual = cantidadActual;
        this.cantidadMaxima = cantidadMaxima;
        repositorio.add(this);                  // se registra al crearse
        maquina.registrarActualizacion();
    }



    
    // ── Método: asignar nueva lista de productos a una máquina ───────────────
    /**
     * Asigna una nueva lista de productos a una máquina expendedora.
     * Elimina los registros de stock anteriores de esa máquina y crea
     * nuevos registros con cantidadActual = 0 para cada producto nuevo.
     */
    public static void asignarProductos(MaquinaExpendedora maquina, List<Producto> nuevosProductos) {

        // Eliminar del repositorio los stocks actuales de esa máquina
        List<Stock> aEliminar = new ArrayList<>();
        for (Stock s : repositorio) {
            if (s.maquina.getID().equals(maquina.getID())) {
                aEliminar.add(s);
            }
        }
        for (Stock s : aEliminar) {
            repositorio.remove(s);
        }

        // Actualizar la lista de productos en la máquina OJO FUNCIONALIDAD INTERESANTE DE JAVA PONER DENTRO DEL CONSTRUCTOR DEL ARRAY OTRO ARRAY PARA QUE COJA SUS DATOS
        maquina.setProductos(new ArrayList<>(nuevosProductos));

        // Crear un registro de stock a 0 por cada producto nuevo
        for (Producto p : nuevosProductos) {
            new Stock(p, maquina, 0, 0);
        }
    }

   // ── Método 1 ─────────────────────────────────────────────────────────────
/**
 * Devuelve todos los registros de stock de una máquina expendedora concreta.
 */
public static List<Stock> consultarPorMaquina(MaquinaExpendedora maquina) {
    List<Stock> resultado = new ArrayList<>();
    for (Stock s : repositorio) {
        if (s.maquina.getID().equals(maquina.getID())) {
            resultado.add(s);
        }
    }
    return resultado;
}

// ── Método 2 ─────────────────────────────────────────────────────────────
/**
 * Devuelve todos los registros de stock de un producto concreto.
 */
public static List<Stock> consultarPorProducto(Producto producto) {
    List<Stock> resultado = new ArrayList<>();
    for (Stock s : repositorio) {
        if (s.producto.getID().equals(producto.getID())) {
            resultado.add(s);
        }
    }
    return resultado;
}

// ── Método 3 ─────────────────────────────────────────────────────────────
/**
 * Modifica el stock de un producto en una máquina expendedora.
 * Si el par (maquina, producto) ya existe, actualiza sus cantidades.
 * Si no existe, crea un nuevo registro.
 */
public static void modificarStock(MaquinaExpendedora maquina, Producto producto,
                                  int nuevaCantidad, int nuevaMaxima) {
    Stock existente = null;
    for (Stock s : repositorio) {
        if (s.maquina.getID().equals(maquina.getID())
                && s.producto.getID().equals(producto.getID())) {
            existente = s;
            break;
        }
    }

    if (existente != null) {
        existente.cantidadActual = nuevaCantidad;
        if (nuevaMaxima >= 0) existente.cantidadMaxima = nuevaMaxima;
        maquina.registrarActualizacion();
    } else {
        int maxima = nuevaMaxima >= 0 ? nuevaMaxima : nuevaCantidad;
        new Stock(producto, maquina, nuevaCantidad, maxima); // el constructor ya actualiza la fecha
    }
}
    // ── Método 4 ─────────────────────────────────────────────────────────────
    /**
     * Consulta la fecha y hora de la última actualización de stock
     * de una máquina expendedora como conjunto.
     * Devuelve null si nunca se ha actualizado.
     */
    public static Instant consultarUltimaActualizacion(MaquinaExpendedora maquina) {
        return maquina.getUltimaActualizacion();
    }

    // ── Getters y Setters ────────────────────────────────────────────────────
    public Producto getProducto()                       { return producto; }
    public void setProducto(Producto producto)          { this.producto = producto; }
    public MaquinaExpendedora getMaquina()              { return maquina; }
    public void setMaquina(MaquinaExpendedora maquina)  { this.maquina = maquina; }
    public int getCantidadActual()                      { return cantidadActual; }
    public void setCantidadActual(int cantidadActual)   { this.cantidadActual = cantidadActual; }
    public int getCantidadMaxima()                      { return cantidadMaxima; }
    public void setCantidadMaxima(int cantidadMaxima)   { this.cantidadMaxima = cantidadMaxima; }

    @Override
    public String toString() {
        return String.format("Stock{producto='%s', maquina='%s', actual=%d, max=%d}",
                producto.getNombre(), maquina.getID(), cantidadActual, cantidadMaxima);
    }
}
