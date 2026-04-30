package aplicacion;

import java.time.Instant;

public class Venta {

	private Instant fecha;
	private int unidades;
	
	//Se toma como momento de la venta el de la creación de su instancia original
	public Venta(int unidades) {
		this.unidades = unidades;
		this.fecha = Instant.now();
	}

	public Instant getFecha() {
		return fecha;
	}

	public void setFecha(Instant fecha) {
		this.fecha = fecha;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
	
	
}
