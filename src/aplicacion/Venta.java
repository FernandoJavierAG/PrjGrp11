package aplicacion;

import java.time.Instant;

public class Venta {

	private MaquinaExpendedora maquina;
	private Producto producto;
	private Instant fecha;
	private int unidades;
	
	//Se toma como momento de la venta el de la creación de su instancia original
	public Venta(MaquinaExpendedora maquina, Producto producto, int unidades) {
		this.maquina = maquina;
		this.producto = producto;
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

	public MaquinaExpendedora getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaExpendedora maquina) {
		this.maquina = maquina;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
	
	
	
}
