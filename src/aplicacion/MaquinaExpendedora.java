package aplicacion;

import java.time.Instant;

public class MaquinaExpendedora {
	private String ID;
	private int capacidad;
	private Instant ultimaActualizacion;
	
	public MaquinaExpendedora(String ID, int capacidad) {
		this.ID = ID;
		this.capacidad = capacidad;
		
		ultimaActualizacion = null;
	}
}
