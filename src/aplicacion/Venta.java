package aplicacion;

import java.time.Instant;

public class Venta {

	private Instant fecha;
	private int unidades;
	
	public Venta(int unidades) {
		this.unidades = unidades;
		this.fecha = Instant.now();
	}
	
	
}
