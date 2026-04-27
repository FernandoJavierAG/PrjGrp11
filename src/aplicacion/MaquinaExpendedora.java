package aplicacion;

import java.time.Instant;
import java.util.Objects;

public class MaquinaExpendedora {
	private String Marca;
	private String numSerie;
	private int capacidad;
	private Instant ultimaActualizacion;
	private Localizacion loc;
	
	
	public MaquinaExpendedora(String Marca, String numSerie, int capacidad, Localizacion loc) throws IllegalArgumentException {
		this.Marca = Marca;
		this.numSerie = numSerie;
		this.loc = loc;
		
		if(!numSerie.matches("[a-zA-Z0-9]+")) {
			throw new IllegalArgumentException("Error de formato en el número de serie. Debe ser una cadena alfanumérica.");
		}
		
		this.capacidad = capacidad;
		if(capacidad < 0) {
			
		}
		
		ultimaActualizacion = null;
	}
	
	public String getID() {
		return Marca+numSerie;
	}

	
	public String getMarca() {
		return Marca;
	}


	public void setMarca(String marca) {
		Marca = marca;
	}


	public String getNumSerie() {
		return numSerie;
	}


	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}


	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
		setUltimaActualizacion(Instant.now());
	}

	public Instant getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(Instant ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Marca, capacidad, numSerie, ultimaActualizacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaquinaExpendedora other = (MaquinaExpendedora) obj;
		return Objects.equals(Marca, other.Marca) && capacidad == other.capacidad
				&& Objects.equals(numSerie, other.numSerie)
				&& Objects.equals(ultimaActualizacion, other.ultimaActualizacion);
	}

	
	
}
