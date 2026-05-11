package aplicacion;

import java.time.Instant;
import java.util.Objects;

public class MaquinaExpendedora {
	private String ID;
	private int capacidad;
	private Instant ultimaActualizacion;
	private Localizacion loc;
	
	
	public MaquinaExpendedora(String marca, String numSerie, int capacidad, Localizacion loc) throws IllegalArgumentException {
		_IsValidMarca(marca);
		_IsValidNumSerie(numSerie);
		StringBuilder newID = new StringBuilder();
		newID.append(marca);
		newID.append("-");
		newID.append(numSerie);
	
		this(newID.toString(), capacidad, loc);
	}
	
	public MaquinaExpendedora(String ID, int capacidad, Localizacion loc) throws IllegalArgumentException {
		_IsValidID(ID);
		this.ID = ID;
		_IsValidCapacidad(capacidad);
		setCapacidad(capacidad);
		this.loc = loc;
		ultimaActualizacion = null;
	}
	
	private static Boolean _IsValidID(String ID) throws IllegalArgumentException {
		if(ID == null || ID.equals(""))
			throw new IllegalArgumentException("Error de formato de ID: No puede ser vacío.");
		if(!ID.contains("-"))
			throw new IllegalArgumentException("Error de formato de ID: No hay separador.");
		
		String campos[] = ID.split("-");
		
		if(campos.length != 2 || campos[0].equals("") || campos[1].equals(""))
			throw new IllegalArgumentException("Error de formato de ID: Campos insuficientes.");
		
		String 
			marca= campos[0],
			numSerie= campos[1];
		
		_IsValidMarca(marca);
		_IsValidNumSerie(numSerie);
		
		return true;
	}
	
	private static Boolean _IsValidNumSerie(String numSerie) throws IllegalArgumentException {
		if(numSerie == null || numSerie.equals(""))
			throw new IllegalArgumentException("Error de formato de número de serie: No puede ser vacío.");
		if(!numSerie.matches("[a-zA-Z0-9]+"))
			throw new IllegalArgumentException("Error de formato de número de serie: Debe ser una cadena alfanumérica.");
		else
			return true;
	}
	
	private static Boolean _IsValidMarca(String marca) throws IllegalArgumentException {
		if(marca == null || marca.equals(""))
			throw new IllegalArgumentException("Error de formato de marca: No puede ser vacía.");
		else
			return true;
	}

	private static Boolean _IsValidCapacidad(int capacidad) throws IllegalArgumentException {
		if(capacidad <= 0)
			throw new IllegalArgumentException("Error: La capacidad debe ser no-negativa.");
		else
			return true;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		_IsValidID(ID);
		String brokenID[] = ID.split("-");
		
		setMarca(brokenID[0]);
		setNumSerie(brokenID[1]);
	}

	
	public String getMarca() {
		return ID.split("-")[0];
	}


	public void setMarca(String marca) {
		_IsValidMarca(marca);

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


	public void setNumSerie(String numSerie) {
		_IsValidNumSerie(numSerie);
		
		StringBuilder newID = new StringBuilder();
		if(ID != null)
			newID.append(ID.split("-")[0]);
		newID.append("-");
		newID.append(numSerie);
		
		this.ID = newID.toString();
	}

	public Localizacion getLoc() {
		return this.loc;
	}
	
	public void setLoc(Localizacion loc) {
		this.loc = loc;
	}

	public int getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(int capacidad) {
		_IsValidCapacidad(capacidad);
		
		this.capacidad = capacidad;
		setUltimaActualizacion(Instant.now());
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
