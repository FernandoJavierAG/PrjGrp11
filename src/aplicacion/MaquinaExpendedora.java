package aplicacion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaquinaExpendedora {
	private String ID;
	private int capacidad;
	private Instant ultimaActualizacion;
	private HashMap<Integer,Producto> stocks;
	private List<Venta> ventas;
	
	public MaquinaExpendedora(String ID, int capacidad) {
		this.ID = ID;
		this.capacidad = capacidad;
		ultimaActualizacion = null;
		this.stocks = new HashMap();
		this.ventas = new ArrayList<Venta>();
	}
	
	public String getID() {
		return this.ID;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public Producto getProducto(int producto) {
		return this.stocks.get(producto);
	}
}
