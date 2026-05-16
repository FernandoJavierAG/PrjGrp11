	package aplicacion;

import java.time.Instant;

public class Venta {

	private MaquinaExpendedora maquina;
	private Producto producto;
	private Instant fecha;
	private int unidades;
	
	//Se toma como momento de la venta el de la creación de su instancia original
	public Venta(MaquinaExpendedora maquina, Producto producto, int unidades) throws IllegalArgumentException{
		if (maquina == null)
				throw new IllegalArgumentException("Maquina nula introducida.");
		this.maquina = maquina;
		if (producto == null)
			throw new IllegalArgumentException("Producto nulo introducido.");
		this.producto = producto;
		if (unidades < 1)
			throw new IllegalArgumentException("Unidades no-validas introducidas.");
		this.unidades = unidades;
		this.fecha = Instant.now();
	}

	public Instant getFecha() {
		return fecha;
	}

	public void setFecha(Instant fecha) {
		if (fecha == null)
			throw new IllegalArgumentException("Fecha nula introducida.");
		if (fecha.isAfter(Instant.now()))
			throw new IllegalArgumentException("Fecha futura introducida.");
		this.fecha = fecha;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) throws IllegalArgumentException {
		if (unidades < 1)
			throw new IllegalArgumentException("Unidades no-validas introducidas.");
		this.unidades = unidades;
	}

	public MaquinaExpendedora getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaExpendedora maquina) throws IllegalArgumentException {
		if (maquina == null)
			throw new IllegalArgumentException("Maquina nula introducida.");
		this.maquina = maquina;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) throws IllegalArgumentException {
		if (producto == null)
			throw new IllegalArgumentException("Producto nulo introducido.");
		this.producto = producto;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return (this.fecha.equals(other.getFecha())&& this.maquina.equals(other.getMaquina()));
	}
	
	
	
	
}
