package aplicacion;

import java.time.Instant;
import java.util.Objects;

public class MaquinaExpendedora {
	private String ID;
	private int capacidad;
	private Instant ultimaActualizacion;
	private Localizacion loc;
	
	
	public MaquinaExpendedora(String marca, String numSerie, int capacidad, Localizacion loc) throws IllegalArgumentException {
		StringBuilder newID = new StringBuilder();
		newID.append(marca);
		newID.append("-");
		newID.append(numSerie);
	
		this(newID.toString(), capacidad, loc);
	}
	
	public MaquinaExpendedora(String ID, int capacidad, Localizacion loc) throws IllegalArgumentException {
		this.ID = ID;
		setCapacidad(capacidad);
		this.loc = loc;
		ultimaActualizacion = null;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		String brokenID[] = ID.split("-");
		
		setMarca(brokenID[0]);
		setNumSerie(brokenID[1]);
	}

	
	public String getMarca() {
		return ID.split("-")[0];
	}


	public void setMarca(String marca) {
		StringBuilder newID = new StringBuilder();
		newID.append(marca);
		newID.append("-");
		if(ID != null)
			newID.append(ID.split("-")[1]);
		
		this.ID = newID.toString();
	}


	public String getNumSerie() {
		return ID.split("-")[1];
	}


	public void setNumSerie(String numSerie) throws IllegalArgumentException {
		if(!numSerie.matches("[a-zA-Z0-9]+")) {
			throw new IllegalArgumentException("Error de formato en el número de serie. Debe ser una cadena alfanumérica.");
		}
		
		StringBuilder newID = new StringBuilder();
		if(ID != null)
			newID.append(ID.split("-")[1]);
		newID.append("-");
		newID.append(numSerie);
		
		this.ID = newID.toString();
	}

	public Localizacion getLoc(Localizacion loc) {
		return this.loc;
	}
	
	public void setLoc(Localizacion loc) {
		this.loc = loc;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public float getAltitud() {
		return loc.getAltitud();
	}
	
	public void setAltitud(float altitud) {
		loc.setAltitud(altitud);
	}
	
	public float getLatitud() {
		return loc.getLatitud();
	}
	
	public void setLatitud(float latitud) {
		loc.setLatitud(latitud);
	}
	
	public float getLongitud() {
		return loc.getLongitud();
	}
	
	public void setLongitud(float longitud) {
		loc.setLongitud(longitud);
	}
	
	
	public void setCapacidad(int capacidad) throws IllegalArgumentException {
		this.capacidad = capacidad;
		if(capacidad < 0) {
			throw new IllegalArgumentException("La capacidad debe ser no-negativa.");
		}
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
		return Objects.hash(ID, capacidad, loc, ultimaActualizacion);
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
		return Objects.equals(ID, other.ID) && capacidad == other.capacidad && Objects.equals(loc, other.loc)
				&& Objects.equals(ultimaActualizacion, other.ultimaActualizacion);
	}

	

	
	
}
