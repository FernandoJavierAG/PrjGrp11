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
	
	//EL almacenamiento en la base de datos recaería sobre GestionMaquinas
	public void venta(int producto, int unidades) throws IllegalArgumentException {
		
		if(producto < 0 || producto > capacidad) {
			throw new IllegalArgumentException("El producto requerido no existe.");
		}
		
		Venta venta = new Venta(unidades);
		//La nueva venta debe estar a disposición tanto del producto como de la máquina asociada
		this.ventas.add(venta);
		this.stocks.get(producto).getVentas().add(venta);
		
	}
}
